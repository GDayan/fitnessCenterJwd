package by.epam.webtask.controller.command;

import by.epam.webtask.controller.Router;
import by.epam.webtask.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public interface Command {
    Router execute(HttpServletRequest request) throws CommandException;
}