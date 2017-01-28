package ua.nure.piontkovskyi.SummaryTask4.validator;

import org.junit.Test;
import ua.nure.piontkovskyi.SummaryTask4.entity.Flight;

import java.util.Locale;
import java.util.ResourceBundle;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;

public class FlightValidatorTest {


    @Test
    public void testNull() {
        Validator validator = new FlightValidator(null, "ru");
        assertFalse(validator.hasErrors());
    }

    @Test
    public void testName() {
        Flight flight = new Flight();
        Validator validator = new FlightValidator(flight, "ru");
        ((FlightValidator) validator).setBundle(ResourceBundle.getBundle("messages", new Locale("ru")));

        assertNotNull(validator.getMessages().get("name"));
        flight.setName("");
        validator = new FlightValidator(flight, "ru");
        assertNotNull(validator.getMessages().get("name"));
        flight.setName("d");
        validator = new FlightValidator(flight, "ru");
        assertNotNull(validator.getMessages().get("name"));
        flight.setName("123");
        validator = new FlightValidator(flight, "ru");
        assertNotNull(validator.getMessages().get("name"));
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < 101; i++) {
            name.append("a");
        }
        flight.setName(name.toString());
        validator = new FlightValidator(flight, "ru");
        assertNotNull(validator.getMessages().get("name"));
        flight.setName("Ivan");
        validator = new FlightValidator(flight, "ru");
        assertNull(validator.getMessages().get("name"));
    }

    @Test
    public void testLocation() {
        Flight flight = new Flight();
        Validator validator = new FlightValidator(flight, "ru");
        ((FlightValidator) validator).setBundle(ResourceBundle.getBundle("messages", new Locale("ru")));

        assertNotNull(validator.getMessages().get("locations"));
        flight.setDepartureLocation("");
        validator = new FlightValidator(flight, "ru");
        assertNotNull(validator.getMessages().get("locations"));
        flight.setDepartureLocation("d");
        validator = new FlightValidator(flight, "ru");
        assertNotNull(validator.getMessages().get("locations"));
        flight.setDepartureLocation("123");
        validator = new FlightValidator(flight, "ru");
        assertNotNull(validator.getMessages().get("locations"));
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < 101; i++) {
            name.append("a");
        }
        flight.setDepartureLocation(name.toString());
        validator = new FlightValidator(flight, "ru");
        assertNotNull(validator.getMessages().get("locations"));
        flight.setDepartureLocation("London");
        flight.setArriveLocation("Warsaw");
        validator = new FlightValidator(flight, "ru");
        assertNull(validator.getMessages().get("locations"));
        flight.setDepartureLocation("London");
        flight.setArriveLocation("London");
        validator = new FlightValidator(flight, "en");
        assertNotNull(validator.getMessages().get("locations"));
    }

}