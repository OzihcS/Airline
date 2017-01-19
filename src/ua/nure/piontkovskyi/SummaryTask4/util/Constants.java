package ua.nure.piontkovskyi.SummaryTask4.util;


public final class Constants {

    public final class Attributes {
        public static final String CONNECTION_MANAGER = "connectionManager";
        public static final String CACHE = "ehcache";
        public static final String CURRENT_USER = "currentUser";
        public static final String DEFAULT_LOCALE = "defaultLocale";
        public static final String LOCALES = "locales";
        public static final String CURRENT_LOCALE = "currentLocale";
        public static final String SERIALIZER = "serializer";
    }

    public static final class Service {
        public static final String FILE_PROC_SERVICE = "fileProcessingService";
    }

    public static final class ServletPaths {


        public static final String LOGIN = "/log/login";
        public static final String LOGOUT = "/log/logout";

        public static final class Dispatcher {
            public static final String DISPATCHER = "/dispatcher/";

            public static final String MAIN = DISPATCHER + "home";
            public static final String FLIGHTS = DISPATCHER + "flights";
        }

        public static final class Admin {
            public static final String ADMIN = "/admin/";

            public static final String MAIN = ADMIN + "home";
        }
    }

    public static final class ROUTES {
        public static final String BUNDLE_PATH = "messages";
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
    }

    public static final class Pages {
        private static final String PREFIX = "/pages/";

        public static final String LOGIN = "/login.jsp";

        public static final class Dispatcher {
            private static final String USER_PREFIX = PREFIX + "dispatcher/";
            public static final String MAIN = USER_PREFIX + "home.jsp";
        }

        public static final class Admin {
            private static final String USER_PREFIX = PREFIX + "admin/";

            public static final String MAIN = USER_PREFIX + "home.jsp";
        }
    }
}
