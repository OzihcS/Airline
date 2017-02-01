package ua.nure.piontkovskyi.SummaryTask4.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Contains values from specified bundle in all accessible locales.
 */
public class Locale {

    private static final Logger LOGGER = LoggerFactory.getLogger(Locale.class);
    private Map<String, ResourceBundle> resBundles;

    /**
     * Creates a new interpreter.
     *
     * @param bundlePath path to bundle that contains translations to be interpreted.
     * @param locales    array of locales
     */
    public Locale(String bundlePath, String[] locales) {
        resBundles = new HashMap<>();
        for (String locale : locales) {
            ResourceBundle resBundle = ResourceBundle.getBundle(bundlePath, new java.util.Locale(locale));
            LOGGER.debug("Bundle for path {} and locale {}", bundlePath, locale);
            resBundles.put(locale, resBundle);
        }
    }

    /**
     * Gets an interpretation for the specified {@code key} on specified locale.
     *
     * @param key    key find
     * @param locale current locale
     * @return found interpretation
     */
    public String translate(String key, String locale) {
        LOGGER.debug("{} translation {}", key, locale);
        ResourceBundle bundle = resBundles.get(locale);
        LOGGER.debug((bundle != null) ? bundle.getLocale().toString() : "bundle for {} is empty" , locale);

        if (bundle == null) {
            return null;
        }

        return bundle.getString(key);
    }
}
