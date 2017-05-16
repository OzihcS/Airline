package servlet.admin;

import entity.Flight;
import entity.enums.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import servlet.BaseServlet;
import util.constants.Constants;
import validator.FlightValidator;
import validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Servlet provide adding new flight to DB.
 */
@WebServlet(urlPatterns = Constants.ServletPaths.Admin.NEW_FLIGHT)
public class NewFlightServlet extends BaseServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewFlightServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward(Constants.Pages.Admin.ADD, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = decodeParameter(getStringParam(req, Constants.Attributes.NAME));
        String from = decodeParameter(getStringParam(req, Constants.Attributes.DEPARTURE_LOCATION));
        String to = decodeParameter(getStringParam(req, Constants.Attributes.ARRIVE_LOCATION));
        String departureDate = getStringParam(req, Constants.Attributes.DEPARTURE_DATE);
        String arriveDate = getStringParam(req, Constants.Attributes.ARRIVE_DATE);

        Validator validator = new FlightValidator(name, from, to, getLocale(req));

        if (validator.hasErrors()) {
            sendError(req, resp, validator);
            return;
        }

        if (departureDate.compareTo(arriveDate) == 1) {
            validator.putIssue(Constants.Attributes.DEPARTURE_DATE, Constants.Validation.DATE_ERROR);
            sendError(req, resp, validator);
            return;
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        try {
            getFlightService().add(new Flight(0, name, from, to, Status.UNCONFIRMED,
                    format.parse(departureDate), format.parse(arriveDate), null));
        } catch (ParseException e) {
            validator.putIssue(Constants.Attributes.DATE, "validator.invalidDate");
            sendError(req, resp, validator);
            return;
        }
        LOGGER.trace("New flight added");
        redirectToAction(Constants.ServletPaths.Admin.FLIGHT_LIST, req, resp);
    }
}