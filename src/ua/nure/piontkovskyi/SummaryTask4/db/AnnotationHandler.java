package ua.nure.piontkovskyi.SummaryTask4.db;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.piontkovskyi.SummaryTask4.annotation.*;
import ua.nure.piontkovskyi.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.piontkovskyi.SummaryTask4.db.manager.ConnectionManager;
import ua.nure.piontkovskyi.SummaryTask4.exception.DataAccessException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Provides functionality for {@link Transactional}, {@link Cacheable} and {@link EvictCache} transactions handling.
 */
public class AnnotationHandler implements InvocationHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnnotationHandler.class);
    private static final String COMMIT_FAILED = "Commit failed";
    private static final String INVOCATION_FAILED = "Invocation failed";
    private static final String ROLLBACK_FAILED = "Rollback failed";
    private static final String CLOSING_CONNECTION_FAILED = "Closing connection failed";
    private final ConnectionManager connectionManager;
    private final ConnectionHolder connectionHolder;
    private final Object serviceToInvoke;
    private final Cache cache;

    /**
     * Creates a new transaction handler.
     *
     * @param holder          connection holder
     * @param serviceToInvoke service to invoke
     * @param manager         connection manager
     * @param cache           cache manager
     */
    public AnnotationHandler(ConnectionHolder holder, Object serviceToInvoke, ConnectionManager manager, Cache cache) {
        this.connectionManager = manager;
        this.serviceToInvoke = serviceToInvoke;
        this.connectionHolder = holder;
        this.cache = cache;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        if (method.isAnnotationPresent(EvictCache.class)) {
            evictCache();
        }

        boolean cacheable = method.isAnnotationPresent(Cacheable.class);
        StringBuilder sb = new StringBuilder(method.getName());
        if (args != null) {
            for (Object o : args) {
                if (o != null) {
                    sb.append(o.toString());
                } else {
                    sb.append("null");
                }
            }
        }

        if (cacheable) {
            Element e = cache.get(sb.toString());
            if (e != null) {
                return e.getObjectValue();
            }
        }

        Object cacheableObj;
        if (method.isAnnotationPresent(Transactional.class)) {
            cacheableObj = invokeWithTransaction(method, args);
        } else {
            cacheableObj = invokeWithoutTransaction(method, args);
        }

        if (cacheable)
            cache.put(new Element(sb.toString(), cacheableObj));

        return cacheableObj;

    }

    private void evictCache() {
        cache.removeAll();
    }

    private Object invokeWithoutTransaction(Method method, Object[] args) {
        LOGGER.debug("Invoking without transaction");
        Connection connection = connectionManager.getConnection();
        connectionHolder.set(connection);
        try {
            connection.setAutoCommit(true);
            return method.invoke(serviceToInvoke, args);
        } catch (Exception e) {
            LOGGER.warn(INVOCATION_FAILED, e);
            throw new DataAccessException(INVOCATION_FAILED, e);
        } finally {
            closeConnection(connection);
            connectionHolder.remove();
        }
    }

    private Object invokeWithTransaction(Method method, Object[] args) {
        LOGGER.debug("Invoking with transaction");
        Connection connection = connectionManager.getConnection();
        connectionHolder.set(connection);
        try {
            Object result;
            connection.setAutoCommit(false);
            try {
                result = method.invoke(serviceToInvoke, args);
            } catch (Exception e) {
                LOGGER.warn(INVOCATION_FAILED, e);
                throw new DataAccessException(INVOCATION_FAILED, e);
            }
            connection.commit();
            return result;
        } catch (Exception e) {
            rollback(connection);
            LOGGER.warn(COMMIT_FAILED, e);
            throw new DataAccessException(COMMIT_FAILED, e);
        } finally {
            closeConnection(connection);
            connectionHolder.remove();
        }
    }

    private void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(CLOSING_CONNECTION_FAILED, e);
            throw new DataAccessException(CLOSING_CONNECTION_FAILED, e);
        }
    }

    private void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e) {
            LOGGER.error(ROLLBACK_FAILED, e);
            throw new DataAccessException(ROLLBACK_FAILED, e);
        }
    }
}
