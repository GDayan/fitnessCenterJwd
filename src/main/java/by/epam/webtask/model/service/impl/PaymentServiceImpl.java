package by.epam.webtask.model.service.impl;

import by.epam.webtask.exception.DaoException;
import by.epam.webtask.exception.ServiceException;
import by.epam.webtask.model.dao.CardDao;
import by.epam.webtask.model.dao.impl.DaoProvider;
import by.epam.webtask.model.entity.Order;
import by.epam.webtask.model.service.PaymentService;
import by.epam.webtask.validator.OrderValidator;

import java.math.BigDecimal;
import java.math.MathContext;

public class PaymentServiceImpl implements PaymentService {
    private static final BigDecimal PERCENT_DIVISOR = BigDecimal.valueOf(100);
    private static final MathContext MONEY_PRECISION = new MathContext(2);
    private final CardDao cardDao;

    PaymentServiceImpl() {
        cardDao = DaoProvider.getInstance().getCardDao();
    }

    @Override
    public void establishCredit(String cardNumber, Order order)
            throws ServiceException {
        BigDecimal percentage = DEFAULT_CREDIT_PERCENTAGE;
        int numberOfMonths = DEFAULT_CREDIT_PERIOD;
        try {
            cardDao.establishCredit(cardNumber,
                    calcCreditPricePerMonth(order.getPrice(), percentage, numberOfMonths),
                    numberOfMonths, order.getId(), order.getUserDetailsId());
        } catch (DaoException e) {
            throw new ServiceException("Unable to establish credit", e);
        }
    }

    @Override
    public boolean doPayment(String cardNumber, Order order) throws ServiceException {
        if (!OrderValidator.isValidCardNumber(cardNumber)) {
            return false;
        }
        try {
            return cardDao.withdraw(cardNumber, order.getPrice());
        } catch (DaoException e) {
            throw new ServiceException("Unable to execute withdraw", e);
        }
    }

    @Override
    public boolean checkCardExistence(String cardNumber) throws ServiceException {
        if (!OrderValidator.isValidCardNumber(cardNumber)) {
            return false;
        }
        try {
            return cardDao.isCardExist(cardNumber);
        } catch (DaoException e) {
            throw new ServiceException("Unable to check card existence", e);
        }
    }

    @Override
    public boolean checkCardBalance(String cardNumber, BigDecimal amount, boolean isCredit) throws ServiceException {
        if (!OrderValidator.isValidCardNumber(cardNumber)) {
            return false;
        }
        if (isCredit) {
            amount = calcCreditPricePerMonth(amount, DEFAULT_CREDIT_PERCENTAGE, DEFAULT_CREDIT_PERIOD);
        }
        try {
            return cardDao.isEnoughMoney(cardNumber, amount);
        } catch (DaoException e) {
            throw new ServiceException("Unable to check card balance", e);
        }
    }

    private BigDecimal calcCreditPricePerMonth(BigDecimal price, BigDecimal percentage, int numOfMonths) {
        BigDecimal result = price.add(price.multiply(percentage.divide(PERCENT_DIVISOR)));
        return result.divide(BigDecimal.valueOf(numOfMonths), MONEY_PRECISION);
    }
}
