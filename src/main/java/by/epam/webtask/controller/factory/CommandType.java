package by.epam.webtask.controller.factory;

import by.epam.webtask.controller.command.Command;
import by.epam.webtask.controller.command.impl.*;

import java.util.Optional;

public enum CommandType {
    CHANGE_LANGUAGE(new ChangeLanguageCommand()),
    REGISTRATION(new RegistrationCommand()),
    SHOW_SIGN_IN(new ShowSignInCommand()),
    SIGN_IN(new SignInCommand()),
    SHOW_REGISTRATION(new ShowRegistrationCommand()),
    SHOW_TRAINER(new ShowTrainerCommand()),
    SHOW_GUEST(new ShowGuestCommand()),
    SHOW_PRICING(new ShowPricingCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

    public static Optional<Command> provideCommand(String command) {
        Optional<Command> resultCommand;
        if (command == null || command.isEmpty()) {
            return Optional.empty();
        }
        try {
            CommandType commandType = CommandType.valueOf(command.toUpperCase());
            resultCommand = Optional.of(commandType.getCommand());
        } catch (IllegalArgumentException exp) {
            resultCommand = Optional.empty();
        }
        return resultCommand;
    }
}
