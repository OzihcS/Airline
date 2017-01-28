package ua.nure.piontkovskyi.SummaryTask4.filter.security;

import ua.nure.piontkovskyi.SummaryTask4.entity.User;

/**
 * Defines whether the current users can access the specified path.
 */
public interface AccessConfiguration {

    /**
     * Defines whether the current users can access the specified path
     *
     * @param path path to access
     * @return {@code true} if can, {@code false} otherwise
     */
    Tuple<Boolean, Boolean> isAllowed(String path, User user);

    /**
     * Gets action, where the user will be redirected to in case of denied access.
     *
     * @return action, where the user will be redirected to in case of denied access
     */
    String getRedirect();

}
