package me.vesder.blazeydoublejump.commands.subcommands;

import me.vesder.blazeydoublejump.commands.SubCommand;
import org.bukkit.entity.Player;
import java.util.Arrays;
import java.util.Set;

import static me.vesder.blazeydoublejump.utils.ConfigUtils.configReader;

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

    }

    @Override
    public Set<String> getSubcommandArguments(String[] args) {

        return configReader(String.join(".", Arrays.copyOfRange(args, 0, args.length - 1)));
    }
}
