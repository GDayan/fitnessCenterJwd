package by.epam.webtask.controller.filter;

import by.epam.webtask.controller.factory.CommandType;
import by.epam.webtask.controller.filter.permission.UserPermission;
import by.epam.webtask.model.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

import static by.epam.webtask.controller.RequestParameter.COMMAND;
import static by.epam.webtask.controller.RequestParameter.USER;

import static by.epam.webtask.controller.PathPage.ERROR_404;

@WebFilter(urlPatterns =  "/command/*" , filterName = "PageSecurityCommandFilter")
public class PageSecurityCommandFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();

    public void init(FilterConfig config) throws ServletException {
        logger.log(Level.INFO,"PageSecurityFilter: method - init");
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        logger.log(Level.INFO,"PageSecurityFilter - doFilter");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();
        String command = httpServletRequest.getParameter(COMMAND);
        if (command == null){
            logger.log(Level.INFO, "command = " + command);
            request.getRequestDispatcher(ERROR_404).forward(httpServletRequest,httpServletResponse);
            return;
        }
        User.UserRole role = User.UserRole.TRAINER;
        Set<String> commands;
        User user = (User) session.getAttribute(USER);
        if(user != null){
            role = user.getRole();
        }
        switch (role){
            case ADMIN -> {
                commands = UserPermission.ADMIN.getCommands();
            }
            case CLIENT -> {
                commands = UserPermission.CLIENT.getCommands();
            }
            default -> {
                commands = UserPermission.TRAINER.getCommands();
            }
        }
        boolean isCorrect = Arrays.stream(CommandType.values())
                .anyMatch(commandType -> command.equalsIgnoreCase(commandType.toString()));
        if(isCorrect && !commands.contains(command.toUpperCase())){
            logger.log(Level.INFO,"isCorrect = " + isCorrect + "command = " + command);
            httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        if (!commands.contains(command.toUpperCase())){
            logger.log(Level.INFO, "command = " + command);
            request.getRequestDispatcher(ERROR_404)
                    .forward(httpServletRequest,httpServletResponse);
            return;
        }
        logger.log(Level.INFO, "Chain continue");
        chain.doFilter(request, response);
    }

}
