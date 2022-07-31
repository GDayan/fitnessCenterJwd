package by.epam.webtask.controller.command;

public interface Command {
    /**
     * Executes command
     *
     * @param request request wrapped with CommandRequest
     * @return CommandResponse containing the path and type of response
     */
    CommandResponse execute(CommandRequest request);
}