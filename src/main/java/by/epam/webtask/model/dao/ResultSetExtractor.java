package by.epam.webtask.model.dao;

import by.epam.webtask.exception.DaoException;
import by.epam.webtask.model.entity.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
public interface ResultSetExtractor<T extends Entity> {
    T extract(ResultSet resultSet) throws DaoException;

    default List<T> extractAll(ResultSet resultSet) throws DaoException {
        List<T> entities = new ArrayList<>();
        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new DaoException("Unable to iterate through a ResultSet", e);
            }
            final T entity = this.extract(resultSet);
            entities.add(entity);
        }
        return entities;
    }
}