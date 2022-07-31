package by.epam.webtask.model.dao;

import by.epam.webtask.exception.DaoException;
import by.epam.webtask.model.entity.UserDetails;
import by.epam.webtask.model.entity.UserRole;

import java.math.BigDecimal;
import java.util.List;

public interface UserDetailsDao extends EntityDao<UserDetails> {

    boolean updateDiscountByRole(BigDecimal discount, UserRole role) throws DaoException;

    List<UserDetails> findByUserId(Long userId) throws DaoException;
}
