package by.epam.webtask.controller.command;

import by.epam.webtask.controller.PagePath;

import by.epam.webtask.controller.ResponseCreator;
import by.epam.webtask.model.entity.Item;
import by.epam.webtask.model.entity.User;
import by.epam.webtask.model.entity.UserDetails;
import by.epam.webtask.model.service.ItemService;
import by.epam.webtask.validator.NumberValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class CommandHelper {
    private static final Logger LOG = LogManager.getLogger(CommandHelper.class);

    private CommandHelper() {

    }

    public static CommandResponse createInfoErrorResponse(ResponseCreator responseCreator, CommandRequest request) {
        request.addToSession(Attribute.INFO_BUNDLE_KEY, ResourceBundleKey.INFO_ERROR_LINK);
        return responseCreator.createRedirectResponse(PagePath.SHOW_INFO_REDIRECT);
    }

    public static Optional<UserDetails> retrieveUserDetailsFromSession(CommandRequest request) {
        Optional<Object> userDetailsOptional = request.retrieveFromSession(Attribute.USER_DETAILS);
        if (!userDetailsOptional.isPresent()) {
            LOG.error("Not found user details for user: {}",
                    request.retrieveFromSession(Attribute.USER).isPresent()
                            ? request.retrieveFromSession(Attribute.USER).get() : "user not found in session");
            return Optional.empty();
        }
        return Optional.of((UserDetails) userDetailsOptional.get());
    }

    public static Optional<User> retrieveUserFromSession(CommandRequest request) {
        Optional<Object> userOptional = request.retrieveFromSession(Attribute.USER);
        if (!userOptional.isPresent()) {
            LOG.error("Not found user: {}",
                    request.retrieveFromSession(Attribute.USER).isPresent()
                            ? request.retrieveFromSession(Attribute.USER).get() : "user not found in session");
            return Optional.empty();
        }
        return Optional.of((User) userOptional.get());
    }

    public static Optional<Long> retrievePositiveLongParameter(CommandRequest request, String parameter) {
        String paramStr = request.getParameter(parameter);
        if (paramStr != null) {
            if (!NumberValidator.isPositiveInteger(paramStr)) {
                LOG.error("Parameter {} is not valid", parameter);
                return Optional.empty();
            }
            long number = Long.parseLong(paramStr);
            return Optional.of(number);
        }
        LOG.error("Parameter {} is null", parameter);
        return Optional.empty();
    }

    public static void addDiscountListToJsp(CommandRequest request, List<Item> products, ItemService itemService) {
        ArrayList<Item> clonedProductList = new ArrayList<>();
        try {
            for (Item item : products) {
                clonedProductList.add(item.clone());
            }
        } catch (CloneNotSupportedException e) {
            LOG.error("Unable to clone products list", e);
        }
        Optional<Object> optionalUserDetails = request.retrieveFromSession(Attribute.USER_DETAILS);
        if (optionalUserDetails.isPresent()) {
            BigDecimal discount = ((UserDetails) optionalUserDetails.get()).getDiscount();
            if (discount != null && discount.compareTo(BigDecimal.ZERO) > 0) {
                request.addAttributeToJsp(Attribute.PRODUCT_LIST_WITH_DISCOUNT,
                        itemService.modifyItemsByDiscount(clonedProductList, discount));
            }
        }
    }
}
