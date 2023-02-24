package de.mxscha.bedwars.listeners.ingame;

import de.mxscha.bedwars.utils.game.extra.GameStates;
import de.mxscha.bedwars.utils.manager.build.BuildManager;
import de.mxscha.bedwars.utils.manager.build.MapResetManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class IngameBuildListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (GameStates.getGameState() == 2) {
            if (!BuildManager.canBuild(event.getPlayer())) {
                if (!(event.getBlock().getType() == Material.RED_BED ||
                        event.getBlock().getType() == Material.BLUE_BED ||
                        event.getBlock().getType() == Material.YELLOW_BED ||
                        event.getBlock().getType() == Material.LIME_BED)) {
                    if (!MapResetManager.map.containsKey(event.getBlock())) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (GameStates.getGameState() == 2) {
            if (!BuildManager.canBuild(player)) {
                MapResetManager.map.put(event.getBlock(), event.getBlock().getLocation());
            }
        }
    }
}
