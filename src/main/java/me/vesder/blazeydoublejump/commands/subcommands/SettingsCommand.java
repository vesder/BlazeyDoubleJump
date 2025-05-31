package me.vesder.blazeydoublejump.commands.subcommands;

import me.vesder.blazeydoublejump.commands.SubCommand;
import me.vesder.blazeydoublejump.utils.TextUtils;
import org.bukkit.entity.Player;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

import static me.vesder.blazeydoublejump.commands.CommandManager.getSubCommand;
import static me.vesder.blazeydoublejump.utils.ConfigUtils.getDoubleConfig;
import static me.vesder.blazeydoublejump.utils.ConfigUtils.setConfig;
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
    public void perform(Player player, String[] args) {

        if (args.length >= 3) {
            switch (args[1].toLowerCase()) {
                case "infinitejump":
                    setConfig(args[0] + "." + args[1], Boolean.valueOf(args[2]));
                    player.sendMessage(TextUtils.color(String.format("&aSetting '%s' successfully updated to '%s'!", args[1], args[2])));
                    return;
                case "launch":
                case "launchy":
                case "cooldown":
                    try {
                        setConfig(args[0] + "." + args[1], Double.valueOf(args[2]));
                        player.sendMessage(TextUtils.color(String.format("&aSetting '%s' successfully updated to '%s'!", args[1], args[2])));
                        return;
                    } catch (NumberFormatException ex) {
                        player.sendMessage(TextUtils.color("&cInvalid number. Please enter a valid numeric value."));
                        getLogger().log(Level.WARNING, "Unexpected exception while updating config", ex);
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
