package by.epam.webtask.validator;

import java.util.Map;

public interface Validator {
    boolean isCorrectName(String name);
    boolean isCorrectLogin(String login);
    boolean isCorrectPassword(String password);
    boolean isCorrectEmail(String gmail);
    boolean isCorrectPhoneNumber(String phoneNumber);
    boolean checkRegistration(Map<String, String> map);
}
