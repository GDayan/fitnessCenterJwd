package by.epam.webtask.controller.command.impl;

import by.epam.webtask.controller.Router;
import by.epam.webtask.controller.command.Command;
import by.epam.webtask.util.LanguageUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.epam.webtask.controller.RequestParameter.CURRENT_PAGE;
import static by.epam.webtask.controller.RequestParameter.LANGUAGE;

public class ChangeLanguageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Router router = new Router();
        String currentPage = (String) session.getAttribute(CURRENT_PAGE);
        String language = request.getParameter(LANGUAGE);
        logger.log(Level.INFO,"Language parameter is " + language);
        if(!LanguageUtil.isCorrectLanguage(language)){
            router.setCurrentPage(currentPage);
            return router;
        }
        logger.log(Level.INFO,"Language parameter is " + language);
        logger.log(Level.INFO,"Current page is " + currentPage);
        session.setAttribute(LANGUAGE,language);
        router.setRedirectType();
        router.setCurrentPage(currentPage);
        return router;
    }
}
