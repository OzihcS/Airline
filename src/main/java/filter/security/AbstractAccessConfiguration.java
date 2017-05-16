package filter.security;

import entity.User;
import entity.enums.Role;

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