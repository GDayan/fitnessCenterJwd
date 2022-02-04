package by.epam.webtask.validator.impl;

import by.epam.webtask.validator.Validator;

import java.util.Map;
import static by.epam.webtask.controller.RequestParameter.*;

public class ValidatorImpl implements Validator {
    private static final String NAME_PATTERN = "^[A-Za-zА-Яа-я]{3,50}$";
    private static final String USER_LOGIN_PATTERN = "^[A-Za-zА-Яа-я0-9_]{4,16}$";
    private static final String USER_PASSWORD_PATTERN = "^[A-Za-zА-Яа-я0-9\\.]{5,40}$";
    private static final String USER_MAIL_PATTERN = "^[A-Za-z0-9\\.]{1,30}@[a-z]{2,7}\\.[a-z]{2,4}$";
    private static final String USER_PHONE_NUMBER_PATTERN = "\\d{9}";
    private static ValidatorImpl instance;

    private ValidatorImpl() {
    }

    public static ValidatorImpl getInstance() {
        if (instance == null) {
            instance = new ValidatorImpl();
        }
        return instance;
    }

    public boolean isCorrectName(String name) {
        return isNotNullOrEmpty(name) && name.matches(NAME_PATTERN);
    }

    public boolean isCorrectLogin(String login){
        return isNotNullOrEmpty(login) && login.matches(USER_LOGIN_PATTERN);
    }

    public boolean isCorrectPassword(String password){
        return isNotNullOrEmpty(password) && password.matches(USER_PASSWORD_PATTERN);
    }

    public boolean isCorrectEmail(String gmail){
        return isNotNullOrEmpty(gmail) && gmail.matches(USER_MAIL_PATTERN);
    }

    public boolean isCorrectPhoneNumber(String phoneNumber){
        return isNotNullOrEmpty(phoneNumber) && phoneNumber.matches(USER_PHONE_NUMBER_PATTERN);
    }

    private boolean isNotNullOrEmpty(String line){
        return line != null && !line.isEmpty();
    }

    @Override
    public boolean checkRegistration(Map<String, String> map) {
        boolean result = true;
        String firstName = map.get(USER_FIRST_NAME);
        String lastName = map.get(USER_LAST_NAME);
        String login = map.get(LOGIN);
        String password = map.get(PASSWORD);
        String email = map.get(USER_EMAIL);
        String phone = map.get(USER_PHONE_NUMBER);

        if(!isCorrectName(firstName)){
            map.put(USER_FIRST_NAME,INVALID_FIRST_NAME);
            result = false;
        }
        if(!isCorrectName(lastName)){
            map.put(USER_LAST_NAME,INVALID_LAST_NAME);
            result = false;
        }
        if(!isCorrectLogin(login)){
            map.put(LOGIN,INVALID_LOGIN);
            result = false;
        }
        if(!isCorrectPassword(password)){
            map.put(PASSWORD,INVALID_PASSWORD);
            result = false;
        }
        if(!isCorrectEmail(email)){
            map.put(USER_EMAIL, INVALID_EMAIL);
            result = false;
        }
        if(!isCorrectPhoneNumber(phone)){
            map.put(USER_PHONE_NUMBER,INVALID_PHONE_NUMBER);
            result = false;
        }
        return result;
    }

}
