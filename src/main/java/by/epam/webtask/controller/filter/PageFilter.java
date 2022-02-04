package by.epam.webtask.controller.filter;

import by.epam.webtask.controller.filter.permission.PagePermission;
import by.epam.webtask.model.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

import static by.epam.webtask.controller.PathPage.SIGN_PAGE;
import static by.epam.webtask.controller.RequestParameter.USER;

@WebFilter(urlPatterns =  "/jsp/*" , filterName = "PageFilter")
public class PageFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();
        String requestURI = httpRequest.getServletPath();
        logger.log(Level.INFO,"Page URI: " + requestURI);

        User.UserRole userRole = User.UserRole.GUEST;
        User user = (User) session.getAttribute(USER);
        if(user != null){
            userRole = user.getRole();
            logger.log(Level.INFO, userRole.toString());
        }
        logger.log(Level.INFO,userRole);
        boolean isCorrect;
        Set<String> pages;
        switch (userRole){
            case ADMIN -> {
                pages = PagePermission.ADMIN.getUserPages();
                isCorrect = pages.stream().anyMatch(requestURI::contains);
            }
            case CLIENT -> {
                pages = PagePermission.CLIENT.getUserPages();
                logger.log(Level.INFO,pages);
                isCorrect = pages.stream().anyMatch(requestURI::contains);
            }
            case TRAINER -> {
                pages = PagePermission.TRAINER.getUserPages();
                logger.log(Level.INFO,pages);
                isCorrect = pages.stream().anyMatch(requestURI::contains);
            }
            default -> {
                pages = PagePermission.GUEST.getUserPages();
                isCorrect = pages.stream().anyMatch(requestURI::contains);
            }
        }
        if(!isCorrect && user == null){
            logger.log(Level.INFO,"isCorrect = " + isCorrect);
            user = new User();
            user.setRole(User.UserRole.GUEST);
            session.setAttribute(USER,user);
            httpRequest.getRequestDispatcher(SIGN_PAGE)//TODO нормально на такую страницу возвращать?
                    .forward(httpRequest,httpResponse);
            return;
        }else if(!isCorrect && user != null){
            httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        chain.doFilter(request,response);
    }
}
