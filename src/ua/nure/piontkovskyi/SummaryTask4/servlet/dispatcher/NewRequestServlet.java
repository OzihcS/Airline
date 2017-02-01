package ua.nure.piontkovskyi.SummaryTask4.servlet.dispatcher;

import ua.nure.piontkovskyi.SummaryTask4.entity.Request;
import ua.nure.piontkovskyi.SummaryTask4.entity.enums.RequestStatus;
import ua.nure.piontkovskyi.SummaryTask4.servlet.BaseServlet;
import ua.nure.piontkovskyi.SummaryTask4.util.constants.Constants;
import ua.nure.piontkovskyi.SummaryTask4.validator.RequestValidator;
import ua.nure.piontkovskyi.SummaryTask4.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * New request servlet which create {@link Request} and add it to DB.
 */
@WebServlet(urlPatterns = Constants.ServletPaths.Dispatcher.NEW_REQUEST)
public class NewRequestServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(Constants.Attributes.ADMINS, getUserService().getAdmins());
        forward(Constants.Pages.Dispatcher.NEW_REQUEST, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String from = decodeParameter(getStringParam(req, Constants.Attributes.FROM_ID));
        String toId = getStringParam(req, Constants.Attributes.TO_ID);
        String message = decodeParameter(getStringParam(req, Constants.Attributes.MESSAGE));
        String title = decodeParameter(getStringParam(req, Constants.Attributes.TITLE));

        Request request = new Request();
        request.setToId(Integer.parseInt(toId));
        request.setFrom(from);
        request.setMessage(message);
        request.setDate(new Date());
        request.setTitle(title);
        request.setStatus(RequestStatus.UNCONFIRMED);

        Validator validator = new RequestValidator(request, getLocale(req));

        if (validator.hasErrors()) {
            sendError(req, resp, validator);
            return;
        }

        getRequestService().add(request);
        redirectToAction(Constants.ServletPaths.Dispatcher.CREATE_BRIGADE, req, resp);
    }
}
