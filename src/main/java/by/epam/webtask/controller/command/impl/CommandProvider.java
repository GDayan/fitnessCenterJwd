package by.epam.webtask.controller.command.Impl;

import by.epam.webtask.controller.ResponseCreator;
import by.epam.webtask.controller.SimpleResponseCreator;
import by.epam.webtask.controller.command.Command;
import by.epam.webtask.controller.command.CommandType;

import java.util.EnumMap;


public class CommandProvider {
    private final EnumMap<CommandType, Command> commands = new EnumMap(CommandType.class);
    private final ResponseCreator responseCreator = SimpleResponseCreator.getInstance();

    private CommandProvider() {


    }

    public static CommandProvider getInstance() {
        return CommandProviderHolder.INSTANCE;
    }

    public Command of(String name) {
        return commands.get(CommandType.of(name));
    }

    private static class CommandProviderHolder {
        private static final CommandProvider INSTANCE = new CommandProvider();
    }
}
