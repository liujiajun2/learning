package xin.liujiajun.redis.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author liujiajun
 * @date 2020-01-13 19:42
 **/
public class AccessLogFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(AccessLogFilter.class);

    private static final int SLOW = 500;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        AccessLog log = new AccessLog("API_CALL", servletRequest);
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        log.setToken(request.getHeader("Authrosization"));

        try {
            if (servletRequest.getContentType() == null ||
                    servletRequest.getContentType().contains("json") ||
                    servletRequest.getContentType().contains("xml")) {

                handleLog(log);
            }
        } finally {
            filterChain.doFilter(servletRequest, servletResponse);
            handleCostTime(log.getUrl(), System.currentTimeMillis() - start);
        }

    }

    private void handleLog(AccessLog log) {
        logger.info(log.toLogStr());
    }

    private void handleCostTime(String url, long costTime) {
        if (costTime > SLOW) {
            logger.warn("[Access-Slow] {} cost time : {} ms", url, costTime);
        } else {
            logger.info("[Access] {} cost time : {} ms", url, costTime);
        }
    }

    @Override
    public void destroy() {

    }
}
