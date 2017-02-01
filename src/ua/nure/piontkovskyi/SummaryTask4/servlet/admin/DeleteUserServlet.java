package ua.nure.piontkovskyi.SummaryTask4.servlet.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.piontkovskyi.SummaryTask4.servlet.BaseServlet;
import ua.nure.piontkovskyi.SummaryTask4.util.constants.Constants;
import ua.nure.piontkovskyi.SummaryTask4.validator.UserValidator;
import ua.nure.piontkovskyi.SummaryTask4.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Delete user servlet which delete user in DB.
 */
@WebServlet(urlPatterns = Constants.ServletPaths.Admin.DELETE_USER)
public class DeleteUserServlet extends BaseServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteUserServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = getStringParam(req, Constants.Attributes.ID);
        if (getCurrentUser(req).getId() == Integer.parseInt(id)) {
            Validator validator = new UserValidator(getCurrentUser(req), getLocale(req));
            validator.putIssue("user", Constants.Validation.CANT_DELETE_YOURSELF);
            sendError(req, resp, validator);
            return;
        }
        getUserService().remove(Integer.parseInt(id));
        LOGGER.trace("User with id {} was deleted", id);
        redirectToAction(Constants.ServletPaths.Admin.USER_LIST, req, resp);
    }
}
