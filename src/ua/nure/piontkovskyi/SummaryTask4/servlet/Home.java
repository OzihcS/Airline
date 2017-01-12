package ua.nure.piontkovskyi.SummaryTask4.servlet;

import ua.nure.piontkovskyi.SummaryTask4.model.User;
import ua.nure.piontkovskyi.SummaryTask4.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = {Constants.ServletPaths.LOGIN})
public class Home extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String login = req.getParameter("login");
//        String password = req.getParameter("password");
        User user = getUserService().getByLogin("ivanov");
        System.out.println("name:" + user.getName());
    }
}
