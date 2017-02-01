package ua.nure.piontkovskyi.SummaryTask4.servlet.admin;

import ua.nure.piontkovskyi.SummaryTask4.entity.User;
import ua.nure.piontkovskyi.SummaryTask4.servlet.BaseServlet;
import ua.nure.piontkovskyi.SummaryTask4.util.constants.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * User list servlet which get all users from DB.
 */
@WebServlet(urlPatterns = Constants.ServletPaths.Admin.USER_LIST)
public class UserListServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> userList = getUserService().getAllUsers();
        req.setAttribute(Constants.Attributes.USERS, userList);
        forward(Constants.Pages.Admin.USERS, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = getStringParam(req, Constants.Attributes.ID);
        List<User> users;
        if (!(id == null)) {
            users = new ArrayList<>();
            users.add(getUserService().getById(Integer.parseInt(id)));
            req.setAttribute(Constants.Attributes.USERS, users);
            forward(Constants.Pages.Admin.USERS, req, resp);
            return;
        }
        users = getUserService().getAllUsers();
        req.setAttribute(Constants.Attributes.USERS, users);
        forward(Constants.Pages.Admin.USERS, req, resp);
    }
}
