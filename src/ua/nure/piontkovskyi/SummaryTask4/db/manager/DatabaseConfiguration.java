package ua.nure.piontkovskyi.SummaryTask4.db.manager;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.piontkovskyi.SummaryTask4.exception.FileProcessingException;
import ua.nure.piontkovskyi.SummaryTask4.util.constants.Settings;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * DatabaseConfiguration class.
 */
public final class DatabaseConfiguration {

    private static final String DATABASE_CONFIG_FILE = Settings.getDatabaseConfigFile();
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConfiguration.class);
    private static final String SERVER;
    private static final String PORT;
    private static final String DATABASE;
    private static final String RDBMS;
    private static final String USER;
    private static final String PASSWORD;
    private static final String TRANSACTION_ISOLATION;
    private static final String ENCODING;
    private static final String AUTO_RECONNECT;
    private static final String CONNECTION_URL;

    static {
        try (InputStream resource = DatabaseConfiguration.class.getResourceAsStream(DATABASE_CONFIG_FILE)) {
            Properties properties = new Properties();
            properties.load(resource);
            SERVER = properties.getProperty("server");
            PORT = properties.getProperty("port");
            DATABASE = properties.getProperty("database");
            RDBMS = properties.getProperty("rdbms");
            USER = properties.getProperty("user");
            PASSWORD = properties.getProperty("password");
            TRANSACTION_ISOLATION = properties.getProperty("transactionIsolation");
            ENCODING = properties.getProperty("encoding");
            AUTO_RECONNECT = properties.getProperty("autoReconnect");
            CONNECTION_URL = defineConnectionUrl();
        } catch (IOException e) {
            LOGGER.error("Cannot load config file: '{}'", DATABASE_CONFIG_FILE);
            throw new FileProcessingException("Cannot load config file: '" + DATABASE_CONFIG_FILE + "'", e);
        }
    }

    public static String getServer() {
        return SERVER;
    }

    public static String getPort() {
        return PORT;
    }

    public static String getDatabase() {
        return DATABASE;
    }

    public static String getRdbms() {
        return RDBMS;
    }

    public static String getUser() {
        return USER;
    }

    public static String getPassword() {
        return PASSWORD;
    }

    public static String getTransactionIsolation() {
        return TRANSACTION_ISOLATION;
    }

    private static String defineConnectionUrl() {
        return "jdbc:" + RDBMS + "://" + SERVER + ":" + PORT + "/"
                + DATABASE + "?characterEncoding=" + ENCODING + "&autoReconnect=" + AUTO_RECONNECT;

    }

    private DatabaseConfiguration() {
    }


}
