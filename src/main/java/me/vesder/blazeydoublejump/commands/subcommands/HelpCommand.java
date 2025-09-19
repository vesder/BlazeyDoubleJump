package me.vesder.blazeydoublejump.commands.subcommands;

import me.vesder.blazeydoublejump.commands.CommandManager;
import me.vesder.blazeydoublejump.commands.SubCommand;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

import static me.vesder.blazeydoublejump.commands.CommandManager.getSubCommand;
import static me.vesder.blazeydoublejump.commands.CommandManager.getSubCommandNames;
import static me.vesder.blazeydoublejump.commands.CommandManager.getSubCommands;
import static me.vesder.blazeydoublejump.utils.Utils.color;

public class HelpCommand implements SubCommand {

    private static final String HEADER = "&b=====&6===== &d&lBlazeyDoubleJump &6=====&b=====&f\n \n";
    private static final String FOOTER = "&b=====&6=============================&b=====";

    private String getDefaultHelpMessage(CommandSender sender) {

        StringBuilder helpMessageBuilder = new StringBuilder();
        helpMessageBuilder.append(HEADER);

        for (SubCommand subCommand : getSubCommands()) {

            if (!sender.hasPermission(subCommand.getPermission())) {
                continue;
            }

            helpMessageBuilder.append(subCommand.getSyntax())
                .append("\n")
                .append(subCommand.getDescription())
                .append("\n \n");
        }

        helpMessageBuilder.append(FOOTER);

        return color(helpMessageBuilder.toString());
    }

    private String getHelpMessage(CommandSender sender, String commandName) {

        StringBuilder helpMessageBuilder = new StringBuilder();
        helpMessageBuilder.append(HEADER);

        for (String subArg : getSubCommand(commandName).getSubcommandArguments(sender, new String[]{commandName, ""})) {
            helpMessageBuilder.append("/bdj ").append(commandName).append(" ").append(subArg)
                .append("\n \n");
        }

        helpMessageBuilder.append(FOOTER);

        return color(helpMessageBuilder.toString());
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Shows help information for commands";
    }

    @Override
    public String getSyntax() {
        return "/bdj help [command]";
    }

    @Override
    public String getPermission() {
        return "blazeydoublejump.command.help";
    }

    @Override
    public boolean allowConsole() {
        return true;
    }

    @Override
    public void perform(CommandSender sender, String[] args) {

        if (args.length >= 2 && getSubCommandNames().contains(args[1].toLowerCase())) {
            sender.sendMessage(getHelpMessage(sender, args[1].toLowerCase()));
            return;
        }

        sender.sendMessage(getDefaultHelpMessage(sender));
    }

    @Override
    public List<String> getSubcommandArguments(CommandSender sender, String[] args) {

        if (args.length == 2) {
            return CommandManager.getSubCommandNames(getName());
        }

        return Collections.emptyList();
    }
}
