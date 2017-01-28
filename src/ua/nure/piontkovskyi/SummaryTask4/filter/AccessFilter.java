package ua.nure.piontkovskyi.SummaryTask4.filter;

import ua.nure.piontkovskyi.SummaryTask4.filter.security.AccessConfiguration;
import ua.nure.piontkovskyi.SummaryTask4.filter.security.ActionAccessConfiguration;
import ua.nure.piontkovskyi.SummaryTask4.filter.security.ModuleAccessConfiguration;
import ua.nure.piontkovskyi.SummaryTask4.filter.security.Tuple;
import ua.nure.piontkovskyi.SummaryTask4.entity.User;
import ua.nure.piontkovskyi.SummaryTask4.entity.enums.Role;
import ua.nure.piontkovskyi.SummaryTask4.util.constants.Constants;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filters request and defines whether the current user can access specified path.
 * In case of denied access redirects to the path that can be accessed.
 */
@WebFilter(filterName = "AccessFilter")
public class AccessFilter extends BaseFilter {

    private static final AccessConfiguration[] CONFIGS = new AccessConfiguration[]{
            new ActionAccessConfiguration(
                    Role.DISPATCHER,
                    Constants.ServletPaths.ERROR,
                    Constants.ServletPaths.Dispatcher.MAIN,
                    Constants.ServletPaths.Dispatcher.CREATE_BRIGADE,
                    Constants.ServletPaths.Dispatcher.CHANGE_STATUS,
                    Constants.ServletPaths.Dispatcher.FLIGHTS,
                    Constants.ServletPaths.Dispatcher.NEW_REQUEST
            ),
            new ModuleAccessConfiguration(
                    Role.ADMINISTRATOR,
                    Constants.ServletPaths.Admin.ADMIN,
                    Constants.ServletPaths.ERROR,
                    Constants.ServletPaths.Admin.PICK_UP
            )
    };

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpSession session = request.getSession();
        String path = request.getServletPath();
        User user = (User) session.getAttribute(Constants.Attributes.CURRENT_USER);
        String redirect = isAllowed(path, user);
        if (redirect != null) {
            response.sendRedirect(redirect);
            return;
        }
        chain.doFilter(request, response);
    }

    private String isAllowed(String path, User user) {
        for (AccessConfiguration config : CONFIGS) {
            Tuple<Boolean, Boolean> t = config.isAllowed(path, user);
            if (t.getSecondEntity()) {
                continue;
            }
            return t.getFirstEntity() ? null : config.getRedirect();
        }
        return null;
    }

}


