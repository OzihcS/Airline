package ua.nure.piontkovskyi.SummaryTask4.model.enums;


public enum StaffRole {

    PILOT, NAVIGATOR, RADIOMAN, STEWARDESS;

    public static int getRoleId(StaffRole role) {
        switch (role) {
            case PILOT: {
                return 1;
            }
            case NAVIGATOR: {
                return 2;
            }
            case RADIOMAN: {
                return 3;
            }
            case STEWARDESS: {
                return 4;
            }
        }
        return 0;
    }
}

