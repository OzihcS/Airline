package ua.nure.piontkovskyi.SummaryTask4.servlet.dispatcher;

import ua.nure.piontkovskyi.SummaryTask4.model.enums.Status;
import ua.nure.piontkovskyi.SummaryTask4.servlet.BaseServlet;
import ua.nure.piontkovskyi.SummaryTask4.util.constants.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = Constants.ServletPaths.Dispatcher.CHANGE_STATUS)
public class ChangeStatusServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = getStringParam(req, "id");
        String status = getStringParam(req, "status");

        getFlightService().chaneStatus(Integer.parseInt(id), Status.valueOf(status));

        redirectToAction(Constants.ServletPaths.Dispatcher.MAIN, req, resp);


    }
}
