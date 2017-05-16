package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Base class for filters. Handles only {@link HttpServletRequest}
 */
public abstract class BaseFilter implements Filter {

    public void init(FilterConfig fConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        doFilter(httpServletRequest, httpServletResponse, chain);
    }

    /**
     * Operates only with {@code HttpServletRequest} and {@code HttpServletResponse} objects.
     *
     * @param request  request to filter
     * @param response response
     * @param chain    filter chain
     */
    public abstract void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException;

    public void destroy() {

    }


}
