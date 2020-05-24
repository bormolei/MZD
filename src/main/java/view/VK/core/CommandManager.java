package view.VK.core;

import model.Bot;
import view.VK.core.commands.Unknown;

import java.util.HashSet;

/**
 * @author Arthur Kupriyanov
 */
public class CommandManager {
    private static HashSet<Command> commands = new HashSet<>();

    static {
        commands.add(new Unknown("unknown"));
             commands.add(new Bot("лох"));

    }

    public static HashSet<Command> getCommands(){
        return commands;
    }
    public static void addCommand(Command command) { commands.add(command);}
}
