package by.epam.webtask.controller.command;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.util.Optional;

/**
 * Interface that wraps http request
 *
 * @author Pavel Kovalev
 * @version 1.0
 */
public interface CommandRequest {
    void addAttributeToJsp(String name, Object attribute);

    String getParameter(String name);

    String[] getParameterValues(String name);

    void addToSession(String name, Object value);

    HttpSession getSession();

    Optional<Object> retrieveFromSession(String name);

    Optional<Object> pullFromSession(String name);

    void removeFromSession(String name);

    void clearSession();

    void createSession();

    Optional<Part> getPart(String var);

    ServletContext getServletContext();
}
