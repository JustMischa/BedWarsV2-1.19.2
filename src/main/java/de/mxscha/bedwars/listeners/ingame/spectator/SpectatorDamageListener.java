package de.mxscha.bedwars.listeners.ingame.spectator;

import de.mxscha.bedwars.utils.game.Game;
import de.mxscha.bedwars.utils.game.extra.GameStates;
import de.mxscha.bedwars.utils.manager.build.BuildManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class SpectatorDamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (GameStates.getGameState() == 2) {
            if (event.getEntity() instanceof Player player) {
                if (Game.getSpectators().contains(player)) {
                    if (!BuildManager.canBuild(player)) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onHunger(FoodLevelChangeEvent event) {
        if (GameStates.getGameState() == 2) {
            if (event.getEntity() instanceof Player player) {
                if (Game.getSpectators().contains(player)) {
                    if (!BuildManager.canBuild(player)) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
}
