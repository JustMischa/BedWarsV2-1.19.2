package de.mxscha.bedwars.listeners.ingame.spectator;

import de.mxscha.bedwars.BedwarsCore;
import de.mxscha.bedwars.utils.game.extra.GameStates;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class SpectatorQuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        if (GameStates.getGameState() == 2) {
            event.quitMessage(null);
        }
    }
}
