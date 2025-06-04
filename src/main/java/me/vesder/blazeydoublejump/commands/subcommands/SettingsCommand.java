package me.vesder.blazeydoublejump.commands.subcommands;

import me.vesder.blazeydoublejump.commands.SubCommand;
import me.vesder.blazeydoublejump.utils.VoidUtils;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import static me.vesder.blazeydoublejump.commands.CommandManager.getSubCommand;
import static me.vesder.blazeydoublejump.config.ConfigUtils.configReader;
import static me.vesder.blazeydoublejump.config.ConfigUtils.setConfig;
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
                    VoidUtils.sendMessageAdmin(player, "success", args[1], args[2]);
                    return;
                case "launch":
                case "launchy":
                case "cooldown":
                    try {
                        setConfig(args[0] + "." + args[1], Double.valueOf(args[2]));
                        VoidUtils.sendMessageAdmin(player, "success", args[1], args[2]);
                        return;
                    } catch (NumberFormatException ex) {
                        VoidUtils.sendMessageAdmin(player, "invalid-number");
                        getLogger().log(Level.WARNING, "Unexpected exception while updating config", ex);
                        break;
                    }
            }
        }

        getSubCommand("help").perform(player, new String[]{"help", "settings"});
    }

    @Override
    public List<String> getSubcommandArguments(String[] args) {

        return new ArrayList<>(configReader(String.join(".", Arrays.copyOfRange(args, 0, args.length - 1))));
    }
}
