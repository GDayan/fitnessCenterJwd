package by.epam.webtask.controller.command;

public interface CommandResponse {
    boolean isRedirect();

    boolean isAjax();

    String getPath();

    String getAjaxData();
}