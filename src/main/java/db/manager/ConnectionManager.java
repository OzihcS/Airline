package db.manager;

import java.sql.Connection;

/**
 * Manages a data source connection.
 *
 * @see Connection
 */
public interface ConnectionManager {

    /**
     * Receives connection from pool
     *
     * @return received connection
     */
    Connection getConnection();

    /**
     * Closes connection.
     */
    void shutdown();

}
