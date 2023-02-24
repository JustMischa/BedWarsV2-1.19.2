package de.mxscha.bedwars.listeners.lobby;

import de.mxscha.bedwars.BedwarsCore;
import de.mxscha.bedwars.utils.game.Game;
import de.mxscha.bedwars.utils.game.extra.GameStates;
import de.mxscha.bedwars.utils.manager.locations.ConfigLocationUtil;
import de.mxscha.bedwars.utils.manager.scoreboard.LobbyScoreboard;
import de.mxscha.bedwars.utils.text.Messages;
import org.bukkit.Bukkit;
import org.bukkit.GameEvent;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class LobbyQuitListener implements Listener {

    @EventHandler
    public void onJoin(PlayerQuitEvent event) {
        if (GameStates.getGameState() == 1) {
            Player player = event.getPlayer();

            Game.getPlayers().remove(player);

            event.setQuitMessage(Messages.PREFIX.get() + "§f§n" + player.getName() + "§7 hat das Spiel verlassen " +
                    "§8(§a"+ Game.getPlayers().size() +"§8/§2"+(2*4)+"§8)");

            new LobbyScoreboard(player).delete();

            Location lobby = new ConfigLocationUtil("Lobby").loadLocation();
            if (lobby != null)
                player.teleport(lobby);

            if (Game.getPlayers().size() < 3) {
                if (BedwarsCore.getInstance().getLobbyCountdown().isRunning()) {
                    BedwarsCore.getInstance().getLobbyCountdown().stop();
                }
            }

            // removing the players vote & Team
            BedwarsCore.getInstance().getMapVoting().removeVote(player);
            BedwarsCore.getInstance().getTeamManager().removeFromTeam(player);
        }
    }
}
