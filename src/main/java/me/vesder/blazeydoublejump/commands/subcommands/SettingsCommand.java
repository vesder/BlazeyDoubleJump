package me.vesder.blazeydoublejump.commands.subcommands;

import me.vesder.blazeydoublejump.commands.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static me.vesder.blazeydoublejump.commands.CommandManager.getSubCommand;
import static me.vesder.blazeydoublejump.utils.ConfigUtils.getDoubleConfig;
import static me.vesder.blazeydoublejump.utils.ConfigUtils.setConfig;

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
    public void perform(Player player, String[] args) {

        if (args.length >= 3) {
            switch (args[1].toLowerCase()) {
                case "infinitejump":
                    player.sendMessage(String.format("Setting '%s' successfully updated to '%s'!", args[1], args[2]));
                    setConfig("Settings.InfiniteJump", Boolean.valueOf(args[2]));
                    return;
                case "launch":
                    try {
                        player.sendMessage(String.format("Setting '%s' successfully updated to '%s'!", args[1], args[2]));
                        setConfig("Settings.Launch", Double.valueOf(args[2]));
                        return;
                    } catch (NumberFormatException ex) {
                        Bukkit.getLogger().warning(ex.getMessage());
                        break;
                    }
                case "launchy":
                    try {
                        player.sendMessage(String.format("Setting '%s' successfully updated to '%s'!", args[1], args[2]));
                        setConfig("Settings.LaunchY", Double.valueOf(args[2]));
                        return;
                    } catch (NumberFormatException ex) {
                        Bukkit.getLogger().warning(ex.getMessage());
                        break;
                    }
                case "cooldown":
                    try {
                        player.sendMessage(String.format("Setting '%s' successfully updated to '%s'!", args[1], args[2]));
                        setConfig("Settings.Cooldown", Double.valueOf(args[2]));
                        return;
                    } catch (NumberFormatException ex) {
                        Bukkit.getLogger().warning(ex.getMessage());
                        break;
                    }
            }
        }

        getSubCommand("help").perform(player, new String[]{"help", "settings"});
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {

        if (args.length == 2) {
            return Arrays.asList("InfiniteJump", "Launch", "LaunchY", "Cooldown");
        }

        if (args.length == 3) {
            switch (args[1].toLowerCase()) {
                case "infinitejump":
                    return Arrays.asList("true", "false");
                case "launch":
                    return Collections.singletonList(String.valueOf(getDoubleConfig("Settings.Launch", 10D)));
                case "launchy":
                    return Collections.singletonList(String.valueOf(getDoubleConfig("Settings.LaunchY", 10D)));
                case "cooldown":
                    return Collections.singletonList(String.valueOf(getDoubleConfig("Settings.Cooldown")));
            }
        }

        return Collections.emptyList();
    }
}
