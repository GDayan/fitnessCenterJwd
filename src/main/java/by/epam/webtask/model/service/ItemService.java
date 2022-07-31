package by.epam.webtask.model.service;

import by.epam.webtask.exception.ServiceException;
import by.epam.webtask.model.entity.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ItemService extends EntityService<Item> {

    void changeIsArchive(long id) throws ServiceException;

    Optional<Item> find(long id) throws ServiceException;

    boolean update(Long id, String name, String price, String description) throws ServiceException;

    Item insert(String name, String price, String description) throws ServiceException;

    BigDecimal calculateItemPriceForUser(long userId, long itemId) throws ServiceException;

    List<Item> modifyItemsByDiscount(List<Item> items, BigDecimal discount);
}
