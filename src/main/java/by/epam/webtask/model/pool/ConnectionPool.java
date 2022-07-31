package by.epam.webtask.model.pool;

import by.epam.webtask.exception.DatabaseConnectionException;

import java.sql.Connection;

public interface ConnectionPool {
    boolean init();
    boolean isInitialized();
    Connection takeConnection() throws DatabaseConnectionException;
    boolean releaseConnection(Connection connection);
    boolean shutDown();
}