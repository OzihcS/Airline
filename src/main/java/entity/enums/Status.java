package entity.enums;

/**
 * Enum which contain flight status
 */
public enum Status {

    UNCONFIRMED, IN_PROGRESS, FINISHED;

    public static int index(Status status) {
        switch (status) {
            case IN_PROGRESS: {
                return 1;
            }
            case FINISHED: {
                return 2;
            }
        }
        return 0;
    }

}
