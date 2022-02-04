package by.epam.webtask.controller.filter;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.webtask.controller.RequestParameter.CURRENT_PAGE;

@WebFilter(urlPatterns =  "/*" , filterName = "CurrentPageFilter")
public class CurrentPageFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();
    private static final String CONTROLLER_PATTERN = "/controller?";

    @Override
    public void init(FilterConfig config) throws ServletException {
        logger.log(Level.INFO,"init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        logger.log(Level.INFO,"Current page");
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpSession session = servletRequest.getSession();
        String requestURI = servletRequest.getRequestURI();
        logger.log(Level.INFO,"request URI: " + requestURI);
        String query = servletRequest.getQueryString();
        if(query != null){
            requestURI = CONTROLLER_PATTERN + query;
        }
        logger.log(Level.INFO, query);
        session.setAttribute(CURRENT_PAGE, requestURI);
        chain.doFilter(request, response);
    }
    public void destroy() {
    }
}
