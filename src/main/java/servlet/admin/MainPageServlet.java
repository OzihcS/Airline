package servlet.admin;


import entity.AdminStatistic;
import entity.Request;
import entity.enums.RequestStatus;
import servlet.BaseServlet;
import util.constants.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Admin's homepage servlet.
 */
@WebServlet(urlPatterns = Constants.ServletPaths.Admin.MAIN)
public class MainPageServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Request> requests = getRequestService().get(getCurrentUser(req).getId());
        AdminStatistic statistic = getRequestService().getStatistic(getCurrentUser(req).getId());
        req.setAttribute("statistic", statistic);   //
        req.setAttribute(Constants.Attributes.REQUESTS, requests);
        forward(Constants.Pages.Admin.MAIN, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Request request = new Request();
        request.setId(Integer.parseInt(getStringParam(req, Constants.Attributes.ID)));
        request.setStatus(RequestStatus.valueOf(getStringParam(req, Constants.Attributes.STATUS)));
        if (getRequestService().update(request)) {
            int adminId = Integer.parseInt(getStringParam(req, "adminId"));
            AdminStatistic statistic = getRequestService().getStatistic(adminId);
            switch (request.getStatus()) {
                case REJECTED: {
                    statistic.setReject(statistic.getReject() + 1);
                    break;
                }
                case EXECUTED: {
                    statistic.setExecute(statistic.getExecute() + 1);
                    break;
                }
            }
            getRequestService().updateStatistic(adminId, statistic);
        }
        redirectToAction(Constants.ServletPaths.Admin.MAIN, req, resp);
    }
}
