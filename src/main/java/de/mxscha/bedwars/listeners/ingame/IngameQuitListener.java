package de.mxscha.bedwars.listeners.ingame;

import de.mxscha.bedwars.BedwarsCore;
import de.mxscha.bedwars.utils.game.Game;
import de.mxscha.bedwars.utils.game.extra.GameStates;
import de.mxscha.bedwars.utils.manager.team.TeamManager;
import de.mxscha.bedwars.utils.text.Messages;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class IngameQuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (GameStates.getGameState() == 2) {
            if (Game.getPlayers().contains(player)) {
                TeamManager tm = BedwarsCore.getInstance().getTeamManager();
                if (tm.isInTeam(player, Game.getTeamRed())) {
                    event.setQuitMessage(Messages.PREFIX.get() + "§c" + player.getName() + " §7hat das Spiel verlassen!");
                    tm.removeFromTeam(player);
                    BedwarsCore.getInstance().getGame().checkForEnd();
                }
                if (tm.isInTeam(player, Game.getTeamBlue())) {
                    event.setQuitMessage(Messages.PREFIX.get() + "§9" + player.getName() + " §7hat das Spiel verlassen!");
                    tm.removeFromTeam(player);
                    BedwarsCore.getInstance().getGame().checkForEnd();
                }
                if (tm.isInTeam(player, Game.getTeamYellow())) {
                    event.setQuitMessage(Messages.PREFIX.get() + "§e" + player.getName() + " §7hat das Spiel verlassen!");
                    tm.removeFromTeam(player);
                    BedwarsCore.getInstance().getGame().checkForEnd();
                }
                if (tm.isInTeam(player, Game.getTeamGreen())) {
                    event.setQuitMessage(Messages.PREFIX.get() + "§a" + player.getName() + " §7hat das Spiel verlassen!");
                    tm.removeFromTeam(player);
                    BedwarsCore.getInstance().getGame().checkForEnd();
                }
            }
        }
    }
}
