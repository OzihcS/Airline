package entity.enums;

/**
 * Enum which contain user role
 */
public enum Role {

    ADMINISTRATOR, DISPATCHER;

    public static int getRoleId(Role role) {
        switch (role) {
            case ADMINISTRATOR: {
                return 1;
            }
            case DISPATCHER: {
                return 2;
            }
        }
        return 0;
    }
}

