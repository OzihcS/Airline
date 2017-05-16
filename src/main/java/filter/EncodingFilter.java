package filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Sets request charset encoding.
 *
 * @author Anton Piontkowski
 */
@WebFilter(filterName = "EncodingFilter")
public class EncodingFilter extends BaseFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(EncodingFilter.class);
    private static final String DEFAULT_ENCODING = "UTF-8";
    private String encoding;

    public void init(FilterConfig fConfig) throws ServletException {
        LOGGER.trace("Filter initialization starts");
        encoding = fConfig.getInitParameter("encoding");
        if (encoding == null) {
            LOGGER.trace("Encoding from Filter Config --> {} ",  encoding);
            encoding = DEFAULT_ENCODING;
        }
        LOGGER.trace("Filter initialization finished");
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        LOGGER.debug("Filter starts");
        request.setCharacterEncoding(encoding);
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding(encoding);

        LOGGER.debug("Filter finished");
        chain.doFilter(request, response);
    }


    public void destroy() {
        LOGGER.debug("Filter destruction starts");
        LOGGER.debug("Filter destruction finished");
    }
}