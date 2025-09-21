package me.vesder.blazeydoublejump.hooks;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.managers.RegionManager;
import me.vesder.blazeydoublejump.BlazeyDoubleJump;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class WorldGuardHook {

    private static StateFlag DOUBLE_JUMP;
    private static StateFlag INFINITE_JUMP;

    public static boolean isDoubleJumpAllowed(Player player) {

        World weWorld = BukkitAdapter.adapt(player.getWorld());

        RegionManager regionManager =
            WorldGuard.getInstance().getPlatform().getRegionContainer().get(weWorld);

        if (regionManager == null) return false;

        BlockVector3 pos = BukkitAdapter.asBlockVector(player.getLocation());

        ApplicableRegionSet regions = regionManager.getApplicableRegions(pos);

        return regions.queryState(null, DOUBLE_JUMP) == StateFlag.State.ALLOW;

    }

    public static boolean isInfiniteJumpAllowed(Player player) {

        World weWorld = BukkitAdapter.adapt(player.getWorld());

        RegionManager regionManager =
            WorldGuard.getInstance().getPlatform().getRegionContainer().get(weWorld);

        if (regionManager == null) return false;

        BlockVector3 pos = BukkitAdapter.asBlockVector(player.getLocation());

        ApplicableRegionSet regions = regionManager.getApplicableRegions(pos);

        return regions.queryState(null, INFINITE_JUMP) == StateFlag.State.ALLOW;

    }

    public static void init() {

        try {
            StateFlag doubleJumpFlag = new StateFlag("double-jump", false);
            WorldGuard.getInstance().getFlagRegistry().register(doubleJumpFlag);
            DOUBLE_JUMP = doubleJumpFlag;
        } catch (FlagConflictException ex) {
            BlazeyDoubleJump.getPlugin().getLogger().log(Level.WARNING, "Could not register custom flag 'double-jump' due to a conflict!", ex);
        }

        try {
            StateFlag infiniteJumpFlag = new StateFlag("infinite-jump", false);
            WorldGuard.getInstance().getFlagRegistry().register(infiniteJumpFlag);
            INFINITE_JUMP = infiniteJumpFlag;
        } catch (FlagConflictException ex) {
            BlazeyDoubleJump.getPlugin().getLogger().log(Level.WARNING, "Could not register custom flag 'infinite-jump' due to a conflict!", ex);
        }

    }

}
