package me.vesder.blazeydoublejump.commands.subcommands;

import me.vesder.blazeydoublejump.commands.CommandManager;
import me.vesder.blazeydoublejump.commands.SubCommand;
import org.bukkit.entity.Player;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static me.vesder.blazeydoublejump.commands.CommandManager.getSubCommand;
import static me.vesder.blazeydoublejump.commands.CommandManager.getSubCommandNames;
import static me.vesder.blazeydoublejump.commands.CommandManager.getSubCommands;
import static me.vesder.blazeydoublejump.utils.TextUtils.color;

public class HelpCommand implements SubCommand {

    private final Map<String, String> cachedHelpMessages = new HashMap<>();

    private static final String HEADER = "&b=====&6===== &d&lBlazeyDoubleJump &6=====&b=====&f\n \n";
    private static final String FOOTER = "&b=====&6=============================&b=====";

    private String getDefaultHelpMessage() {

        String commandName = "help";

        if (cachedHelpMessages.containsKey(commandName)) {
            return cachedHelpMessages.get(commandName);
        }

        StringBuilder helpMessageBuilder = new StringBuilder();
        helpMessageBuilder.append(HEADER);

        for (SubCommand subCommand : getSubCommands()) {
            helpMessageBuilder.append(subCommand.getSyntax())
                .append("\n")
                .append(subCommand.getDescription())
                .append("\n \n");
        }

        helpMessageBuilder.append(FOOTER);
        cachedHelpMessages.put(commandName, color(helpMessageBuilder.toString()));

        return cachedHelpMessages.get(commandName);
    }

    private String getHelpMessage(String commandName) {

        if (cachedHelpMessages.containsKey(commandName)) {
            return cachedHelpMessages.get(commandName);
        }

        StringBuilder helpMessageBuilder = new StringBuilder();
        helpMessageBuilder.append(HEADER);

        for (String subArg : getSubCommand(commandName).getSubcommandArguments(null, new String[2])) {
            helpMessageBuilder.append("/bdj ").append(commandName).append(" ").append(subArg)
                .append("\n \n");
        }

        helpMessageBuilder.append(FOOTER);
        cachedHelpMessages.put(commandName, color(helpMessageBuilder.toString()));

        return cachedHelpMessages.get(commandName);
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
        return "/bdj help <command>";
    }

    @Override
    public void perform(Player player, String[] args) {

        if (args.length >= 2 && getSubCommandNames().contains(args[1].toLowerCase())) {
            player.sendMessage(getHelpMessage(args[1].toLowerCase()));
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
