package ua.nure.piontkovskyi.SummaryTask4.servlet.admin;

import ua.nure.piontkovskyi.SummaryTask4.model.Request;
import ua.nure.piontkovskyi.SummaryTask4.model.enums.RequestStatus;
import ua.nure.piontkovskyi.SummaryTask4.servlet.BaseServlet;
import ua.nure.piontkovskyi.SummaryTask4.util.constants.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = Constants.ServletPaths.Admin.MAIN)
public class MainPageServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Request> requests = getRequestService().get(getCurrentUser(req).getId());
        req.setAttribute(Constants.Attributes.REQUESTS, requests);
        forward(Constants.Pages.Admin.MAIN, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Request request = new Request();
        request.setId(Integer.parseInt(req.getParameter("id")));
        request.setStatus(RequestStatus.valueOf(req.getParameter("status")));
        getRequestService().update(request);
        redirectToAction(Constants.ServletPaths.Admin.MAIN, req, resp);
    }
}
