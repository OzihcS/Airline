package validator;


import java.util.Map;

/**
 * Checks object for validity.
 */
public interface Validator {

    /**
     * Defines if Validator found invalid data in specified object.
     *
     * @return {@code false} if the object is valid, and {@code true} if it is not
     */
    boolean hasErrors();

    /**
     * Gets messages of validity issues
     *
     * @return validity errors messages {@link Map}
     */
    Map<String, String> getMessages();

    /**
     * Adds a new message and its identifier.
     *
     * @param key     key of error message
     * @param message error message text
     */
    void putIssue(String key, String message);
}
