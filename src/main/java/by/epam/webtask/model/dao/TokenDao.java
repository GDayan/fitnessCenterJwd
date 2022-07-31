package by.epam.webtask.model.dao;

import by.epam.webtask.exception.DaoException;
import by.epam.webtask.model.entity.Token;

import java.util.List;

public interface TokenDao extends EntityDao<Token> {

    long removeByUserId(Long userId) throws DaoException;

    List<Token> findByUserId(Long userId) throws DaoException;

    void removeExpiredToken(int days) throws DaoException;
}