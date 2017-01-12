package ua.nure.piontkovskyi.SummaryTask4.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.piontkovskyi.SummaryTask4.model.User;
import ua.nure.piontkovskyi.SummaryTask4.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Basic servlet that provides functionality for operating
 * with current user. And safe retrieving of parameters.
 */
public class BaseServlet extends AbstractServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseServlet.class);

    protected User getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object userObj = session.getAttribute(Constants.Attributes.CURRENT_USER);
        return userObj == null ? null : (User) userObj;
    }

    /**
     * Set current user in {@link HttpSession}
     *
     * @param request
     * @param user    user to set
     */
    protected void setCurrentUser(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        session.setAttribute(Constants.Attributes.CURRENT_USER, user);
    }

    /**
     * Unset current user in {@link HttpSession}
     *
     * @param request
     */
    protected void unsetCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(Constants.Attributes.CURRENT_USER);
    }

    protected void forward(String page, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(page).forward(request, response);
    }

    protected void redirectToAction(String uri, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + uri);
    }
}
