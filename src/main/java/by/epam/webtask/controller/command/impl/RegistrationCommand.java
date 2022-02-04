package by.epam.webtask.controller.command.impl;

import by.epam.webtask.controller.Router;
import by.epam.webtask.controller.command.Command;
import by.epam.webtask.exception.CommandException;
import by.epam.webtask.model.service.UserService;
import by.epam.webtask.model.service.impl.UserServiceImpl;
import com.google.protobuf.ServiceException;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import static by.epam.webtask.controller.RequestParameter.*;
import static by.epam.webtask.controller.PathPage.*;
import static by.epam.webtask.controller.PropertiesKey.*;

public class RegistrationCommand implements Command {
    private static final UserService service = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Map<String,String> mapData = new HashMap<>();
        mapData.put(USER_FIRST_NAME, request.getParameter(USER_FIRST_NAME));
        mapData.put(USER_LAST_NAME, request.getParameter(USER_LAST_NAME));
        mapData.put(LOGIN, request.getParameter(LOGIN));
        mapData.put(PASSWORD, request.getParameter(PASSWORD));
        mapData.put(USER_EMAIL, request.getParameter(USER_EMAIL));
        mapData.put(USER_PHONE_NUMBER, request.getParameter(USER_PHONE_NUMBER));
        Router router = new Router();
        try {
            if (service.userRegistration(mapData)) {
                router.setRedirectType();
                router.setCurrentPage(SIGN_PAGE);
            } else {
                for (String key : mapData.keySet()) {
                    String currentValue = mapData.get(key);
                    switch (currentValue) {
                        case INVALID_FIRST_NAME -> request.setAttribute(INVALID_FIRST_NAME, INVALID_FIRST_MESSAGE);
                        case INVALID_EMAIL -> request.setAttribute(INVALID_EMAIL, INVALID_EMAIL_MESSAGE);
                        case INVALID_LAST_NAME -> request.setAttribute(INVALID_LAST_NAME, INVALID_LAST_MESSAGE);
                        case INVALID_LOGIN -> request.setAttribute(INVALID_LOGIN, INVALID_LOGIN_MESSAGE);
                        case INVALID_PASSWORD -> request.setAttribute(INVALID_PASSWORD, INVALID_PASSWORD_MESSAGE);
                        case INVALID_PHONE_NUMBER -> request.setAttribute(INVALID_PHONE_NUMBER, INVALID_PHONE_NUMBER_MESSAGE);
                        case NOT_UNIQ_EMAIL -> request.setAttribute(INVALID_EMAIL, NOT_UNIQ_EMAIL_MESSAGE);
                        case NOT_UNIQ_LOGIN -> request.setAttribute(INVALID_LOGIN, NOT_UNIQ_LOGIN_MESSAGE);
                        case NOT_UNIQ_PHONE -> request.setAttribute(INVALID_PHONE_NUMBER, NOT_UNIQ_PHONE_MESSAGE);
                    }
                }
                router.setCurrentPage(REGISTRATION_PAGE);
            }
        } catch (ServiceException e) {
            throw new CommandException("Error while registration command", e);
        }
        return router;
    }
}
