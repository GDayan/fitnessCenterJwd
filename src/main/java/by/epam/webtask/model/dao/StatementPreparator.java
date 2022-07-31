package by.epam.webtask.model.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementPreparator {
    void accept(PreparedStatement t) throws SQLException;
}