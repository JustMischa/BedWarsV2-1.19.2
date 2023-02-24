package de.mxscha.bedwars.listeners.lobby.cancel;

import de.mxscha.bedwars.utils.game.Game;
import de.mxscha.bedwars.utils.game.extra.GameStates;
import de.mxscha.bedwars.utils.manager.build.BuildManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class LobbyDropItemListener implements Listener {

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        if (GameStates.getGameState() == 1 || GameStates.getGameState() == 3) {
              Player player = event.getPlayer();
              if (!BuildManager.canBuild(player)) {
                  event.setCancelled(true);
              }
        }
    }

    @EventHandler
    public void onPickUp(PlayerPickupItemEvent event) {
        if (GameStates.getGameState() == 1 || GameStates.getGameState() == 3) {
            Player player = event.getPlayer();
            if (!BuildManager.canBuild(player)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onSwitchHands(PlayerSwapHandItemsEvent event) {
        if (GameStates.getGameState() == 1) {
            Player player = event.getPlayer();
            if (!BuildManager.canBuild(player)) {
                event.setCancelled(true);
            }
        }
    }
}

