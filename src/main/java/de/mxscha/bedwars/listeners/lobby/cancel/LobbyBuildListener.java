package de.mxscha.bedwars.listeners.lobby.cancel;

import de.mxscha.bedwars.utils.game.extra.GameStates;
import de.mxscha.bedwars.utils.manager.build.BuildManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class LobbyBuildListener implements Listener {

    @EventHandler
    public void onBuild(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (GameStates.getGameState() == 1) {
            if (!BuildManager.canBuild(player)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (GameStates.getGameState() == 1) {
            if (!BuildManager.canBuild(player)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (GameStates.getGameState() == 1) {
            if (event.getClickedBlock() != null) {
                if (!BuildManager.canBuild(player)) {
                    event.setCancelled(true);
                }
            }
        }
    }
}