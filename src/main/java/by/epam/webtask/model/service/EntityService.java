package by.epam.webtask.model.service;

import by.epam.webtask.exception.ServiceException;
import by.epam.webtask.model.entity.Entity;

import java.util.List;

public interface EntityService<T extends Entity> {

    List<T> findAll() throws ServiceException;

    boolean update(T entity) throws ServiceException;

    T insert(T entity) throws ServiceException;
}
