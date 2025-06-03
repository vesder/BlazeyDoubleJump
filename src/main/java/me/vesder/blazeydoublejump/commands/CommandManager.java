package me.vesder.blazeydoublejump.commands;

import me.vesder.blazeydoublejump.commands.subcommands.ActionsCommand;
import me.vesder.blazeydoublejump.commands.subcommands.HelpCommand;
import me.vesder.blazeydoublejump.commands.subcommands.SettingsCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandManager implements TabExecutor {

    private static final Map<String, SubCommand> subCommandMap = new HashMap<>();

    static {

        registerSubCommands(

            new ActionsCommand(),
            new HelpCommand(),
            new SettingsCommand()

        );

    }

    private static void registerSubCommands(SubCommand... subCommands) {

        for (SubCommand subCommand : subCommands) {
            subCommandMap.put(subCommand.getName().toLowerCase(), subCommand);
        }

    }

    public static SubCommand getSubCommand(String name) {
        return subCommandMap.get(name.toLowerCase());
    }

    public static Collection<SubCommand> getSubCommands() {
        return subCommandMap.values();
    }

    /**
     * Returns a list of all subcommand names, excluding any names provided.
     */
    public static List<String> getSubCommandNames(String... excludedNames) {

        List<String> filteredNames = new ArrayList<>(subCommandMap.keySet());

        for (String excludedName : excludedNames) {
            filteredNames.remove(excludedName.toLowerCase());
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

        if (args.length >= 1 && getSubCommandNames().contains(args[0].toLowerCase())) {
            getSubCommand(args[0]).perform(player, args);
            return true;
        }

        getSubCommand("help").perform(player, args);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        if (args.length == 1) {
            return getSubCommandNames();
        }

        if (args.length >= 2 && getSubCommandNames().contains(args[0].toLowerCase())) {
            return getSubCommand(args[0]).getSubcommandArguments(args);
        }

        return Collections.emptyList();
    }
}
