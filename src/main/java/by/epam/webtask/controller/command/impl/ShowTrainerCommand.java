package by.epam.webtask.controller.command.impl;

import by.epam.webtask.controller.Router;
import by.epam.webtask.controller.command.Command;
import jakarta.servlet.http.HttpServletRequest;

import static by.epam.webtask.controller.PathPage.TRAINER_PAGE;

public class ShowTrainerCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        router.setCurrentPage(TRAINER_PAGE);
        return router;
    }
}
