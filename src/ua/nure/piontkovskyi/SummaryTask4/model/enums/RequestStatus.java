package ua.nure.piontkovskyi.SummaryTask4.model.enums;

public enum RequestStatus {

    UNCONFIRMED, EXECUTED, REJECTED;

    public static int index(RequestStatus status) {
        switch (status) {
            case EXECUTED: {
                return 1;
            }
            case REJECTED: {
                return 2;
            }
        }
        return 0;
    }
}
