package ua.nure.piontkovskyi.SummaryTask4.servlet.admin;

import ua.nure.piontkovskyi.SummaryTask4.model.User;
import ua.nure.piontkovskyi.SummaryTask4.servlet.BaseServlet;
import ua.nure.piontkovskyi.SummaryTask4.util.constants.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = Constants.ServletPaths.Admin.USER_LIST)
public class UserListServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> userList = getUserService().getAll();
        req.setAttribute(Constants.Attributes.USERS, userList);
        forward(Constants.Pages.Admin.USERS, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        System.out.println("______________" + name);
    }
}
