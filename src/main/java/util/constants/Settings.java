package util.constants;

import exception.FileProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * Some base setting
 */
public class Settings {

    private static final Logger LOGGER = LoggerFactory.getLogger(Settings.class);

    private static final String EHCACHE_CONFIG = "S:\\Airline\\src\\main\\resources\\ehcache\\ehcache.xml";
    private static final String SQL_FILE = "/properties/query.properties";
    private static final String DB_CONFIG = "/properties/db.properties";
    private static final String CONFIG_FILE = "/properties/config.properties";
    public static final String BUNDLE_PATH = "messages";

    private static final String UPLOAD_DIRECTORY;


    static {
        try (InputStream resource = Settings.class.getResourceAsStream(CONFIG_FILE)) {
            Properties prop = new Properties();
            prop.load(resource);
            UPLOAD_DIRECTORY = prop.getProperty("upload.dir");
        } catch (IOException e) {
            LOGGER.error("Cannot load file: {}" + CONFIG_FILE);
            throw new FileProcessingException("Cannot load file: '" + CONFIG_FILE + "'", e);
        }
    }

    public static String getSqlFile() {
        return SQL_FILE;
    }

    public static String getDatabaseConfigFile() {
        return DB_CONFIG;
    }

    public static String getEhcacheConfig() {
        return EHCACHE_CONFIG;
    }

    public static String getUploadDirectory() {
        return UPLOAD_DIRECTORY;
    }
}
