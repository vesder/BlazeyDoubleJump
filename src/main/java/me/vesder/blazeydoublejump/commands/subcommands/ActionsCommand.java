package me.vesder.blazeydoublejump.commands.subcommands;

import me.vesder.blazeydoublejump.commands.SubCommand;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class ActionsCommand implements SubCommand {
    @Override
    public String getName() {
        return "actions";
    }

    @Override
    public String getDescription() {
        return "Actions Desc";
    }

    @Override
    public String getSyntax() {
        return "/bdj actions";
    }

    @Override
    public void perform(Player player, String[] args) {

    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {
        return Collections.emptyList();
    }
}
