package by.epam.webtask.model.dao;

import by.epam.webtask.exception.DaoException;
import by.epam.webtask.model.entity.User;
import by.epam.webtask.model.entity.UserRole;
import by.epam.webtask.model.entity.UserStatus;

import java.util.List;
import java.util.Optional;

public interface UserDao extends EntityDao<User> {
    boolean updateStatus(UserStatus status, long id) throws DaoException;

    Optional<User> findByEmail(String email) throws DaoException;

    List<User> findActiveTrainers() throws DaoException;

    List<User> findActiveClients() throws DaoException;

    boolean updateRole(UserRole role, long id) throws DaoException;

}
