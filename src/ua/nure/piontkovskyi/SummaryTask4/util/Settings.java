package ua.nure.piontkovskyi.SummaryTask4.util;

import org.apache.log4j.Logger;
import ua.nure.piontkovskyi.SummaryTask4.exception.FileProcessingException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Settings {

    private static final Logger LOGGER = Logger.getLogger(Settings.class);

    private static final String EHCACHE_CONFIG = "S:\\idea\\SummaryTask4\\src\\ua\\nure\\piontkovskyi\\SummaryTask4\\settings\\ehcache\\ehcache.xml";
    private static final String SQL_FILE = "/ua/nure/piontkovskyi/SummaryTask4/settings/query.properties";
    private static final String DB_CONFIG = "/ua/nure/piontkovskyi/SummaryTask4/settings/db.properties";
    private static final String CONFIG_FILE = "/ua/nure/piontkovskyi/SummaryTask4/settings/config.properties";

    private static final String UPLOAD_DIRECTORY;


    static {
        try (InputStream resource = Settings.class.getResourceAsStream(CONFIG_FILE)) {
            Properties prop = new Properties();
            prop.load(resource);
            UPLOAD_DIRECTORY = prop.getProperty("upload.dir");
        } catch (IOException e) {
            LOGGER.error("Cannot load file: '{}'" + CONFIG_FILE);
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
