package ua.nure.piontkovskyi.SummaryTask4.servlet.dispatcher;

import ua.nure.piontkovskyi.SummaryTask4.model.Brigade;
import ua.nure.piontkovskyi.SummaryTask4.model.Staffer;
import ua.nure.piontkovskyi.SummaryTask4.model.enums.Role;
import ua.nure.piontkovskyi.SummaryTask4.model.enums.StaffRole;
import ua.nure.piontkovskyi.SummaryTask4.servlet.BaseServlet;
import ua.nure.piontkovskyi.SummaryTask4.util.constants.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = Constants.ServletPaths.Dispatcher.CREATE_BRIGADE)
public class CreateBrigadeServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        req.getSession().setAttribute("flightId", id);

        req.setAttribute("pilots", getFlightService().getStaffersByRole(StaffRole.PILOT));
        req.setAttribute("radiomen", getFlightService().getStaffersByRole(StaffRole.RADIOMAN));
        req.setAttribute("navigators", getFlightService().getStaffersByRole(StaffRole.NAVIGATOR));
        req.setAttribute("stewardesses", getFlightService().getStaffersByRole(StaffRole.STEWARDESS));
        forward(Constants.Pages.Dispatcher.CREATE_BRIGADE, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pilot = req.getParameter("pilot");
        String radioman = req.getParameter("radioman");
        String navigator = req.getParameter("navigator");
        String firstStewardess = req.getParameter("firstStewardess");
        String secondStewardess = getStringParam(req, "secondStewardess");
        String thirdStewardess = getStringParam(req, "thirdStewardess");

        Brigade brigade = new Brigade();

        //TODO validate

        brigade.setRadioman(getFlightService().getStaffer(Integer.parseInt(radioman)));
        brigade.setNavigator(getFlightService().getStaffer(Integer.parseInt(navigator)));
        brigade.setPilot(getFlightService().getStaffer(Integer.parseInt(pilot)));
        if (!firstStewardess.isEmpty()) {
            brigade.addStewardess(getFlightService().getStaffer(Integer.parseInt(firstStewardess)));
        }
        if (!secondStewardess.isEmpty()) {
            brigade.addStewardess(getFlightService().getStaffer(Integer.parseInt(secondStewardess)));
        }
        if (!thirdStewardess.isEmpty()) {
            brigade.addStewardess(getFlightService().getStaffer(Integer.parseInt(thirdStewardess)));
        }
        getFlightService().setBrigade(Integer.parseInt(String.valueOf(req.getSession().getAttribute("flightId"))), brigade);
        redirectToAction(Constants.ServletPaths.Dispatcher.MAIN, req, resp);
    }
}
