package de.mxscha.bedwars.listeners.lobby.cancel;

import de.mxscha.bedwars.utils.game.extra.GameStates;
import de.mxscha.bedwars.utils.manager.build.BuildManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class LobbyDamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (GameStates.getGameState() == 1 || GameStates.getGameState() == 3) {
            if (event.getEntity() instanceof Player player) {
                if (!BuildManager.canBuild(player)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onHunger(FoodLevelChangeEvent event) {
        if (GameStates.getGameState() == 1 || GameStates.getGameState() == 3) {
            if (event.getEntity() instanceof Player player) {
                if (!BuildManager.canBuild(player)) {
                    event.setCancelled(true);
                }
            }
        }
    }
}