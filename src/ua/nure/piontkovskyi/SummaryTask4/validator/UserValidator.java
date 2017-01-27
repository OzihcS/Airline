package ua.nure.piontkovskyi.SummaryTask4.validator;

import ua.nure.piontkovskyi.SummaryTask4.model.User;

import java.util.regex.Pattern;

import ua.nure.piontkovskyi.SummaryTask4.model.enums.Role;
import ua.nure.piontkovskyi.SummaryTask4.util.constants.Constants.Validation;

/**
 * Performs validation of {@link User} objects.
 */
public class UserValidator extends AbstractValidator {

    private static final Pattern LOGIN_TEMPLATE = Pattern.compile(
            "^[A-ZА-Яа-яa-z0-9_-]+$", Pattern.CASE_INSENSITIVE);
    private static final Pattern NAME_TEMPLATE = Pattern.compile("^(\\p{L}+)(,\\s\\p{L}+\\s\\p{L}\\.)?(\\s?\\p{L}+)?$",
            Pattern.CASE_INSENSITIVE);


    /**
     * Instantiates a new UserValidator for validating {@link User}s.
     *
     * @param user   user that needs validation
     * @param locale current locale value
     */
    public UserValidator(User user, String locale) {
        super(locale);
        if (user == null) {
            return;
        }
        putIssue("name", validateName(user.getName()));
        putIssue("login", validateLogin(user.getLogin()));
        putIssue("password", validatePassword(user.getPassword()));
        putIssue("roles", validateRoles(user.getRole()));
    }


    /**
     * Instantiates a new UserValidator that validates only user's login and password immediately.
     *
     * @param login    login to validate
     * @param password password to validate
     * @param locale   current locale value
     */
    public UserValidator(String login, String password, String locale) {
        super(locale);
        putIssue("login", validateLogin(login));
        putIssue("password", validatePassword(password));
    }

    /**
     * Instantiates a new UserValidator that validates only user's login and password immediately.
     *
     * @param name     name to validate
     * @param login    login to validate
     * @param password password to validate
     * @param locale   current locale value
     */
    public UserValidator(String name, String login, String password, String locale) {
        super(locale);
        putIssue("name", validateName(name));
        putIssue("login", validateLogin(login));
        putIssue("password", validatePassword(password));
    }

    public UserValidator(String locale) {
        super(locale);
    }

    private String validateName(String name) {
        if (name == null || name.isEmpty()) {
            return Validation.CANT_BE_EMPTY;
        }
        if (!NAME_TEMPLATE.matcher(name).matches()) {
            return Validation.LETTERS_ONLY;
        }
        if (name.length() < 3 || name.length() > 100) {
            return Validation.LEN_3_TO_100;
        }
        return null;
    }

    private String validateRoles(Role role) {
        if (role == null) {
            return Validation.CANT_BE_EMPTY;
        }
        return null;
    }


    private String validateLogin(String login) {
        if (login == null || login.isEmpty()) {
            return Validation.CANT_BE_EMPTY;
        }
        if (!LOGIN_TEMPLATE.matcher(login).matches()) {
            return Validation.PATT_MATCH;
        }
        if (login.length() < 4 || login.length() > 100) {
            return Validation.LEN_4_TO_100;
        }
        return null;
    }

    private String validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            return Validation.CANT_BE_EMPTY;
        }
        if (password.contains(" ")) {
            return Validation.NO_WHITESPACES;
        }
        if (password.length() < 5 || password.length() > 100) {
            return Validation.LEN_5_TO_100;
        }
        return null;
    }

}
