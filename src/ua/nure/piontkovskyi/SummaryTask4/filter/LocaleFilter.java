package ua.nure.piontkovskyi.SummaryTask4.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.piontkovskyi.SummaryTask4.util.constants.Constants;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Defines and sets current locale
 */
@WebFilter(filterName = "LocaleFilter")
public class LocaleFilter extends BaseFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(LocaleFilter.class);

    private static final String COOKIES_LOCALE = "lang";
    private static final String COMMON_DEFAULT_LOCALE = "en";
    private String[] locales;
    private ServletContext servletContext;
    private String defaultLocale;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.debug("Init starts.");
        loadLocales(filterConfig);
        LOGGER.debug("Init finished.");
    }


    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String locale = request.getParameter(Constants.Attributes.LANG);

        if (locale == null) {
            locale = getLocaleFromSession(request);
        }

        LOGGER.info("Locale filter set locale to " + locale);
        setLocale(request, response, locale);
        chain.doFilter(request, response);
    }

    /**
     * Loads locales from {@link ServletContext}
     *
     * @param filterConfig {@link FilterConfig} locales to be loaded from.
     */
    private void loadLocales(FilterConfig filterConfig) {
        locales = filterConfig.getServletContext().getInitParameter(Constants.Attributes.LOCALES).split(" ");

        if (locales.length == 0) {
            LOGGER.error("Locales loading error, locale are set to default - " + COMMON_DEFAULT_LOCALE);
            defaultLocale = COMMON_DEFAULT_LOCALE;
            locales = new String[]{defaultLocale};
        } else {
            defaultLocale = locales[0];
        }
        servletContext = filterConfig.getServletContext();
        servletContext.setAttribute(Constants.Attributes.LOCALES, locales);
        servletContext.setAttribute(Constants.Attributes.DEFAULT_LOCALE, defaultLocale);

        LOGGER.debug("Default locale loaded to " + defaultLocale);
    }

    private String getLocaleFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String locale = defineLocale((String) session.getAttribute(Constants.Attributes.CURRENT_LOCALE));
        return locale == null ? getLocaleFromCookies(request) : locale;
    }

    private String getLocaleFromCookies(HttpServletRequest request) {
        Cookie cookie = getCookie(request, COOKIES_LOCALE);
        String locale = defineLocale(cookie != null ? cookie.getValue() : null);
        return locale == null ? getLocaleFromRequest(request) : locale;
    }

    private String getLocaleFromRequest(HttpServletRequest request) {
        String locale = defineLocale(request.getLocale().getLanguage());
        return locale == null ? defaultLocale : locale;
    }

    /**
     * Check if locale are proper to existing locales
     *
     * @param locale declared locale
     * @return existing locale if exists
     */
    private String defineLocale(String locale) {
        for (String l : locales) {
            if (l.equals(locale)) {
                return l;
            }
        }
        return null;
    }

    private Cookie getCookie(HttpServletRequest request, String locale) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(locale)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    private void setLocale(HttpServletRequest request, HttpServletResponse response, String locale) {
        request.setAttribute(Constants.Attributes.CURRENT_LOCALE, locale);
        setLocaleInSession(request, locale);
        setLocaleInCookies(response, locale);
    }

    private void setLocaleInSession(HttpServletRequest request, String locale) {
        HttpSession session = request.getSession();
        session.setAttribute(Constants.Attributes.CURRENT_LOCALE, locale);
    }

    private void setLocaleInCookies(HttpServletResponse response, String locale) {
        Cookie cookie = new Cookie(COOKIES_LOCALE, locale);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(Integer.MAX_VALUE);
        response.addCookie(cookie);
    }

}

