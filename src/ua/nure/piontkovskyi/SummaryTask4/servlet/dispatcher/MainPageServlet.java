package ua.nure.piontkovskyi.SummaryTask4.servlet.dispatcher;

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

@WebServlet(urlPatterns = Constants.ServletPaths.Dispatcher.MAIN)
public class MainPageServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Flight> flights = null;
        flights = getFlightService().getAll();
        req.setAttribute("flights", flights);
        forward(Constants.Pages.Dispatcher.MAIN, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        List<Flight> flights;
        if (!(id == null)) {
            flights = new ArrayList<>();
            flights.add(getFlightService().getById(Integer.parseInt(id)));
            req.setAttribute("flights", flights);
            forward(Constants.Pages.Dispatcher.MAIN, req, resp);
            return;
        }
        flights = getFlightService().getAll();
        req.setAttribute("flights", flights);
        forward(Constants.Pages.Dispatcher.MAIN, req, resp);
    }
}
