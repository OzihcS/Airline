package ua.nure.piontkovskyi.SummaryTask4.servlet;

import ua.nure.piontkovskyi.SummaryTask4.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Basic servlet for all servlets. Provides access to
 * services that are needed for more than one servlet.
 */
public abstract class AbstractServlet<T> extends HttpServlet {

    private static final long serialVersionUID = -7053877476429245836L;
    private UserService userService;


    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        userService = (UserService) context.getAttribute(UserService.class.getName());
    }

    protected UserService getUserService() {
        return userService;
    }

    /**
     * Retrieves specified integer parameter from request.
     *
     * @param param The name of parameter to retrieve
     * @return int value of parameter
     */
    protected Integer getIntParam(HttpServletRequest request, String param) {
        String paramValue = request.getParameter(param);
        if (paramValue == null) {
            return null;
        }
        try {
            return Integer.parseInt(paramValue);
        } catch (NumberFormatException e) {
            return null;
        }
    }


    /**
     * Retrieves integer parameter from request.
     * Sets to default if parameter is null
     *
     * @param request      HttpServletRequest
     * @param param        The name of parameter to retrieve
     * @param defaultValue value to set if value from request is null.
     * @return int value of parameter.
     */
    protected int getIntParam(HttpServletRequest request, String param, int defaultValue) {
        Integer value = getIntParam(request, param);
        if (value == null || value < 1) {
            return defaultValue;
        }
        return value;
    }


    /**
     * Retrieves specified integer parameters from request.
     *
     * @param param The name of parameter to retrieve
     * @return <{@link List} list of integer values
     */
    protected List<Integer> getIntParamValues(HttpServletRequest request, String param) {
        String[] values = request.getParameterValues(param);
        if (values == null) {
            return null;
        }
        List<Integer> intValues = new ArrayList<>();
        for (String value : values) {
            try {
                Integer intVal = Integer.parseInt(value);
                intValues.add(intVal);
            } catch (NumberFormatException e) {
                intValues.add(null);
            }
        }
        return intValues;
    }

    /**
     * Retrieves specified String parameter from request.
     *
     * @param param The name of parameter to retrieve
     * @return {@link String} value of parameter
     */
    protected String getStringParam(HttpServletRequest request, String param) {
        String paramValue = request.getParameter(param);
        if (paramValue == null) {
            return null;
        }
        return paramValue.trim();
    }

    protected Boolean getBooleanParam(HttpServletRequest request, String param, Boolean def) {
        String strParamValue = request.getParameter(param);
        if (strParamValue == null) {
            return def;
        }
        return Boolean.valueOf(strParamValue);
    }

    /**
     * Retrieves String parameter from request.
     * Sets to default if parameter is null
     *
     * @param request      HttpServletRequest
     * @param defaultValue value to set if value from request is null.
     * @return String value of parameter.
     */
    protected String getStringParam(HttpServletRequest request, String paramName, String defaultValue) {
        String paramValue = getStringParam(request, paramName);
        if (paramValue == null || paramValue.isEmpty()) {
            return defaultValue;
        }
        return paramValue;
    }

}
