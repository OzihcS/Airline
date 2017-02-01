package ua.nure.piontkovskyi.SummaryTask4.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.piontkovskyi.SummaryTask4.util.constants.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * Servlet for instant locale changing
 */
@WebServlet(urlPatterns = {Constants.ServletPaths.LOCALE})
public class LocaleServlet extends BaseServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(LocaleServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        print(req, resp, getLocale(req));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newLocale = getStringParam(req, Constants.Attributes.NEW_LOCALE);
        req.getSession().setAttribute(Constants.Attributes.CURRENT_LOCALE, newLocale);
        req.setAttribute(Constants.Attributes.CURRENT_LOCALE, newLocale);
        LOGGER.debug("locale changed to {}", newLocale);
        if (Arrays.asList(getLocales()).contains(newLocale)) {
            req.getRequestDispatcher("locales/" + newLocale + ".json").forward(req, resp);
        }

    }
}
