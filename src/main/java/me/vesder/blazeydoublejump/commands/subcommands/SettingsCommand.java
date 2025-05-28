package me.vesder.blazeydoublejump.commands.subcommands;

import me.vesder.blazeydoublejump.commands.SubCommand;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class SettingsCommand implements SubCommand {

    @Override
    public String getName() {
        return "settings";
    }

    @Override
    public String getDescription() {
        return "Settings desc";
    }

    @Override
    public String getSyntax() {
        return "/bdj settings";
    }

    @Override
    public void perform(Player player, String[] args) {

    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {
        return Collections.emptyList();
    }
}
