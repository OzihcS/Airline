package ua.nure.piontkovskyi.SummaryTask4.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.piontkovskyi.SummaryTask4.util.constants.Settings;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Class that provides basic functionality for all {@link Validator}s.
 */
public abstract class AbstractValidator implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractValidator.class);
    private ResourceBundle bundle;

    private Map<String, String> errMessages;
    private String locale;

    protected AbstractValidator(String locale) {
        this.locale = locale;
        bundle = ResourceBundle.getBundle(Settings.BUNDLE_PATH, new Locale(locale));
        errMessages = new HashMap<>();
    }

    @Override
    public boolean hasErrors() {
        return errMessages.size() != 0;
    }

    @Override
    public Map<String, String> getMessages() {
        return errMessages;
    }

    public ResourceBundle getBundle() {
        return bundle;
    }

    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    @Override
    public void putIssue(String key, String message) {
        if (key == null || message == null) {
            return;
        }
        LOGGER.debug("{} translation {}", key, locale);
        LOGGER.debug((bundle != null) ? "bundle {}" : "bundle for {} is empty", locale);

        if (bundle != null) {
            LOGGER.debug("Put message {}", message);
            errMessages.put(key, bundle.getString(message));
        }
    }

}

