package servlet;


import entity.User;
import nl.captcha.Captcha;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.constants.Constants;
import validator.UserValidator;
import validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Login servlet which authenticate user and redirect to users homepage
 */
@WebServlet(urlPatterns = Constants.ServletPaths.LOGIN)
public class LoginServlet extends BaseServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getCurrentUser(req);
        if (user != null) {
            LOGGER.error("User already logged in");
            redirect(user, req, resp);
            return;
        }
        forward(Constants.Pages.LOGIN, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = getCurrentUser(req);

        if (currentUser != null) {
            LOGGER.error("User already logged in");
            redirect(currentUser, req, resp);
            return;
        }

        Captcha captcha = (Captcha) req.getSession().getAttribute(Constants.Attributes.CAPTCHA);

        if (!captcha.getAnswer().equals(getStringParam(req, Constants.Attributes.CAPTCHA))) {
            redirectToAction(Constants.ServletPaths.LOGIN, req, resp);
            return;
        }

        String login = decodeParameter(getStringParam(req, Constants.Attributes.LOGIN));
        String password = decodeParameter(getStringParam(req, Constants.Attributes.PASSWORD));

        Validator validator = new UserValidator(login, password, getLocale(req));

        if (validator.hasErrors()) {
            sendError(req, resp, validator);
            return;
        }

        User user = getUserService().getByLogin(login);
        if (user == null || !user.getLogin().equals(login)) {
            validator.putIssue(Constants.Attributes.LOGIN, "validator.invalidLogin");
            sendError(req, resp, validator);
            return;
        }

        LOGGER.debug("User found. The id is {} and login is {}", user.getId(), user.getLogin());

        if (!user.getPassword().equals(password)) {
            validator.putIssue(Constants.Attributes.PASSWORD, "validator.invalidPassword");
            sendError(req, resp, validator);
            return;
        }
        setCurrentUser(req, user);
        redirect(user, req, resp);
    }

    private void redirect(User user, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (user.getRole()) {
            case DISPATCHER: {
                redirectToAction(Constants.ServletPaths.Dispatcher.MAIN, req, resp);
                break;
            }
            case ADMINISTRATOR: {
                redirectToAction(Constants.ServletPaths.Admin.MAIN, req, resp);
                break;
            }
        }
    }

}
