package by.epam.webtask.model.dao;

import by.epam.webtask.exception.DaoException;
import by.epam.webtask.model.entity.Order;
import by.epam.webtask.model.entity.OrderStatus;

import java.util.List;

public interface OrderDao extends EntityDao<Order> {
    boolean updateStatus(OrderStatus status, long id) throws DaoException;

    List<Order> findByStatus(OrderStatus status) throws DaoException;

    List<Order> findByTrainerId(Long trainerId) throws DaoException;

    List<Order> findByUserId(Long userId) throws DaoException;

    List<Order> findByAssignmentTrainerId(Long trainerId) throws DaoException;

    List<Order> findActiveByUserId(Long userId) throws DaoException;
}
