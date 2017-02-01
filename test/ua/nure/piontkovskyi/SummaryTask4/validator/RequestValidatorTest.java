package ua.nure.piontkovskyi.SummaryTask4.validator;

import junit.framework.TestCase;
import org.junit.Test;
import ua.nure.piontkovskyi.SummaryTask4.entity.Request;
import ua.nure.piontkovskyi.SummaryTask4.util.constants.Constants;

import java.util.Locale;
import java.util.ResourceBundle;


public class RequestValidatorTest extends TestCase {


    @Test
    public void testNull() {
        Validator validator = new RequestValidator(null, "ru");
        assertFalse(validator.hasErrors());
    }

    @Test
    public void testTitle() {
        Request request = new Request();
        Validator validator = new RequestValidator(request, "ru");
        ((RequestValidator) validator).setBundle(ResourceBundle.getBundle("messages", new Locale("ru")));

        assertNotNull(validator.getMessages().get(Constants.Attributes.TITLE));

        request.setTitle("4554");
        validator = new RequestValidator(request, "ru");
        assertNotNull(validator.getMessages().get(Constants.Attributes.TITLE));

        request.setTitle("a");
        validator = new RequestValidator(request, "ru");
        assertNotNull(validator.getMessages().get(Constants.Attributes.TITLE));

        request.setTitle("Title");
        validator = new RequestValidator(request, "ru");
        assertNull(validator.getMessages().get(Constants.Attributes.TITLE));
    }

    public void testMessage(){
        Request request = new Request();
        Validator validator = new RequestValidator(request, "ru");
        ((RequestValidator) validator).setBundle(ResourceBundle.getBundle("messages", new Locale("ru")));

        assertNotNull(validator.getMessages().get(Constants.Attributes.MESSAGE));

        request.setMessage("4554");
        validator = new RequestValidator(request, "ru");
        assertNotNull(validator.getMessages().get(Constants.Attributes.MESSAGE));

        request.setMessage("a");
        validator = new RequestValidator(request, "ru");
        assertNotNull(validator.getMessages().get(Constants.Attributes.MESSAGE));

        request.setMessage("Title");
        validator = new RequestValidator(request, "ru");
        assertNull(validator.getMessages().get(Constants.Attributes.MESSAGE));
    }
}