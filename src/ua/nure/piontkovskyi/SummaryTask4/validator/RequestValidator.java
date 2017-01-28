package ua.nure.piontkovskyi.SummaryTask4.validator;


import ua.nure.piontkovskyi.SummaryTask4.entity.Request;
import ua.nure.piontkovskyi.SummaryTask4.util.constants.Constants;

import java.util.regex.Pattern;

public class RequestValidator extends AbstractValidator {

    private static final Pattern TEMPLATE = Pattern.compile("[a-zA-zа-яА-Я- ]+",
            Pattern.CASE_INSENSITIVE);

    /**
     * Instantiates a new UserValidator for validating {@link Request}s.
     *
     * @param request request that needs validation
     * @param locale  current locale value
     */
    public RequestValidator(Request request, String locale) {
        super(locale);
        if (request == null) {
            return;
        }
        putIssue("title", validateTitle(request.getTitle()));
        putIssue("message", validateMessage(request.getMessage()));
    }

    private String validateTitle(String title) {
        if (title == null || title.isEmpty()) {
            return Constants.Validation.CANT_BE_EMPTY;
        }
        if (!TEMPLATE.matcher(title).matches()) {
            return Constants.Validation.LETTERS_ONLY;
        }
        if (title.length() < 3 || title.length() > 25) {
            return Constants.Validation.LEN_3_TO_25;
        }
        return null;
    }

    private String validateMessage(String message) {
        if (message == null || message.isEmpty()) {
            return Constants.Validation.CANT_BE_EMPTY;
        }
        if (!TEMPLATE.matcher(message).matches()) {
            return Constants.Validation.LETTERS_ONLY;
        }
        if (message.length() < 3 || message.length() > 100) {
            return Constants.Validation.LEN_3_TO_100;
        }
        return null;
    }

}
