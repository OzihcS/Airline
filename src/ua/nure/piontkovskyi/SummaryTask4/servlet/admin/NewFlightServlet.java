package ua.nure.piontkovskyi.SummaryTask4.servlet.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@WebServlet(urlPatterns = Constants.ServletPaths.Admin.NEW_FLIGHT)
public class NewFlightServlet extends BaseServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewFlightServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward(Constants.Pages.Admin.ADD, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("Name");
        String from = req.getParameter("From");
        String to = req.getParameter("To");
        String departureDate = req.getParameter("DepartureDate");
        String arriveDate = req.getParameter("ArriveDate");

        Validator validator = new FlightValidator(name, from, to, getLocale(req));

        if (validator.hasErrors()) {
            sendError(req, resp, validator);
            return;
        }

        SimpleDateFormat format = new SimpleDateFormat("dd.mm.yyyy");
        try {
            getFlightService().add(new Flight(0, name, from, to, Status.UNCONFIRMED,
                    format.parse(departureDate), format.parse(arriveDate), null));
        } catch (ParseException e) {
            //TODO exception
        }
        LOGGER.trace("New flight added");
        redirectToAction(Constants.ServletPaths.Admin.MAIN, req, resp);
    }
}