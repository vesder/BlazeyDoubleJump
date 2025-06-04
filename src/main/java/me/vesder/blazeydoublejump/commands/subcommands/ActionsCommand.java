package me.vesder.blazeydoublejump.commands.subcommands;

import me.vesder.blazeydoublejump.commands.SubCommand;
import me.vesder.blazeydoublejump.utils.VoidUtils;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static me.vesder.blazeydoublejump.commands.CommandManager.getSubCommand;
import static me.vesder.blazeydoublejump.config.ConfigUtils.configReader;
import static me.vesder.blazeydoublejump.config.ConfigUtils.setConfig;

public class ActionsCommand implements SubCommand {

    @Override
    public String getName() {
        return "actions";
    }

    @Override
    public String getDescription() {
        return "Modify plugin actions";
    }

    @Override
    public String getSyntax() {
        return "/bdj actions <option> <value>";
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
                    setConfig(path, value);
                    VoidUtils.sendMessageAdmin(player, "success", path, value);
                    return;
                case "onjump":
                case "onactive":
                    switch (args[2].toLowerCase()) {
                        case "message":
                        case "sound":
                        case "command":
                            path = args[0] + "." + args[1] + "." + args[2];
                            value = String.join(" ", Arrays.copyOfRange(args, 3, args.length));
                            setConfig(path, value);
                            VoidUtils.sendMessageAdmin(player, "success", path, value);
                            return;
                    }
                    break;
                case "errors":
                    switch (args[2].toLowerCase()) {
                        case "alreadyjumped":
                        case "cooldown":
                        case "notavailable":
                            switch (args[3]) {
                                case "message":
                                case "sound":
                                case "command":
                                    path = args[0] + "." + args[1] + "." + args[2] + "." + args[3];
                                    value = String.join(" ", Arrays.copyOfRange(args, 4, args.length));
                                    setConfig(path, value);
                                    VoidUtils.sendMessageAdmin(player, "success", path, value);
                                    return;
                            }
                    }
                    break;
            }
        }

        getSubCommand("help").perform(player, new String[]{"help", "actions"});
    }

    @Override
    public List<String> getSubcommandArguments(String[] args) {

        return new ArrayList<>(configReader(String.join(".", Arrays.copyOfRange(args, 0, args.length - 1))));
    }
}
