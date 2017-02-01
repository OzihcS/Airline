package ua.nure.piontkovskyi.SummaryTask4.validator;

import junit.framework.TestCase;
import org.junit.Test;
import ua.nure.piontkovskyi.SummaryTask4.util.constants.Constants;

import java.util.Locale;
import java.util.ResourceBundle;

public class PickValidatorTest extends TestCase {

    @Test
    public void testNull() {
        Validator validator = new PickValidator(null, null, null, null,
                null, "ru");
        assertFalse(validator.hasErrors());
    }

    @Test
    public void testId() {
        Validator validator = new PickValidator("2", null, null,
                null, null, "ru");
        ((PickValidator) validator).setBundle(ResourceBundle.getBundle("messages", new Locale("ru")));

        assertNull(validator.getMessages().get("id"));
        validator = new PickValidator("asd", null, null, null, null, "ru");
        assertNotNull(validator.getMessages().get("id"));
    }

    @Test
    public void testLocation() {
        Validator validator = new PickValidator("2", null, null,
                null, null, "ru");
        ((PickValidator) validator).setBundle(ResourceBundle.getBundle("messages", new Locale("ru")));

        assertNull(validator.getMessages().get(Constants.Attributes.DEPARTURE_LOCATION));
        assertNull(validator.getMessages().get(Constants.Attributes.ARRIVE_LOCATION));

        validator = new PickValidator("2", "London", "Берлин",
                null, null, "ru");
        assertNull(validator.getMessages().get(Constants.Attributes.DEPARTURE_LOCATION));
        assertNull(validator.getMessages().get(Constants.Attributes.ARRIVE_LOCATION));

        validator = new PickValidator("2", "1232", "Бе1321рлин",
                null, null, "ru");

        assertNotNull(validator.getMessages().get(Constants.Attributes.DEPARTURE_LOCATION));
        assertNotNull(validator.getMessages().get(Constants.Attributes.ARRIVE_LOCATION));
    }


    @Test
    public void testDate() {
        Validator validator = new PickValidator("2", null, null,
                null, null, "ru");
        ((PickValidator) validator).setBundle(ResourceBundle.getBundle("messages", new Locale("ru")));

        assertNull(validator.getMessages().get(Constants.Attributes.DEPARTURE_DATE));
        assertNull(validator.getMessages().get(Constants.Attributes.ARRIVE_DATE));

        validator = new PickValidator("2", "London", "Берлин",
                "2017-02-12", "2017-02-13", "ru");
        assertNull(validator.getMessages().get(Constants.Attributes.DEPARTURE_DATE));
        assertNull(validator.getMessages().get(Constants.Attributes.ARRIVE_DATE));

        validator = new PickValidator("2", "1232", "Бе1321рлин",
                "20.02.12", "21.02.12", "ru");

        assertNotNull(validator.getMessages().get(Constants.Attributes.DEPARTURE_LOCATION));
        assertNotNull(validator.getMessages().get(Constants.Attributes.ARRIVE_LOCATION));

        validator = new PickValidator("2", "1232", "Бе1321рлин",
                "asd.02.asd", "asd.asd.12", "ru");

        assertNotNull(validator.getMessages().get(Constants.Attributes.DEPARTURE_LOCATION));
        assertNotNull(validator.getMessages().get(Constants.Attributes.ARRIVE_LOCATION));

    }

}