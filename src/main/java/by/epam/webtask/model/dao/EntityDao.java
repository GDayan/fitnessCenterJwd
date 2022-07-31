package by.epam.webtask.model.dao;

import by.epam.webtask.exception.DaoException;
import by.epam.webtask.model.entity.Entity;

import java.util.List;
import java.util.Optional;

public interface EntityDao<T extends Entity> {

    T create(T entity) throws DaoException;

    List<T> read() throws DaoException;

    Optional<T> read(Long id) throws DaoException;

    boolean update(T entity) throws DaoException;

    boolean delete(Long id) throws DaoException;
}
