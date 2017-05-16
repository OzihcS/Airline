package db;

import annotation.Repository;
import exception.FileProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.constants.Settings;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Contains all queries  used in repositories and stored in specified properties file.
 *
 * @see Repository
 */
public final class Query {

    private static final String SQL_FILE = Settings.getSqlFile();
    private static final Logger LOGGER = LoggerFactory.getLogger(Query.class);
    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream resource = Query.class.getResourceAsStream(SQL_FILE)) {
            PROPERTIES.load(resource);
        } catch (IOException e) {
            LOGGER.error("Cannot load file: '{}'", SQL_FILE);
            throw new FileProcessingException("Cannot load file: '" + SQL_FILE + "'", e);
        }
    }

    /**
     * Gets a query by {@code key}.
     *
     * @param key key of the query to find
     * @return found query
     */
    public static String get(String key) {
        System.out.println(PROPERTIES.getProperty(key));
        return PROPERTIES.getProperty(key);
    }
}
