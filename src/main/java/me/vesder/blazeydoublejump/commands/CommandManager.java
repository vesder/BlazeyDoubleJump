package me.vesder.blazeydoublejump.commands;

import me.vesder.blazeydoublejump.commands.subcommands.HelpCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandManager implements TabExecutor {

    private static final List<SubCommand> subCommands = new ArrayList<>();
    private static final List<String> subCommandNames = new ArrayList<>();

    static {

        subCommands.add(new HelpCommand());

        for (SubCommand subCommand : getSubCommands()) {

            subCommandNames.add(subCommand.getName());
        }

    }

    public static List<SubCommand> getSubCommands() {
        return subCommands;
    }

    /**
     * Returns a list of all subcommand names, excluding any names provided.
     */
    public static List<String> getSubCommandNames(String... excludedNames) {

        List<String> filteredNames = new ArrayList<>(subCommandNames);

        for (String excludedName : excludedNames) {
            filteredNames.remove(excludedName);
        }

        return filteredNames;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {

            for (SubCommand subCommand : getSubCommands()) {
                if (subCommand.getName().equalsIgnoreCase("help")) {
                    subCommand.perform(player, args);
                    break;
                }
            }

            return true;
        }

        for (SubCommand subCommand : getSubCommands()) {
            if (args[0].equalsIgnoreCase(subCommand.getName())) {
                subCommand.perform(player, args);
            }
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        if (args.length == 1) {

            return getSubCommandNames();
        }

        if (args.length >= 2) {
            for (SubCommand subCommand : getSubCommands()) {
                if (args[0].equalsIgnoreCase(subCommand.getName())) {
                    return subCommand.getSubcommandArguments((Player) sender, args);
                }
            }
        }

        return Collections.emptyList();
    }
}
