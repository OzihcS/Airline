package ua.nure.piontkovskyi.SummaryTask4.validator;

import org.junit.Test;
import ua.nure.piontkovskyi.SummaryTask4.entity.User;
import ua.nure.piontkovskyi.SummaryTask4.util.constants.Constants;

import java.util.Locale;
import java.util.ResourceBundle;

import static junit.framework.TestCase.*;

public class UserValidatorTest {

    @Test
    public void testNull() {
        Validator validator = new UserValidator(null, "ru");
        assertFalse(validator.hasErrors());
    }

    @Test
    public void testLastName() {
        User user = new User();
        Validator validator = new UserValidator(user, "ru");
        ((UserValidator) validator).setBundle(ResourceBundle.getBundle("messages", new Locale("ru")));

        assertNotNull(validator.getMessages().get(Constants.Attributes.NAME));
        user.setName("");
        validator = new UserValidator(user, "ru");
        assertNotNull(validator.getMessages().get(Constants.Attributes.NAME));
        user.setName("d");
        validator = new UserValidator(user, "ru");
        assertNotNull(validator.getMessages().get(Constants.Attributes.NAME));
        user.setName("123");
        validator = new UserValidator(user, "ru");
        assertNotNull(validator.getMessages().get(Constants.Attributes.NAME));
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < 101; i++) {
            name.append("a");
        }
        user.setName(name.toString());
        validator = new UserValidator(user, "ru");
        assertNotNull(validator.getMessages().get(Constants.Attributes.NAME));
        user.setName("Ivan");
        validator = new UserValidator(user, "ru");
        assertNull(validator.getMessages().get(Constants.Attributes.NAME));
    }

    @Test
    public void validateLogin() {
        Validator validator = new UserValidator(null, "en");
        ((UserValidator) validator).setBundle(ResourceBundle.getBundle("messages", new Locale("en")));

        assertNull(validator.getMessages().get(Constants.Attributes.LOGIN));
        String login = "";
        validator = new UserValidator(login, null, "en");
        assertNotNull(validator.getMessages().get(Constants.Attributes.LOGIN));
        login = "d";
        validator = new UserValidator(login, null, "en");
        assertNotNull(validator.getMessages().get(Constants.Attributes.LOGIN));
        StringBuilder loginString = new StringBuilder();
        for (int i = 0; i < 101; i++) {
            loginString.append("a");
        }
        validator = new UserValidator(loginString.toString(), null, "en");
        assertNotNull(validator.getMessages().get(Constants.Attributes.LOGIN));
        login = "John 95";
        validator = new UserValidator(login, null, "en");
        assertNotNull(validator.getMessages().get(Constants.Attributes.LOGIN));
        login = "John95";
        validator = new UserValidator(login, null, "en");
        assertNull(validator.getMessages().get(Constants.Attributes.LOGIN));
    }

    @Test
    public void testPassword() {
        Validator validator = new UserValidator(null, null, "en");
        ((UserValidator) validator).setBundle(ResourceBundle.getBundle("messages", new Locale("en")));

        assertNotNull(validator.getMessages().get(Constants.Attributes.PASSWORD));
        String password = "";
        validator = new UserValidator(null, password, "en");
        assertNotNull(validator.getMessages().get(Constants.Attributes.PASSWORD));
        password = "ddd";
        validator = new UserValidator(null, password, "ru");
        assertNotNull(validator.getMessages().get(Constants.Attributes.PASSWORD));
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 101; i++) {
            s.append("a");
        }
        validator = new UserValidator(null, s.toString(), "en");
        assertNotNull(validator.getMessages().get(Constants.Attributes.PASSWORD));
        password = "John Doe";
        validator = new UserValidator(null, password, "en");
        assertNotNull(validator.getMessages().get(Constants.Attributes.PASSWORD));
        password = "John95";
        validator = new UserValidator(null, password, "en");
        assertNull(validator.getMessages().get(Constants.Attributes.PASSWORD));
    }
}