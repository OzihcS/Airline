package ua.nure.piontkovskyi.SummaryTask4.validator;

import ua.nure.piontkovskyi.SummaryTask4.util.constants.Constants;

import java.util.regex.Pattern;

/**
 * Performs validation of pick params of flight.
 */
public class PickValidator extends AbstractValidator {

    private static final Pattern ID_TEMPLATE = Pattern.compile(
            "\\d+$", Pattern.CASE_INSENSITIVE);
    private static final Pattern LOCATION_TEMPLATE = Pattern.compile("[a-zA-zа-яА-Я-]+",
            Pattern.CASE_INSENSITIVE);
    private static final Pattern DATE_TEMPLATE = Pattern.compile("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$",
            Pattern.CASE_INSENSITIVE);
    private static final int MIN_LENGTH = 3;
    private static final int MAX_LENGTH = 25;
    /**
     * Instantiates a new UserValidator that validates only user's login and password immediately.
     *
     * @param id     id to validate
     * @param locale current locale value
     */
    public PickValidator(String id, String from, String to, String departure, String arrive, String locale) {
        super(locale);
        putIssue(Constants.Attributes.ID, validateId(id));
        putIssue(Constants.Attributes.DEPARTURE_LOCATION, validateLocation(from));
        putIssue(Constants.Attributes.ARRIVE_LOCATION, validateLocation(to));
        putIssue(Constants.Attributes.DEPARTURE_DATE, validateDate(departure));
        putIssue(Constants.Attributes.ARRIVE_DATE, validateDate(arrive));
    }

    private String validateId(String id) {
        if (id != null && !id.isEmpty()) {
            if (!ID_TEMPLATE.matcher(id).matches()) {
                return Constants.Validation.NUMBERS_ONLY;
            }
        }
        return null;
    }

    private String validateLocation(String location) {
        if (location != null && !location.isEmpty()) {
            if (!LOCATION_TEMPLATE.matcher(location).matches()) {
                return Constants.Validation.LETTERS_ONLY;
            }
            if (location.length() < MIN_LENGTH || location.length() > MAX_LENGTH) {
                return Constants.Validation.LEN_3_TO_25;
            }
        }
        return null;
    }

    private String validateDate(String date) {
        if (date != null && !date.isEmpty()) {
            if (!DATE_TEMPLATE.matcher(date).matches()) {
                return Constants.Validation.DATE_FORMAT;
            }
        }
        return null;
    }

}
