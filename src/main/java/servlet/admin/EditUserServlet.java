package servlet.admin;

import entity.User;
import entity.enums.Role;
import servlet.BaseServlet;
import util.constants.Constants;
import validator.UserValidator;
import validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Edit user servlet which update user in DB.
 */
@WebServlet(urlPatterns = Constants.ServletPaths.Admin.EDIT_USER)
public class EditUserServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = getStringParam(req, Constants.Attributes.ID);
        User user = getUserService().getById(Integer.parseInt(id));
        req.setAttribute(Constants.Attributes.USER, user);
        forward(Constants.Pages.Admin.EDIT_USER, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User userToEdit = getUserService().getById(Integer.parseInt(getStringParam(req, Constants.Attributes.ID)));

        String name = decodeParameter(getStringParam(req, Constants.Attributes.NAME));
        String login = decodeParameter(getStringParam(req, Constants.Attributes.LOGIN));
        String password = decodeParameter(getStringParam(req, Constants.Attributes.PASSWORD));
        Role role = Role.valueOf(getStringParam(req, Constants.Attributes.ROLE));

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

