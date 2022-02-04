package by.epam.webtask.controller.command.impl;

import by.epam.webtask.controller.Router;
import by.epam.webtask.controller.command.Command;
import jakarta.servlet.http.HttpServletRequest;

import static by.epam.webtask.controller.PathPage.GUEST_PAGE;

public class ShowGuestCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        router.setCurrentPage(GUEST_PAGE);
        return router;
    }
}
