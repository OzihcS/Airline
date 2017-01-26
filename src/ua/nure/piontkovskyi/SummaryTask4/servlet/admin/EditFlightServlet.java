package ua.nure.piontkovskyi.SummaryTask4.servlet.admin;

import ua.nure.piontkovskyi.SummaryTask4.model.Flight;
import ua.nure.piontkovskyi.SummaryTask4.model.enums.Status;
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
import java.util.Date;

@WebServlet(urlPatterns = Constants.ServletPaths.Admin.EDIT_FLIGHT)
public class EditFlightServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = getStringParam(req, "id");
        System.out.println(id);
        Flight flight = getFlightService().getById(Integer.parseInt(id));
        req.setAttribute("flight", flight);
        forward(Constants.Pages.Admin.EDIT_FLIGHT, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Flight flightToEdit = getFlightService().getById(Integer.parseInt(getStringParam(req, "id")));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");

        String name = getStringParam(req, "name");
        String departureLocation = getStringParam(req, "departureLocation");
        String arriveLocation = getStringParam(req, "arriveLocation");
        Date departureDate = null;
        Date arriveDate = null;
        try {
            departureDate = format.parse(getStringParam(req, "departureDate"));
            System.out.println("D"+ departureDate);
            arriveDate = format.parse(getStringParam(req, "arriveDate"));
        } catch (ParseException e) {
            //TODO exc
        }

        Validator validator = new FlightValidator(name, departureLocation, arriveLocation, getLocale(req));
        if (validator.hasErrors()) {
            sendError(req, resp, validator);
            return;
        }

        flightToEdit.setName(name);
        flightToEdit.setDepartureLocation(departureLocation);
        flightToEdit.setArriveLocation(arriveLocation);
        if (departureDate != null) {
            flightToEdit.setDepartureDate(departureDate);
        }
        if (arriveDate != null) {
            flightToEdit.setArriveDate(arriveDate);
        }
        getFlightService().update(flightToEdit);
        redirectToAction(Constants.ServletPaths.Admin.FLIGHT_LIST, req, resp);
    }
}
