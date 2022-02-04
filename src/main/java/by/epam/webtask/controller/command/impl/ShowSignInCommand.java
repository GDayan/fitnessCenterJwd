package by.epam.webtask.controller.command.impl;

import by.epam.webtask.controller.Router;
import by.epam.webtask.controller.command.Command;
import jakarta.servlet.http.HttpServletRequest;

import static by.epam.webtask.controller.PathPage.SIGN_PAGE;

public class ShowSignInCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        router.setCurrentPage(SIGN_PAGE);
        return router;
    }
}
