package ua.nure.piontkovskyi.SummaryTask4.validator;

import junit.framework.TestCase;
import org.junit.Test;
import ua.nure.piontkovskyi.SummaryTask4.model.User;

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

        assertNotNull(validator.getMessages().get("name"));
        user.setName("");
        validator = new UserValidator(user, "ru");
        assertNotNull(validator.getMessages().get("name"));
        user.setName("d");
        validator = new UserValidator(user, "ru");
        assertNotNull(validator.getMessages().get("name"));
        user.setName("123");
        validator = new UserValidator(user, "ru");
        assertNotNull(validator.getMessages().get("name"));
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < 101; i++) {
            name.append("a");
        }
        user.setName(name.toString());
        validator = new UserValidator(user, "ru");
        assertNotNull(validator.getMessages().get("name"));
        user.setName("Ivan");
        validator = new UserValidator(user, "ru");
        assertNull(validator.getMessages().get("name"));
    }

    @Test
    public void validateLogin() {
        Validator validator = new UserValidator(null, "en");
        ((UserValidator) validator).setBundle(ResourceBundle.getBundle("messages", new Locale("en")));

        assertNull(validator.getMessages().get("login"));
        String login = "";
        validator = new UserValidator(login, null, "en");
        assertNotNull(validator.getMessages().get("login"));
        login = "d";
        validator = new UserValidator(login, null, "en");
        assertNotNull(validator.getMessages().get("login"));
        StringBuilder loginString = new StringBuilder();
        for (int i = 0; i < 101; i++) {
            loginString.append("a");
        }
        validator = new UserValidator(loginString.toString(), null, "en");
        assertNotNull(validator.getMessages().get("login"));
        login = "John 95";
        validator = new UserValidator(login, null, "en");
        assertNotNull(validator.getMessages().get("login"));
        login = "John95";
        validator = new UserValidator(login, null, "en");
        assertNull(validator.getMessages().get("login"));
    }

    @Test
    public void testPassword() {
        Validator validator = new UserValidator(null, null, "en");
        ((UserValidator) validator).setBundle(ResourceBundle.getBundle("messages", new Locale("en")));

        assertNotNull(validator.getMessages().get("password"));
        String password = "";
        validator = new UserValidator(null, password, "en");
        assertNotNull(validator.getMessages().get("password"));
        password = "ddd";
        validator = new UserValidator(null, password, "ru");
        assertNotNull(validator.getMessages().get("password"));
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 101; i++) {
            s.append("a");
        }
        validator = new UserValidator(null, s.toString(), "en");
        assertNotNull(validator.getMessages().get("password"));
        password = "John Doe";
        validator = new UserValidator(null, password, "en");
        assertNotNull(validator.getMessages().get("password"));
        password = "John95";
        validator = new UserValidator(null, password, "en");
        assertNull(validator.getMessages().get("password"));
    }
}