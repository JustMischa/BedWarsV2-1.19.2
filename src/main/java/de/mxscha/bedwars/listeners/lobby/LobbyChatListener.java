package de.mxscha.bedwars.listeners.lobby;

import de.mxscha.bedwars.BedwarsCore;
import de.mxscha.bedwars.utils.game.Game;
import de.mxscha.bedwars.utils.game.extra.GameStates;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class LobbyChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (GameStates.getGameState() == 1) {
            Player player = event.getPlayer();
            if (BedwarsCore.getInstance().getTeamManager().isInTeam(player)) {
                if (BedwarsCore.getInstance().getTeamManager().isInTeam(player, Game.getTeamRed())) {
                    event.setFormat("§c" + player.getName() + "§8: §7§o" + event.getMessage());
                }
                if (BedwarsCore.getInstance().getTeamManager().isInTeam(player, Game.getTeamBlue())) {
                    event.setFormat("§9" + player.getName() + "§8: §7§o" + event.getMessage());
                }
                if (BedwarsCore.getInstance().getTeamManager().isInTeam(player, Game.getTeamYellow())) {
                    event.setFormat("§e" + player.getName() + "§8: §7§o" + event.getMessage());
                }
                if (BedwarsCore.getInstance().getTeamManager().isInTeam(player, Game.getTeamGreen())) {
                    event.setFormat("§a" + player.getName() + "§8: §7§o" + event.getMessage());
                }
            } else
                event.setFormat("§f" + player.getName() + "§8: §7§o" + event.getMessage());
        }
    }
}
