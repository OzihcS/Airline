package ua.nure.piontkovskyi.SummaryTask4.servlet.dispatcher;

import ua.nure.piontkovskyi.SummaryTask4.entity.Brigade;
import ua.nure.piontkovskyi.SummaryTask4.entity.enums.StaffRole;
import ua.nure.piontkovskyi.SummaryTask4.servlet.BaseServlet;
import ua.nure.piontkovskyi.SummaryTask4.util.constants.Constants;
import ua.nure.piontkovskyi.SummaryTask4.validator.UserValidator;
import ua.nure.piontkovskyi.SummaryTask4.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Create brigade servlet
 */
@WebServlet(urlPatterns = Constants.ServletPaths.Dispatcher.CREATE_BRIGADE)
public class CreateBrigadeServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = getStringParam(req, Constants.Attributes.ID);
        req.getSession().setAttribute(Constants.Attributes.FLIGHT_ID, id);

        req.getSession().setAttribute(Constants.Attributes.PILOTS, getFlightService().getStaffersByRole(StaffRole.PILOT));
        req.getSession().setAttribute(Constants.Attributes.RADIOMEN, getFlightService().getStaffersByRole(StaffRole.RADIOMAN));
        req.getSession().setAttribute(Constants.Attributes.NAVIGATORS, getFlightService().getStaffersByRole(StaffRole.NAVIGATOR));
        req.getSession().setAttribute(Constants.Attributes.STEWARDESSES, getFlightService().getStaffersByRole(StaffRole.STEWARDESS));
        forward(Constants.Pages.Dispatcher.CREATE_BRIGADE, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pilot = getStringParam(req, Constants.Attributes.PILOT);
        String radioman = getStringParam(req, Constants.Attributes.RADIOMAN);
        String navigator = getStringParam(req, Constants.Attributes.NAVIGATOR);
        String firstStewardess = getStringParam(req, Constants.Attributes.FIRST_STEWARDESS);
        String secondStewardess = getStringParam(req, Constants.Attributes.SECOND_STEWARDESS);
        String thirdStewardess = getStringParam(req, Constants.Attributes.THIRD_STEWARDESS);

        Brigade brigade = new Brigade();

        Validator validator = new UserValidator(getLocale(req));
        if (radioman == null || radioman.isEmpty()) {
            validator.putIssue(Constants.Attributes.RADIOMAN, Constants.Validation.CANT_BE_EMPTY);
            sendError(req, resp, validator);
            return;
        }
        if (pilot == null || pilot.isEmpty()) {
            validator.putIssue(Constants.Attributes.PILOT, Constants.Validation.CANT_BE_EMPTY);
            sendError(req, resp, validator);
            return;
        }
        if (navigator == null || navigator.isEmpty()) {
            validator.putIssue(Constants.Attributes.NAVIGATOR, Constants.Validation.CANT_BE_EMPTY);
            sendError(req, resp, validator);
            return;
        }
        if (firstStewardess == null || firstStewardess.isEmpty()) {
            validator.putIssue(Constants.Attributes.FIRST_STEWARDESS, Constants.Validation.CANT_BE_EMPTY);
            sendError(req, resp, validator);
            return;
        }

        brigade.setRadioman(getFlightService().getStaffer(Integer.parseInt(radioman)));
        brigade.setNavigator(getFlightService().getStaffer(Integer.parseInt(navigator)));
        brigade.setPilot(getFlightService().getStaffer(Integer.parseInt(pilot)));
        brigade.addStewardess(getFlightService().getStaffer(Integer.parseInt(firstStewardess)));

        if (!(secondStewardess == null)) {
            brigade.addStewardess(getFlightService().getStaffer(Integer.parseInt(secondStewardess)));
        }
        if (!(thirdStewardess == null)) {
            brigade.addStewardess(getFlightService().getStaffer(Integer.parseInt(thirdStewardess)));
        }
        getFlightService().setBrigade(Integer.parseInt(String.valueOf(req.getSession().
                getAttribute(Constants.Attributes.FLIGHT_ID))), brigade);
        redirectToAction(Constants.ServletPaths.Dispatcher.MAIN, req, resp);
    }
}
