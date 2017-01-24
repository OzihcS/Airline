package ua.nure.piontkovskyi.SummaryTask4.servlet.admin;

import ua.nure.piontkovskyi.SummaryTask4.model.User;
import ua.nure.piontkovskyi.SummaryTask4.model.enums.Role;
import ua.nure.piontkovskyi.SummaryTask4.servlet.BaseServlet;
import ua.nure.piontkovskyi.SummaryTask4.util.constants.Constants;
import ua.nure.piontkovskyi.SummaryTask4.validator.UserValidator;
import ua.nure.piontkovskyi.SummaryTask4.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = Constants.ServletPaths.Admin.EDIT_USER)
public class EditUserServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = getStringParam(req, "id");
        User user = getUserService().getById(Integer.parseInt(id));
        req.setAttribute("user", user);
        forward(Constants.Pages.Admin.EDIT_USER, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User userToEdit = getUserService().getById(Integer.parseInt(req.getParameter("id")));

        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Role role = Role.valueOf(req.getParameter("role"));

        if (password.isEmpty()) {
            password = userToEdit.getPassword();
        }

        Validator validator = new UserValidator(name, login, password, getLocale(req));

        if (validator.hasErrors()) {
            sendError(req, resp, validator);
            return;
        }
        userToEdit.setName(name);
        userToEdit.setLogin(login);
        userToEdit.setPassword(password);
        if (!userToEdit.getRole().equals(role)) {
            userToEdit.setRole(role);
        }
        System.out.println(getUserService().update(userToEdit));
        redirectToAction(Constants.ServletPaths.Admin.USER_LIST, req, resp);
    }
}

