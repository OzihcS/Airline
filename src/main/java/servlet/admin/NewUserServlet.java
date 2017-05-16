package servlet.admin;

import entity.User;
import entity.enums.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import servlet.BaseServlet;
import util.constants.Constants;
import validator.UserValidator;
import validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Servlet which provide adding new user to DB.
 */
@WebServlet(urlPatterns = Constants.ServletPaths.Admin.NEW_USER)
public class NewUserServlet extends BaseServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(NewUserServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward(Constants.Pages.Admin.ADD_USER, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = decodeParameter(getStringParam(req, Constants.Attributes.NAME));
        String login = decodeParameter(getStringParam(req, Constants.Attributes.LOGIN));
        String password = decodeParameter(getStringParam(req, Constants.Attributes.PASSWORD));
        String confirmPassword = decodeParameter(getStringParam(req, Constants.Attributes.CONFIRM_PASSWORD));
        String role = getStringParam(req, Constants.Attributes.ROLE);

        User user = new User();
        user.setName(name);
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(Role.valueOf(role));

        Validator validator = new UserValidator(user, getLocale(req));
        checkUserUniqueness(user, validator);

        if (validator.hasErrors()) {
            sendError(req, resp, validator);
            return;
        }

        if (!confirmPassword.equals(user.getPassword())) {
            validator.putIssue(Constants.Attributes.PASSWORD, Constants.Validation.DIFFERENT_PASSWORDS);
            sendError(req, resp, validator);
            return;
        }
        getUserService().add(user);
        LOGGER.trace("New user {} added", user.getName());
        redirectToAction(Constants.ServletPaths.Admin.USER_LIST, req, resp);
    }
}
