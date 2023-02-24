package de.mxscha.bedwars.listeners.ingame.spectator;

import de.mxscha.bedwars.BedwarsCore;
import de.mxscha.bedwars.utils.game.extra.GameStates;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class SpectatorJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (GameStates.getGameState() == 2) {
            event.joinMessage(null);
            BedwarsCore.getInstance().getSpectatorManager().setSpectator(event.getPlayer());
        }
    }
}
