package by.epam.webtask.model.service.impl;

import by.epam.webtask.exception.DaoException;
import by.epam.webtask.model.dao.EntityTransaction;
import by.epam.webtask.model.dao.impl.UserDaoImpl;
import by.epam.webtask.model.entity.User;
import by.epam.webtask.model.service.UserService;
import by.epam.webtask.util.PasswordEncryption;
import by.epam.webtask.util.TextUtil;
import by.epam.webtask.util.mail.MailSender;
import by.epam.webtask.validator.Validator;
import by.epam.webtask.validator.impl.ValidatorImpl;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.Optional;

import static by.epam.webtask.controller.RequestParameter.*;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger();
    private static final String REGISTRATION_SUBJECT = "GymFitness registration";
    private static final String REGISTRATION_BODY = "Registration was successful";
    private static UserServiceImpl instance;

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public Optional<User> signIn(String login, String password) throws ServiceException {
        UserDaoImpl userDao = new UserDaoImpl();
        login = TextUtil.escapeText(login);
        password = TextUtil.escapeText(password);
        EntityTransaction transaction = new EntityTransaction();
        transaction.init(userDao);
        String encryptPassword = PasswordEncryption.md5Apache(password);
        try {
            return userDao.findUserByLoginAndPassword(login, encryptPassword);
        } catch (DaoException e) {
            throw new ServiceException(e); //TODO какое сообщение?
        } finally {
            transaction.end();
        }
    }

    public boolean userRegistration(Map<String,String> mapData) throws ServiceException{
        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction transaction = new EntityTransaction();
        transaction.init(userDao);
        try {
            Validator validator = ValidatorImpl.getInstance();
            boolean commonResult = validator.checkRegistration(mapData);
            logger.log(Level.INFO, "Common result " + commonResult);
            if(!commonResult){
                return false;
            }
            String firstName = mapData.get(USER_FIRST_NAME);
            String lastName = mapData.get(USER_LAST_NAME);
            String login = mapData.get(LOGIN);
            String email = mapData.get(USER_EMAIL);
            String phone = mapData.get(USER_PHONE_NUMBER);
            String password = mapData.get(PASSWORD);
            String encryptPassword = PasswordEncryption.md5Apache(password);
            int phoneNumber = Integer.parseInt(phone);
            long discountId = 1;
            boolean uniqResult = true;
            if(userDao.findUserByLogin(login).isPresent()){
                mapData.put(LOGIN,NOT_UNIQ_LOGIN);
                uniqResult = false;
            }
            if(userDao.findUserByEmail(email).isPresent()){
                mapData.put(USER_EMAIL,NOT_UNIQ_EMAIL);
                uniqResult = false;
            }
            if(userDao.findUserByPhoneNumber(phoneNumber).isPresent()){
                mapData.put(USER_PHONE_NUMBER,NOT_UNIQ_PHONE);
                uniqResult = false;
            }
            if(!uniqResult){
                return false;
            }
            User user = new User(firstName, lastName, login, encryptPassword, email,
                    phoneNumber, User.UserRole.CLIENT, User.UserState.ACTIVE);//TODO нормально что новый сразу активный?
            boolean isUserCreate = userDao.create(user);
            if(isUserCreate){
                MailSender.getInstance().send(email,REGISTRATION_SUBJECT,REGISTRATION_BODY);
            }
            return isUserCreate;
        } catch (DaoException e) {
            throw new ServiceException("Add user error: ", e);
        }finally {
            transaction.end();
        }
    }
}
