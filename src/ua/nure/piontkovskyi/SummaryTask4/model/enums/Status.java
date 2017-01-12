package ua.nure.piontkovskyi.SummaryTask4.model.enums;


public enum Status {

    UNCONFIRMED, IN_PROGRESS, FINISHED;

    public static Status getStatus(String s) {
        switch (s) {
            case "UNCONFIRMED": {
                return Status.UNCONFIRMED;
            }
            case "IN_PROGRESS": {
                return Status.IN_PROGRESS;
            }
            case "FINISHED": {
                return Status.FINISHED;
            }
        }
        return null;
    }
}
