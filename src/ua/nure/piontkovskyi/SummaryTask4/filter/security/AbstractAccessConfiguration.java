package ua.nure.piontkovskyi.SummaryTask4.filter.security;

import ua.nure.piontkovskyi.SummaryTask4.entity.User;
import ua.nure.piontkovskyi.SummaryTask4.entity.enums.Role;

/**
 * Provides basic functionality of {@link AccessConfiguration} interface.
 */
public abstract class AbstractAccessConfiguration implements AccessConfiguration {

    private Role userRole;

    protected AbstractAccessConfiguration(Role userRole) {
        this.userRole = userRole;
    }

    protected boolean userRoleMatch(User user) {
        if (user == null) {
            return false;
        }
        if (user.getRole().equals(userRole)) {
            return true;
        }
        return false;
    }
}