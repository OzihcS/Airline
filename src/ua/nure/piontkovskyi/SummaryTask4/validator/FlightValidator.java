package ua.nure.piontkovskyi.SummaryTask4.validator;

import ua.nure.piontkovskyi.SummaryTask4.entity.Flight;
import ua.nure.piontkovskyi.SummaryTask4.util.constants.Constants;

import java.util.regex.Pattern;

/**
 * Performs validation of {@link Flight} objects.
 */
public class FlightValidator extends AbstractValidator {

    private static final Pattern TEMPLATE = Pattern.compile("[a-zA-zа-яА-Я- ]+",
            Pattern.CASE_INSENSITIVE);
    private static final int MIN_LENGTH = 3;
    private static final int MAX_LENGTH = 25;

    /**
     * Instantiates a new UserValidator that validates only user's login and password immediately.
     *
     * @param flight flight to validate
     * @param locale current locale value
     */
    public FlightValidator(Flight flight, String locale) {
        super(locale);
        if (flight == null) {
            return;
        }
        putIssue("name", validateName(flight.getName()));
        putIssue("locations", validateLocations(flight.getDepartureLocation(), flight.getArriveLocation()));
    }

    /**
     * Instantiates a new UserValidator that validates only user's login and password immediately.
     *
     * @param name              name to validate
     * @param departureLocation departureLocation to validate
     * @param arriveLocation    arriveLocation to validate
     * @param locale            current locale value
     */
    public FlightValidator(String name, String departureLocation, String arriveLocation, String locale) {
        super(locale);
        putIssue("name", validateName(name));
        putIssue("locations", validateLocations(departureLocation, arriveLocation));
    }

    private String validateName(String name) {
        if (name == null || name.isEmpty()) {
            return Constants.Validation.CANT_BE_EMPTY;
        }
        if (!TEMPLATE.matcher(name).matches()) {
            return Constants.Validation.LETTERS_ONLY;
        }
        if (name.length() < MIN_LENGTH || name.length() > MAX_LENGTH) {
            return Constants.Validation.LEN_3_TO_25;
        }

        return null;
    }

    private String validateLocations(String departure, String arrive) {
        if (departure == null || departure.isEmpty() || arrive == null || arrive.isEmpty()) {
            return Constants.Validation.CANT_BE_EMPTY;
        }
        if (!TEMPLATE.matcher(departure).matches() || !TEMPLATE.matcher(arrive).matches()) {
            return Constants.Validation.LETTERS_ONLY;
        }
        if (departure.equals(arrive)) {
            return Constants.Validation.CANT_BE_THE_SAME;
        }
        if (departure.length() < MIN_LENGTH || departure.length() > MAX_LENGTH ||
                arrive.length() < MIN_LENGTH || arrive.length() > MAX_LENGTH) {
            return Constants.Validation.LEN_3_TO_25;
        }
        return null;
    }

}
