package ua.nure.piontkovskyi.SummaryTask4.servlet.admin;

import ua.nure.piontkovskyi.SummaryTask4.entity.Flight;
import ua.nure.piontkovskyi.SummaryTask4.servlet.BaseServlet;
import ua.nure.piontkovskyi.SummaryTask4.util.constants.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Flight list servlet which get all flights from DB.
 */
@WebServlet(urlPatterns = Constants.ServletPaths.Admin.FLIGHT_LIST)
public class FlightListServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Flight> flights = getFlightService().getAllFlights();
        req.setAttribute(Constants.Attributes.FLIGHTS, flights);
        forward(Constants.Pages.Admin.FLIGHTS, req, resp);
    }
}
