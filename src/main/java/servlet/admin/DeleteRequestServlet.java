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
 * Delete request servlet which delete request in DB.
 */
@WebServlet(urlPatterns = Constants.ServletPaths.Admin.DELETE_REQUEST)
public class DeleteRequestServlet extends BaseServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteRequestServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = getStringParam(req, Constants.Attributes.ID);
        getRequestService().remove(Integer.parseInt(id));
        LOGGER.trace("Request with id {} was deleted", id);
        redirectToAction(Constants.ServletPaths.Admin.MAIN, req, resp);
    }

}
