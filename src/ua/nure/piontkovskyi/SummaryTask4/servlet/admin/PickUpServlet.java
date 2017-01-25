package ua.nure.piontkovskyi.SummaryTask4.servlet.admin;

import ua.nure.piontkovskyi.SummaryTask4.model.Flight;
import ua.nure.piontkovskyi.SummaryTask4.servlet.BaseServlet;
import ua.nure.piontkovskyi.SummaryTask4.util.constants.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = Constants.ServletPaths.Admin.PICK_UP)
public class PickUpServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        List<Flight> flights;
        if (!(id == null)) {
            flights = new ArrayList<>();
            flights.add(getFlightService().getById(Integer.parseInt(id)));
            req.setAttribute("flights", flights);
            forward(Constants.Pages.Admin.FLIGHTS, req, resp);
            return;
        }
        String from = req.getParameter("from");
        String to = req.getParameter("to");
        String departure = req.getParameter("departure");

        flights = getFlightService().getAll();
        flights = pickUpFrom(flights, from);
        flights = pickUpTo(flights, to);
        flights = pickUpFromDeparture(flights, departure);
        req.setAttribute(Constants.Attributes.FLIGHTS, flights);
        forward(Constants.Pages.Admin.FLIGHTS, req, resp);

    }


    private List<Flight> pickUpFromDeparture(List<Flight> flights, String departure) {
        if (!departure.isEmpty()) {
            List<Flight> result = new ArrayList<>();
            for (int i = 0; i < flights.size(); i++) {
                if (!flights.get(i).getDepartureDate().equals(departure)) {
                    result.add(flights.get(i));
                }
            }
            return result;
        }
        return flights;
    }

    private List<Flight> pickUpFrom(List<Flight> flights, String from) {
        if (!from.isEmpty()) {
            List<Flight> result = new ArrayList<>();
            for (int i = 1; i < flights.size(); i++) {
                if (flights.get(i).getDepartureLocation().equals(from)) {
                    result.add(flights.get(i));
                }
            }
            return result;
        }
        return flights;
    }

    private List<Flight> pickUpTo(List<Flight> flights, String to) {
        if (!to.isEmpty()) {
            List<Flight> result = new ArrayList<>();
            for (int i = 1; i < flights.size(); i++) {
                if (flights.get(i).getArriveLocation().equals(to)) {
                    result.add(flights.get(i));
                }
            }
            return result;
        }
        return flights;
    }
}
