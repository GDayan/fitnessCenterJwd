package by.epam.webtask.controller.command.impl;

import by.epam.webtask.controller.Router;
import by.epam.webtask.controller.command.Command;
import jakarta.servlet.http.HttpServletRequest;

import static by.epam.webtask.controller.PathPage.PRICING_PAGE;

public class ShowPricingCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        router.setCurrentPage(PRICING_PAGE);
        return router;
    }
}
