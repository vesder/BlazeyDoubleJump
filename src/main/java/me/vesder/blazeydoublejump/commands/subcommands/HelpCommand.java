package me.vesder.blazeydoublejump.commands.subcommands;

import me.vesder.blazeydoublejump.commands.CommandManager;
import me.vesder.blazeydoublejump.commands.SubCommand;
import org.bukkit.entity.Player;
import java.util.Collections;
import java.util.List;

import static me.vesder.blazeydoublejump.commands.CommandManager.getSubCommands;
import static me.vesder.blazeydoublejump.utils.TextUtils.color;

public class HelpCommand implements SubCommand {

    private String defaultHelpMessage;

    private String getDefaultHelpMessage() {

        if (defaultHelpMessage != null) {
            return defaultHelpMessage;
        }

        StringBuilder message = new StringBuilder();
        message.append("&b=====&6===== &d&lBlazeyDoubleJump &6=====&b=====")
            .append("&f\n \n");

        for (SubCommand subCommand : getSubCommands()) {
            message.append(subCommand.getSyntax())
                .append("\n")
                .append(subCommand.getDescription())
                .append("\n \n");
        }

        message.append("&b=====&6=============================&b=====");

        return defaultHelpMessage = color(message.toString());
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "help desc";
    }

    @Override
    public String getSyntax() {
        return "/bdj help";
    }

    @Override
    public void perform(Player player, String[] args) {

        if (args.length >= 2 && args[1].equalsIgnoreCase("settings")) {
            player.sendMessage("MOOOW");
            return;
        }

        player.sendMessage(getDefaultHelpMessage());
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {

        if (args.length == 2) {
            return CommandManager.getSubCommandNames(getName());
        }

        return Collections.emptyList();
    }
}
