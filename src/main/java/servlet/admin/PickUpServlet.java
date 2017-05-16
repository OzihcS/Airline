package servlet.admin;

import entity.Flight;
import entity.enums.Role;
import servlet.BaseServlet;
import util.constants.Constants;
import validator.PickValidator;
import validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet provide sorting all flight by param
 */
@WebServlet(urlPatterns = Constants.ServletPaths.Admin.PICK_UP)
public class PickUpServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = getStringParam(req, Constants.Attributes.ID);
        String from = decodeParameter(getStringParam(req, Constants.Attributes.DEPARTURE_LOCATION));
        String to = decodeParameter(getStringParam(req, Constants.Attributes.ARRIVE_LOCATION));
        String departure = getStringParam(req, Constants.Attributes.DEPARTURE_DATE);
        String arrive = getStringParam(req, Constants.Attributes.ARRIVE_DATE);

        Validator validator = new PickValidator(id, from, to, departure, arrive, getLocale(req));
        if (validator.hasErrors()) {
            sendError(req, resp, validator);
            return;
        }
        List<Flight> flights;
        if (!id.isEmpty()) {
            flights = new ArrayList<>();
            flights.add(getFlightService().getById(Integer.parseInt(id)));
        } else {
            flights = getFlightService().getAllFlights();
            flights = getByDepartureLocation(flights, from);
            flights = getByArriveLocation(flights, to);
            flights = getByDepartureDate(flights, departure);
            flights = getByArriveDate(flights, arrive);
        }
        req.setAttribute(Constants.Attributes.FLIGHTS, flights);
        if (getCurrentUser(req).getRole() == Role.ADMINISTRATOR) {
            forward(Constants.Pages.Admin.FLIGHTS, req, resp);
        } else {
            forward(Constants.Pages.Dispatcher.MAIN, req, resp);
        }
    }

    private List<Flight> getByArriveDate(List<Flight> flights, String arrive) {
        if (!arrive.isEmpty()) {
            List<Flight> result = new ArrayList<>();
            for (int i = 0; i < flights.size(); i++) {
                if (flights.get(i).getArriveDate().toString().equals(arrive)) {
                    result.add(flights.get(i));
                }
            }
            return result;
        }
        return flights;
    }


    private List<Flight> getByDepartureDate(List<Flight> flights, String departure) {
        if (!departure.isEmpty()) {
            List<Flight> result = new ArrayList<>();
            for (int i = 0; i < flights.size(); i++) {
                if (flights.get(i).getDepartureDate().toString().equals(departure)) {
                    result.add(flights.get(i));
                }
            }
            return result;
        }
        return flights;
    }

    private List<Flight> getByDepartureLocation(List<Flight> flights, String from) {
        if (!from.isEmpty()) {
            List<Flight> result = new ArrayList<>();
            for (int i = 0; i < flights.size(); i++) {
                if (flights.get(i).getDepartureLocation().equals(from)) {
                    result.add(flights.get(i));
                }
            }
            return result;
        }
        return flights;
    }

    private List<Flight> getByArriveLocation(List<Flight> flights, String to) {
        if (!to.isEmpty()) {
            List<Flight> result = new ArrayList<>();
            for (int i = 0; i < flights.size(); i++) {
                if (flights.get(i).getArriveLocation().equals(to)) {
                    result.add(flights.get(i));
                }
            }
            return result;
        }
        return flights;
    }
}
