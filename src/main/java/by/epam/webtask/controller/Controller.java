package by.epam.webtask.controller;

import by.epam.webtask.controller.command.*;
import by.epam.webtask.controller.command.Impl.CommandProvider;
import by.epam.webtask.model.pool.ConnectionPoolManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet("/controller")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class Controller extends HttpServlet {
    public static final String COMMAND_NAME_PARAM = "command";
    private static final long serialVersionUID = -5223997271791449828L;
    private static final Logger LOG = LogManager.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) {
        final String commandName = req.getParameter(COMMAND_NAME_PARAM);
        final Command command = CommandProvider.getInstance().of(commandName);
        final CommandRequest commandRequest = new WrappingCommandRequest(req);
        final CommandResponse commandResponse = command.execute(commandRequest);
        if (commandResponse.getPath() != null &&
                !commandResponse.getPath().contains(CommandType.SWITCH_LOCALE.name().toLowerCase())) {
            req.getSession().setAttribute(Attribute.CURRENT_PAGE, commandResponse.getPath());
        }
        proceedWithResponse(req, resp, commandResponse);
    }

    private void proceedWithResponse(HttpServletRequest req, HttpServletResponse resp,
                                     CommandResponse commandResponse) {
        try {
            if (commandResponse.isAjax()) {
                processAjax(resp, commandResponse);
            } else {
                forwardOrRedirectToResponseLocation(req, resp, commandResponse);
            }
        } catch (ServletException e) {
            LOG.error("servlet exception occurred", e);
        } catch (IOException e) {
            LOG.error("IO exception occurred", e);
        }
    }

    private void processAjax(HttpServletResponse resp, CommandResponse commandResponse) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(commandResponse.getAjaxData());
    }

    private void forwardOrRedirectToResponseLocation(HttpServletRequest req, HttpServletResponse resp,
                                                     CommandResponse commandResponse) throws IOException, ServletException {
        if (commandResponse.isRedirect()) {
            resp.sendRedirect(commandResponse.getPath());
        } else {
            final String desiredPath = commandResponse.getPath();
            final RequestDispatcher dispatcher = req.getRequestDispatcher(desiredPath);
            dispatcher.forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        ConnectionPoolManager.getInstance().shutDown();
    }
}
