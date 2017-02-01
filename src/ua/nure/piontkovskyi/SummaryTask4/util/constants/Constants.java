package ua.nure.piontkovskyi.SummaryTask4.util.constants;

/**
 * Constants
 */
public final class Constants {

    public final class Attributes {
        public static final String CONNECTION_MANAGER = "connectionManager";
        public static final String CACHE = "ehcache";
        public static final String CURRENT_USER = "currentUser";
        public static final String DEFAULT_LOCALE = "defaultLocale";
        public static final String LOCALES = "locales";
        public static final String CURRENT_LOCALE = "currentLocale";
        public static final String SERIALIZER = "serializer";
        public static final String LOGIN = "login";
        public static final String PASSWORD = "password";
        public static final String LANG = "lang";
        public static final String USERS = "users";
        public static final java.lang.String NAME = "name";
        public static final java.lang.String CONFIRM_PASSWORD = "confirmPassword";
        public static final java.lang.String ROLE = "role";
        public static final String REQUESTS = "requests";
        public static final String ADMINS = "admins";
        public static final String FLIGHTS = "flights";
        public static final java.lang.String ID = "id";
        public static final java.lang.String STATUS = "status";
        public static final String PILOT = "pilot";
        public static final String RADIOMAN = "radioman";
        public static final String NAVIGATOR = "navigator";
        public static final String FIRST_STEWARDESS = "firstStewardess";
        public static final String SECOND_STEWARDESS = "secondStewardess";
        public static final String THIRD_STEWARDESS = "thirdStewardess";
        public static final String FLIGHT_ID = "flightId";
        public static final String FROM_ID = "fromId";
        public static final String TO_ID = "toId";
        public static final String MESSAGE = "message";
        public static final java.lang.String TITLE = "title";
        public static final java.lang.String DEPARTURE_LOCATION = "departureLocation";
        public static final java.lang.String ARRIVE_LOCATION = "arriveLocation";
        public static final String USER = "user";
        public static final String DEPARTURE_DATE = "departureDate";
        public static final String ARRIVE_DATE = "arriveDate";
        public static final String DATE = "date";
        public static final String FLIGHT = "flight";
        public static final String STEWARDESSES = "stewardesses";
        public static final String PILOTS = PILOT + "s";
        public static final String RADIOMEN = "radiomen";
        public static final String NAVIGATORS = NAVIGATOR + "s";
        public static final String NEW_LOCALE = "newLocale";
        public static final java.lang.String CAPTCHA = "captcha";
    }

    public static final class Service {
        public static final String FILE_PROC_SERVICE = "fileProcessingService";
    }

    public static final class ServletPaths {

        public static final String LOGIN = "/log/login";
        public static final String LOGOUT = "/log/logout";
        public static final String ERROR = "/log/error";
        public static final String LOCALE = "/log/locale";
        public static final String CAPTCHA = "/log/captcha";

        public static final class Dispatcher {
            public static final String DISPATCHER = "/dispatcher/";

            public static final String MAIN = DISPATCHER + "home";
            public static final String FLIGHTS = DISPATCHER + "flights";
            public static final String CREATE_BRIGADE = DISPATCHER + "newBrigade";
            public static final String NEW_REQUEST = DISPATCHER + "newRequest";
            public static final String CHANGE_STATUS = DISPATCHER + "changeStatus";
        }

        public static final class Admin {
            public static final String ADMIN = "/admin/";

            public static final String MAIN = ADMIN + "home";
            public static final String NEW_FLIGHT = ADMIN + "add";
            public static final String FLIGHT_LIST = ADMIN + "flights";
            public static final String USER_LIST = ADMIN + "users";
            public static final String NEW_USER = ADMIN + "newUser";
            public static final String DELETE_USER = ADMIN + "delete";
            public static final String DELETE_FLIGHT = ADMIN + "deleteFlight";
            public static final String PICK_UP = ADMIN + "pickUp";
            public static final String EDIT_USER = ADMIN + "editUser";
            public static final String EDIT_FLIGHT = ADMIN + "editFlight";
            public static final String DELETE_REQUEST = ADMIN + "deleteRequest";
        }
    }

    /**
     * validation constants.
     */
    public static final class Validation {
        public static final String LEN_3_TO_100 = "validator.lengthFrom3to100";
        public static final String LEN_5_TO_100 = "validator.lengthFrom5to100";
        public static final String LETTERS_ONLY = "validator.lettersOnly";
        public static final String PATT_MATCH = "validator.loginPatternMatch";
        public static final String NO_WHITESPACES = "validator.noWhitespaces";
        public static final String CANT_BE_EMPTY = "validator.cannotBeEmpty";
        public static final String LEN_4_TO_100 = "validator.lengthFrom4to100";
        public static final String CANT_BE_THE_SAME = "validator.cannotBeTheSame";
        public static final String DATE_ERROR = "validator.dateError";
        public static final String LEN_3_TO_25 = "validator.lengthFrom3to25";
        public static final String NUMBERS_ONLY = "validator.numbersOnly";
        public static final String DATE_FORMAT = "validator.invalidDate";
        public static final String DIFFERENT_PASSWORDS = "validator.differentPasswords";
        public static final String LEN_3_TO_45 = "validator.lengthFrom4to45";
        public static final String LEN_4_TO_10 = "validator.lengthFrom4to10";
        public static final String LEN_5_TO_15 = "validator.lengthFrom4to15";
        public static final String CANT_DELETE_YOURSELF = "validator.cant.delete.yourself";
    }

    public static final class Pages {
        private static final String PREFIX = "/WEB-INF/pages/";

        public static final String LOGIN = PREFIX + "/login.jsp";
        public static final String ERROR = PREFIX + "error.jsp";

        public static final class Dispatcher {
            private static final String USER_PREFIX = PREFIX + "dispatcher/";
            public static final String MAIN = USER_PREFIX + "home.jsp";
            public static final String CREATE_BRIGADE = USER_PREFIX + "createBrigade.jsp";
            public static final String NEW_REQUEST = USER_PREFIX + "newRequest.jsp";

        }

        public static final class Admin {
            private static final String USER_PREFIX = PREFIX + "admin/";

            public static final String MAIN = USER_PREFIX + "home.jsp";
            public static final String ADD = USER_PREFIX + "add.jsp";
            public static final String USERS = USER_PREFIX + "users.jsp";
            public static final String ADD_USER = USER_PREFIX + "addUser.jsp";
            public static final String FLIGHTS = USER_PREFIX + "flights.jsp";
            public static final String EDIT_USER = USER_PREFIX + "editUser.jsp";
            public static final String EDIT_FLIGHT = USER_PREFIX + "editFlight.jsp";
        }
    }
}
