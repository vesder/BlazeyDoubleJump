package me.vesder.blazeydoublejump.commands.subcommands;

import me.vesder.blazeydoublejump.commands.SubCommand;
import me.vesder.blazeydoublejump.config.ConfigManager;
import me.vesder.blazeydoublejump.config.ConfigUtils;
import me.vesder.blazeydoublejump.config.customconfigs.MessagesConfig;
import me.vesder.blazeydoublejump.config.customconfigs.SettingsConfig;
import me.vesder.blazeydoublejump.utils.Utils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import static me.vesder.blazeydoublejump.commands.CommandManager.getSubCommand;
import static me.vesder.blazeydoublejump.config.ConfigUtils.configReader;
import static org.bukkit.Bukkit.getLogger;

public class SettingsCommand implements SubCommand {

    @Override
    public String getName() {
        return "settings";
    }

    @Override
    public String getDescription() {
        return "Modify plugin settings";
    }

    @Override
    public String getSyntax() {
        return "/bdj settings <option> <value>";
    }

    @Override
    public String getPermission() {
        return "blazeydoublejump.command.settings";
    }

    @Override
    public void perform(Player player, String[] args) {

        if (args.length >= 3) {
            String value;
            String path;
            switch (args[1].toLowerCase()) {
                case "prefix":
                    path = args[0] + "." + args[1];
                    value = String.join(" ", Arrays.copyOfRange(args, 2, args.length));
                    ConfigUtils.set("settings.yml", path, value);
                    Utils.sendMessageAdmin(player, path, value);
                    return;
                case "infinitejump":
                    ConfigUtils.set("settings.yml", args[0] + "." + args[1], Boolean.valueOf(args[2]));
                    Utils.sendMessageAdmin(player, args[1], args[2]);
                    return;
                case "launch":
                case "launchy":
                case "cooldown":
                    try {
                        ConfigUtils.set("settings.yml", args[0] + "." + args[1], Double.valueOf(args[2]));
                        Utils.sendMessageAdmin(player, args[1], args[2]);
                        return;
                    } catch (NumberFormatException ex) {
                        if (!messagesConfig.getConfigEditorInvalidNumber().isEmpty()) {
                            player.sendMessage(Utils.color(settingsConfig.getPrefix() + messagesConfig.getConfigEditorInvalidNumber()));
                        }
                        getLogger().log(Level.WARNING, "Unexpected exception while updating config", ex);
                        break;
                    }
            }
        }

        getSubCommand("help").perform(player, new String[]{"help", "settings"});
    }

    @Override
    public List<String> getSubcommandArguments(CommandSender sender, String[] args) {

        return new ArrayList<>(configReader("settings.yml", String.join(".", Arrays.copyOfRange(args, 0, args.length - 1))));
    }

    SettingsConfig settingsConfig = (SettingsConfig) ConfigManager.getConfigManager().getCustomConfig("settings.yml");
    MessagesConfig messagesConfig = (MessagesConfig) ConfigManager.getConfigManager().getCustomConfig("messages.yml");

}
