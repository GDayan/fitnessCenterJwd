package by.epam.webtask.model.service;

import by.epam.webtask.exception.ServiceException;
import by.epam.webtask.model.entity.*;

import java.util.List;
import java.util.Optional;

/**
 * The interface represents user service
 */
public interface UserService extends EntityService<User> {
    Optional<User> register(String email, String password1, String password2, String firstName, String secondName,
                            UserRole role, UserStatus status, String locale) throws ServiceException;

    Optional<User> authenticate(String login, String password) throws ServiceException;

    Optional<User> findUserByEmail(String email) throws ServiceException;

    Optional<User> findUserById(Long id) throws ServiceException;

    void updateUserStatus(UserStatus status, long id) throws ServiceException;

    void updateUserRole(UserRole role, long id) throws ServiceException;

    boolean isEmailRegistered(String email) throws ServiceException;

    Optional<User> findUserByTokenId(long tokenId) throws ServiceException;

    Optional<Token> retrieveUserToken(long tokenId) throws ServiceException;

    boolean confirmUser(long tokenId, String tokenValue) throws ServiceException;

    List<User> findActiveTrainers() throws ServiceException;

    List<User> findActiveClients() throws ServiceException;

    UserDetails findUserDetails(long userId) throws ServiceException;

    List<UserDetails> findAllUserDetails() throws ServiceException;

    UserDetails addUserDetails(User user) throws ServiceException;

    void updateUserDetails(UserDetails userDetails) throws ServiceException;

    void updateUserData(long id, String fieldName, String value) throws ServiceException;

    void updateUserDetailsDiscount(long userId, String discount) throws ServiceException;

    void updateUserDetailsTrainerId(UserDetails userDetails, long trainerId) throws ServiceException;

    void updateUserDiscountByRole(long userId, String discount) throws ServiceException;

    boolean deleteUser(long userId) throws ServiceException;
}