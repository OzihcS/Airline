package ua.nure.piontkovskyi.SummaryTask4.servlet.dispatcher;

import ua.nure.piontkovskyi.SummaryTask4.model.Request;
import ua.nure.piontkovskyi.SummaryTask4.model.enums.RequestStatus;
import ua.nure.piontkovskyi.SummaryTask4.servlet.BaseServlet;
import ua.nure.piontkovskyi.SummaryTask4.util.constants.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


@WebServlet(urlPatterns = Constants.ServletPaths.Dispatcher.NEW_REQUEST)
public class NewRequestServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(Constants.Attributes.ADMINS, getUserService().getAdmins());
        forward(Constants.Pages.Dispatcher.NEW_REQUEST, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String from = req.getParameter("fromId");
        String toId = req.getParameter("toId");
        String message = req.getParameter("message");
        String title = getStringParam(req, "title");

        if (from.isEmpty()) {
            //TODO exc
            return;
        }

        Request request = new Request();
        request.setToId(Integer.parseInt(toId));
        request.setFrom(from);
        request.setMessage(message);
        request.setDate(new Date());
        request.setTitle(title);
        request.setStatus(RequestStatus.UNCONFIRMED);

        getRequestService().add(request);
        redirectToAction(Constants.ServletPaths.Dispatcher.CREATE_BRIGADE, req, resp);
    }
}
