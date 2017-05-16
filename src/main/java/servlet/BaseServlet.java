package servlet;

import entity.User;
import exception.SerializerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.FileService;
import util.constants.Constants;
import util.serializer.StreamSerializer;
import validator.Validator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * Basic servlet that provides functionality for operating
 * with current user. And safe retrieving of parameters.
 */
public abstract class BaseServlet extends AbstractServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseServlet.class);

    private static final long serialVersionUID = -9037428387637041940L;
    private FileService fileService;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        LOGGER.info("Basic servlet init started.");
        super.init();
        fileService = (FileService) context.getAttribute(Constants.Service.FILE_PROC_SERVICE);
        LOGGER.info("Basic servlet init finished.");
    }

    protected FileService getFileService() {
        return fileService;
    }

    protected User getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object userObj = session.getAttribute(Constants.Attributes.CURRENT_USER);
        return userObj == null ? null : (User) userObj;
    }

    /**
     * Set current user in {@link HttpSession}
     *
     * @param request
     * @param user    user to set
     */
    protected void setCurrentUser(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        session.setAttribute(Constants.Attributes.CURRENT_USER, user);
    }

    /**
     * Unset current user in {@link HttpSession}
     *
     * @param request
     */
    protected void unsetCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(Constants.Attributes.CURRENT_USER);
    }

    protected void forward(String page, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(page).forward(request, response);
    }

    protected void redirectToAction(String uri, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getContextPath());
        response.sendRedirect(request.getContextPath() + uri);
    }

    protected String decodeParameter(String parameter) throws UnsupportedEncodingException {
        return new String(parameter.getBytes("ISO-8859-1"), "UTF8");
    }


    protected void print(HttpServletRequest request, HttpServletResponse response, Object o) throws IOException {
        StreamSerializer serializer = (StreamSerializer) getServletContext().getAttribute(Constants.Attributes.SERIALIZER);
        if (serializer == null) {
            response.sendError(404);
            return;
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType(serializer.getContentType());
        try {
            serializer.serialize(response.getOutputStream(), o);
        } catch (SerializerException e) {
            LOGGER.warn("Cannot serialize object", e);
            response.setContentType("text/html");
            response.sendError(500);
        }

    }

    /**
     * Prints a list of items to response
     *
     * @param request  request
     * @param response response
     * @param list     of items to print
     * @param c        class of items stored in the list
     * @param <T>      type of items stored in the list
     * @throws IOException if an input or output exception occurred
     */
    protected <T> void print(HttpServletRequest request, HttpServletResponse response, List<T> list, Class<T> c) throws IOException {
        StreamSerializer serializer = (StreamSerializer) getServletContext().getAttribute(Constants.Attributes.SERIALIZER);
        if (serializer == null) {
            response.sendError(404);
            return;
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType(serializer.getContentType());
        try {
            serializer.serializeList(response.getOutputStream(), list, c);
        } catch (SerializerException e) {
            LOGGER.warn("Cannot serialize list of objects", e);
            response.setContentType("text/html");
            response.sendError(500);
        }
    }


    /**
     * Sets errors which prevents logging and
     * forward back to validation form
     *
     * @param validator {@link Validator} entity that contains errors to be set.
     * @throws ServletException
     * @throws IOException
     */
    protected void sendError(HttpServletRequest request, HttpServletResponse response, Validator validator)
            throws ServletException, IOException {
        LOGGER.trace("Errors found while processing operation");
        StringBuilder error = new StringBuilder();

        for (Map.Entry<String, String> errorEntry : validator.getMessages().entrySet()) {
            error.append(errorEntry.getKey()).append(": ").append(errorEntry.getValue()).append("\n");
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, error.toString());

    }

    /**
     * Checks if the specified login unique
     *
     * @param user      {@link User} to check
     * @param validator {@link Validator} entity for testing.
     */
    protected void checkUserUniqueness(User user, Validator validator) {
        User existingUser = getUserService().getByLogin(user.getLogin());
        if (existingUser != null) {
            validator.putIssue(Constants.Attributes.LOGIN, "validator.loginTaken");
        }
    }
}

