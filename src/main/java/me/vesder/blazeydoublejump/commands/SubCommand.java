package me.vesder.blazeydoublejump.commands;

import org.bukkit.entity.Player;

import java.util.List;

public interface SubCommand {

    String getName();

    String getDescription();

    String getSyntax();

    void perform(Player player, String args[]);

    List<String> getSubcommandArguments(Player player, String args[]);

}
