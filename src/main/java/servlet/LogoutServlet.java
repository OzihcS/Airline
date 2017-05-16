package servlet;

import util.constants.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Logout servlet which unset user from session
 */
@WebServlet(urlPatterns = Constants.ServletPaths.LOGOUT)
public class LogoutServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        unsetCurrentUser(req);
        redirectToAction(Constants.ServletPaths.LOGIN, req, resp);
    }
}