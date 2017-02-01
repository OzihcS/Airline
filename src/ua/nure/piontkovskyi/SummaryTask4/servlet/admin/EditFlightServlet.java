package ua.nure.piontkovskyi.SummaryTask4.servlet.admin;

import ua.nure.piontkovskyi.SummaryTask4.entity.Flight;
import ua.nure.piontkovskyi.SummaryTask4.entity.enums.Status;
import ua.nure.piontkovskyi.SummaryTask4.servlet.BaseServlet;
import ua.nure.piontkovskyi.SummaryTask4.util.constants.Constants;
import ua.nure.piontkovskyi.SummaryTask4.validator.FlightValidator;
import ua.nure.piontkovskyi.SummaryTask4.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Edit flight servlet which update flight in DB.
 */
@WebServlet(urlPatterns = Constants.ServletPaths.Admin.EDIT_FLIGHT)
public class EditFlightServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = getStringParam(req, Constants.Attributes.ID);
        Flight flight = getFlightService().getById(Integer.parseInt(id));
        req.setAttribute(Constants.Attributes.FLIGHT, flight);
        forward(Constants.Pages.Admin.EDIT_FLIGHT, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Flight flightToEdit = getFlightService().getById(Integer.parseInt(getStringParam(req, Constants.Attributes.ID)));

        String name = decodeParameter(getStringParam(req, Constants.Attributes.NAME));
        String departureLocation = decodeParameter(getStringParam(req, Constants.Attributes.DEPARTURE_LOCATION));
        String arriveLocation = decodeParameter(getStringParam(req, Constants.Attributes.ARRIVE_LOCATION));
        String departureDate = getStringParam(req, Constants.Attributes.DEPARTURE_DATE);
        String arriveDate = getStringParam(req, Constants.Attributes.ARRIVE_DATE);

        Flight flight = new Flight();
        flight.setName(name);
        flight.setArriveLocation(arriveLocation);
        flight.setDepartureLocation(departureLocation);
        flight.setStatus(Status.UNCONFIRMED);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        Validator validator = new FlightValidator(name, departureLocation, arriveLocation, getLocale(req));
        try {
            flight.setDepartureDate(format.parse(departureDate));
            flight.setArriveDate(format.parse(arriveDate));
        } catch (ParseException e) {
            validator.putIssue(Constants.Attributes.DATE, "validator.invalidDate");
            sendError(req, resp, validator);
            return;
        }
        if (validator.hasErrors()) {
            sendError(req, resp, validator);
            return;
        }

        if (departureDate.compareTo(arriveDate) == 1) {
            validator.putIssue(Constants.Attributes.DEPARTURE_DATE, Constants.Validation.DATE_ERROR);
            sendError(req, resp, validator);
            return;
        }

        getFlightService().updateFlight(flightToEdit);
        redirectToAction(Constants.ServletPaths.Admin.FLIGHT_LIST, req, resp);
    }
}
