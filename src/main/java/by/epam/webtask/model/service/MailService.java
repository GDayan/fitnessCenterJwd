package by.epam.webtask.model.service;

import by.epam.webtask.exception.ServiceException;

public interface MailService {

    long sendConfirmationEmail(long userId, String email, String locale) throws ServiceException;
}