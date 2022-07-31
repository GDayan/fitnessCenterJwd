package by.epam.webtask.controller;

import by.epam.webtask.controller.command.CommandResponse;

public interface ResponseCreator {
    CommandResponse createForwardResponse(PagePath page);

    CommandResponse createRedirectResponse(PagePath page);

    CommandResponse createAjaxResponse(String data);
}
