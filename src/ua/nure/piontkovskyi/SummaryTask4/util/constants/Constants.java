package ua.nure.piontkovskyi.SummaryTask4.util.constants;


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
    }

    public static final class Service {
        public static final String FILE_PROC_SERVICE = "fileProcessingService";
    }

    public static final class ServletPaths {


        public static final String LOGIN = "/log/login";
        public static final String LOGOUT = "/log/logout";
        public static final String ERROR = "/error";

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
        public static final String DATE_EROR = "validator.dateError";
    }

    public static final class Pages {
        private static final String PREFIX = "/pages/";

        public static final String LOGIN = "/login.jsp";

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
