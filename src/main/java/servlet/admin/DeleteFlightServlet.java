package servlet.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import servlet.BaseServlet;
import util.constants.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Delete flight servlet which delete flight in DB.
 */
@WebServlet(urlPatterns = Constants.ServletPaths.Admin.DELETE_FLIGHT)
public class DeleteFlightServlet extends BaseServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteFlightServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = getStringParam(req, Constants.Attributes.ID);
        getFlightService().remove(Integer.parseInt(id));
        LOGGER.trace("Flight with id {} was deleted", id);
        redirectToAction(Constants.ServletPaths.Admin.FLIGHT_LIST, req, resp);
    }

}
