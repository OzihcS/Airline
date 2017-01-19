package ua.nure.piontkovskyi.SummaryTask4.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.piontkovskyi.SummaryTask4.exception.SerializerException;
import ua.nure.piontkovskyi.SummaryTask4.model.User;
import ua.nure.piontkovskyi.SummaryTask4.util.Constants;
import ua.nure.piontkovskyi.SummaryTask4.util.FileService;
import ua.nure.piontkovskyi.SummaryTask4.util.StreamSerializer;
import ua.nure.piontkovskyi.SummaryTask4.validator.Validator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
        response.sendRedirect(request.getContextPath() + uri);
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

/*    */

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
        //request.setAttribute("messages", validator.getMessages());

        LOGGER.trace("Errors found while processing operation");

        // print(request, response, validator.getMessages());

        StringBuilder error = new StringBuilder();

        for (Map.Entry<String, String> errorEntry : validator.getMessages().entrySet()) {
            error.append(errorEntry.getKey()).append(": ").append(errorEntry.getValue()).append("\n");
        }

        response.sendError(HttpServletResponse.SC_BAD_REQUEST, error.toString());

    }

//    protected User saveUser(String image, User user) {
//        User savedUser = getUserService().save(user);
//        if (image != null && image.length() > 0) {
//            String imageName = getFileService().saveFile(savedUser.getId(), Settings.getUploadUsersDirectory(), image);
//
//            savedUser.setImage(imageName);
//            savedUser = getUserService().update(savedUser);
//        }
//        return savedUser;
//    }

//    protected User updateUser(String image, User user) {
//        if (image != null) {
//            String imageName = getFileService().saveFile(user.getId(), SettingsAndFolderPaths.getUploadUsersDirectory(), image);
//            user.setImage(imageName);
//        }
//
//        return getUserService().update(user);
//    }

//    protected void checkUserUniqueness(User user, Validator validator) {
//        User existingUser = getUserService().getByLogin(user.getLogin());
//        if (existingUser != null) {
//            validator.putIssue("login", "validator.loginTaken");
//        }
//        existingUser = getUserService().getByEmail(user.getEmail());
//        if (existingUser != null) {
//            validator.putIssue("email", "validator.emailTaken");
//        }
//    }
//
//    protected void checkCourseUniqueness(Course course, Validator validator) {
//        Course existingCourse = getCourseService().getByTitle(course.getTitle());
//        if (existingCourse != null) {
//            validator.putIssue("title", "validator.titleTaken");
//        }
//    }

    protected Integer getEntityId(HttpServletRequest req) {
        String path = req.getRequestURI();
        String servletPath = req.getServletPath();
        String[] slicedPath = path.split("/");
        String[] slicedServletPath = servletPath.split("/");
        String variableString = slicedPath[slicedPath.length - 1];
        if (slicedServletPath[slicedServletPath.length - 1].contains(variableString)) {
            return null;
        }
        return Integer.parseInt(variableString);
    }


//    /**
//     * Checks if the specified login unique
//     *
//     * @param usr       {@link User} to check
//     * @param validator {@link Validator} entity for testing.
//     */
//    protected void checkUserUniqueness(User usr, Validator validator) {
//        User existingUser = getUserService().getByLogin(usr.getLogin(), usr.getId());
//        if (existingUser != null) {
//
//            validator.putIssue("login", "validator.loginTaken");
//        }
//        existingUser = getService().getByEmail(usr.getEmail(), usr.getId());
//        if (existingUser != null) {
//
//            validator.putIssue("email", "validator.emailTaken");
//        }
//    }

}

