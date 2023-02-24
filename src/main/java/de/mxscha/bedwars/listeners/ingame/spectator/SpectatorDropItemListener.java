package de.mxscha.bedwars.listeners.ingame.spectator;

import de.mxscha.bedwars.utils.game.Game;
import de.mxscha.bedwars.utils.game.extra.GameStates;
import de.mxscha.bedwars.utils.manager.build.BuildManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class SpectatorDropItemListener implements Listener {

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        if (GameStates.getGameState() == 2) {
              Player player = event.getPlayer();
              if (Game.getSpectators().contains(player)) {
                if (!BuildManager.canBuild(player)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onPickUp(PlayerPickupItemEvent event) {
        if (GameStates.getGameState() == 2) {
            Player player = event.getPlayer();
            if (Game.getSpectators().contains(player)) {
                if (!BuildManager.canBuild(player)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onInventory(InventoryClickEvent event) {
        if (GameStates.getGameState() == 2) {
            Player player = (Player) event.getWhoClicked();
            if (Game.getSpectators().contains(player)) {
                if (!BuildManager.canBuild(player)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onSwitchHands(PlayerSwapHandItemsEvent event) {
        if (GameStates.getGameState() == 2) {
            Player player = event.getPlayer();
            if (Game.getSpectators().contains(player)) {
                if (!BuildManager.canBuild(player)) {
                    event.setCancelled(true);
                }
            }
        }
    }
}

