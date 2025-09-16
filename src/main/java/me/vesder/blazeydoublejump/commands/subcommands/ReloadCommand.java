package me.vesder.blazeydoublejump.commands.subcommands;

import me.vesder.blazeydoublejump.commands.SubCommand;
import me.vesder.blazeydoublejump.config.ConfigManager;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

import static me.vesder.blazeydoublejump.commands.CommandManager.sendHelpMessage;

public class ReloadCommand implements SubCommand {

    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return "Reloads the plugin configuration files";
    }

    @Override
    public String getSyntax() {
        return "/bdj reload [config]";
    }

    @Override
    public String getPermission() {
        return "blazeydoublejump.command.reload";
    }

    @Override
    public boolean allowConsole() {
        return true;
    }

    @Override
    public void perform(CommandSender sender, String[] args) {

        if (args.length < 2) {

            ConfigManager.getConfigManager().load();
            return;
        }

        if (ConfigManager.getConfigManager().getCustomConfigNames().contains(args[1])) {
            ConfigManager.getConfigManager().load(args[1]);
            return;
        }

        sendHelpMessage(sender, getName());
    }

    @Override
    public List<String> getSubcommandArguments(CommandSender sender, String[] args) {

        if (args.length == 2) {
            return ConfigManager.getConfigManager().getCustomConfigNames();
        }

        return Collections.emptyList();
    }

}
