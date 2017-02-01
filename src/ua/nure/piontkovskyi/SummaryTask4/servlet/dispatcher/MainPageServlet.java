package ua.nure.piontkovskyi.SummaryTask4.servlet.dispatcher;

import ua.nure.piontkovskyi.SummaryTask4.entity.Flight;
import ua.nure.piontkovskyi.SummaryTask4.servlet.BaseServlet;
import ua.nure.piontkovskyi.SummaryTask4.util.constants.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Dispatcher's homepage servlet
 */
@WebServlet(urlPatterns = Constants.ServletPaths.Dispatcher.MAIN)
public class MainPageServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Flight> flights;
        flights = getFlightService().getAllFlights();
        req.setAttribute(Constants.Attributes.FLIGHTS, flights);
        forward(Constants.Pages.Dispatcher.MAIN, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = getStringParam(req, Constants.Attributes.ID);
        List<Flight> flights;
        if (!(id == null)) {
            flights = new ArrayList<>();
            flights.add(getFlightService().getById(Integer.parseInt(id)));
            req.setAttribute(Constants.Attributes.FLIGHTS, flights);
            forward(Constants.Pages.Dispatcher.MAIN, req, resp);
            return;
        }
        flights = getFlightService().getAllFlights();
        req.setAttribute(Constants.Attributes.FLIGHTS, flights);
        forward(Constants.Pages.Dispatcher.MAIN, req, resp);
    }
}
