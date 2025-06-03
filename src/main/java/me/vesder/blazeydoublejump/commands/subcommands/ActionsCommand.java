package me.vesder.blazeydoublejump.commands.subcommands;

import me.vesder.blazeydoublejump.commands.SubCommand;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.List;

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
    public List<String> getSubcommandArguments(Player player, String[] args) {

        return new ArrayList<>(configReader(String.join(".", args)));
    }
}
