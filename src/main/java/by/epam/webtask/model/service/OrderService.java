package by.epam.webtask.model.service;

import by.epam.webtask.exception.ServiceException;
import by.epam.webtask.model.entity.Order;
import by.epam.webtask.model.entity.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderService extends EntityService<Order> {

    List<Order> findOrderByStatus(OrderStatus status) throws ServiceException;

    List<Order> findOrderByUserId(Long userId) throws ServiceException;

    Optional<Order> findOrderById(Long id) throws ServiceException;

    List<Order> findOrderByAssignmentTrainerId(Long trainerId) throws ServiceException;

    List<Order> findOrderByAssignmentTrainerIdAndStatus(Long trainerId, OrderStatus status, OrderStatus... statuses)
            throws ServiceException;


    List<Order> findOrderByUserIdAndStatus(Long userId, OrderStatus status, OrderStatus... statuses)
            throws ServiceException;

    void updateOrderStatus(OrderStatus status, long id) throws ServiceException;

    boolean addOrderReview(String review, long id) throws ServiceException;

    Order insert(long userDetailsId, OrderStatus status, long itemId, long trainerId, long period,
                 String comment) throws ServiceException;

    boolean delete(long orderId) throws ServiceException;
}
