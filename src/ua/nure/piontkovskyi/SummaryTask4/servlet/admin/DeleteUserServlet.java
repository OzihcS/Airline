package ua.nure.piontkovskyi.SummaryTask4.servlet.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.piontkovskyi.SummaryTask4.servlet.BaseServlet;
import ua.nure.piontkovskyi.SummaryTask4.util.constants.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = Constants.ServletPaths.Admin.DELETE_USER)
public class DeleteUserServlet extends BaseServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteUserServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = getStringParam(req, "id");
        getUserService().remove(Integer.parseInt(id));
        LOGGER.trace("User with id {} was deleted", id);
        redirectToAction(Constants.ServletPaths.Admin.USER_LIST, req, resp);
    }
}