package ua.nure.piontkovskyi.SummaryTask4.validator;

import ua.nure.piontkovskyi.SummaryTask4.model.Flight;
import ua.nure.piontkovskyi.SummaryTask4.util.constants.Constants;

import java.util.Date;
import java.util.regex.Pattern;

/**
 * Performs validation of {@link Flight} objects.
 */
public class FlightValidator extends AbstractValidator {

    private static final Pattern TEMPLATE = Pattern.compile("^(\\p{L}+)(,\\s\\p{L}+\\s\\p{L}\\.)?(\\s?\\p{L}+)?$",
            Pattern.CASE_INSENSITIVE);


    protected FlightValidator(Flight flight, String locale) {
        super(locale);
        putIssue("name", validateName(flight.getName()));
        putIssue("locations", validateLocations(flight.getDepartureLocation(), flight.getArriveLocation()));
    }

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
        return null;
    }

    private String validateLocations(String departure, String arrive) {
        if (departure == null || departure.isEmpty() || arrive == null || arrive.isEmpty()) {
            return Constants.Validation.CANT_BE_EMPTY;
        }
        if (!TEMPLATE.matcher(departure).matches() || !TEMPLATE.matcher(arrive).matches()) {
            return Constants.Validation.LETTERS_ONLY;
        }
        if (departure.equals(arrive)){
            return Constants.Validation.CANT_BE_THE_SAME;
        }
        return null;
    }
}