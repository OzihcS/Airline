package repository.JDBCRepository;

import db.holder.ConnectionHolder;
import exception.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides a base functionality for all repositories.
 */
public abstract class JDBCAbstractRepository<T> {

    public static final Logger LOGGER = LoggerFactory.getLogger(JDBCAbstractRepository.class);
    protected static final String ERROR_MESSAGE = "Cannot handle sql ['{}']; Message: ";
    private final ConnectionHolder connectionHolder;

    /**
     * Creates a new repository.
     *
     * @param connectionHolder connection holder
     */
    public JDBCAbstractRepository(ConnectionHolder connectionHolder) {
        this.connectionHolder = connectionHolder;
    }


    /**
     * Gets a connection from connection holder.
     *
     * @return connection from connection holder
     */
    protected Connection getConnection() {
        return connectionHolder.get();
    }

    protected boolean delete(int id, String sql) {
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            return id != 0 && ps.executeUpdate() != 0;
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
    }

    protected T get(int id, String sql) {
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            T entity = null;
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                entity = extractFromResultSet(rs);
            }
            return entity;
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
    }

    protected boolean addEnum(int id, String value, String sql) {
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, value);

            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
    }

    /**
     * Generates a message for {@link DataAccessException}
     *
     * @param sql query that has not been executed properly
     * @return generated message
     */
    protected String getMessage(String sql) {
        return "Cannot handle sql ['" + sql + "']";
    }

    protected List<T> getAllByEnum(String value, String sql) {
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, value);

            return extractListFromPreparedStatement(ps);
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
    }

    protected List<T> extractListFromPreparedStatement(PreparedStatement ps) throws SQLException {
        List<T> studentCourses = new ArrayList<>();
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            T entity = extractFromResultSet(rs);
            studentCourses.add(entity);
        }

        return studentCourses;
    }

    protected List<T> queryListForTwoParams(int firstParam, int secondParam, String sql) {
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, firstParam);
            ps.setInt(2, secondParam);
            LOGGER.debug(ps.toString());
            return extractListFromPreparedStatement(ps);
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
    }

    protected boolean queryBooleanForTwoParams(int firstParam, int secondParam, String sql) {
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, firstParam);
            ps.setInt(2, secondParam);

            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
    }

    protected abstract T extractFromResultSet(ResultSet rs) throws SQLException;

}