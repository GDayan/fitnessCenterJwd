package by.epam.webtask.controller.filter.permission;

import by.epam.webtask.controller.factory.CommandType;

import java.util.Set;

public enum UserPermission {
    ADMIN(Set.of(CommandType.CHANGE_LANGUAGE.name(),
            CommandType.REGISTRATION.name(),
            CommandType.SHOW_SIGN_IN.name(),
            CommandType.SIGN_IN.name(),
            CommandType.SHOW_PRICING.name(),
            CommandType.SHOW_TRAINER.name(),
            CommandType.SHOW_GUEST.name(),
            CommandType.SHOW_CONTACTS.name(),
            CommandType.SHOW_REGISTRATION.name())),
    CLIENT(Set.of(CommandType.CHANGE_LANGUAGE.name(),
            CommandType.SHOW_TRAINER.name(),
            CommandType.SHOW_GUEST.name(),
            CommandType.SHOW_PRICING.name())),
    TRAINER(Set.of(CommandType.CHANGE_LANGUAGE.name(),
            CommandType.SHOW_TRAINER.name(),
            CommandType.SHOW_GUEST.name(),
            CommandType.SHOW_PRICING.name())),
    GUEST(Set.of(CommandType.CHANGE_LANGUAGE.name(),
            CommandType.SHOW_TRAINER.name(),
            CommandType.REGISTRATION.name(),
            CommandType.SHOW_SIGN_IN.name(),
            CommandType.SIGN_IN.name(),
            CommandType.SHOW_CONTACTS.name(),
            CommandType.SHOW_REGISTRATION.name(),
            CommandType.SHOW_GUEST.name(),
            CommandType.SHOW_PRICING.name()));

    private Set<String> commands;

    UserPermission(Set<String> commands){
        this.commands = commands;
    }

    public Set<String> getCommands(){
        return commands;
    }
}
