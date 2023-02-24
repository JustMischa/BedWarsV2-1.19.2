package de.mxscha.bedwars.listeners.ingame;

import de.mxscha.bedwars.BedwarsCore;
import de.mxscha.bedwars.utils.game.Game;
import de.mxscha.bedwars.utils.game.extra.GameStates;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class IngameChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (GameStates.getGameState() == 2) {
            Player player = event.getPlayer();
            if (BedwarsCore.getInstance().getTeamManager().isInTeam(player)) {
                if (event.getMessage().startsWith("@a")) {
                    String msg = event.getMessage().replace("@a", "");
                    if (BedwarsCore.getInstance().getTeamManager().isInTeam(player, Game.getTeamRed())) {
                        event.setFormat("§8[§dAlle§8] §c" + player.getName() + "§8:§f" + msg);
                    }
                    if (BedwarsCore.getInstance().getTeamManager().isInTeam(player, Game.getTeamBlue())) {
                        event.setFormat("§8[§dAlle§8] §9" + player.getName() + "§8:§f" + msg);
                    }
                    if (BedwarsCore.getInstance().getTeamManager().isInTeam(player, Game.getTeamYellow())) {
                        event.setFormat("§8[§dAlle§8] §e" + player.getName() + "§8:§f" + msg);
                    }
                    if (BedwarsCore.getInstance().getTeamManager().isInTeam(player, Game.getTeamGreen())) {
                        event.setFormat("§8[§dAlle§8] §a" + player.getName() + "§8:§f" + msg);
                    }
                } else {
                    event.setCancelled(true);
                    if (BedwarsCore.getInstance().getTeamManager().isInTeam(player, Game.getTeamRed())) {
                        for (Player PlayerRed : Game.getTeamRed().values()) {
                            PlayerRed.sendMessage("§8[§cTeam§8] §c" + player.getName() + "§8: §f" + event.getMessage());
                        }
                    } else  if (BedwarsCore.getInstance().getTeamManager().isInTeam(player, Game.getTeamBlue())) {
                        for (Player PlayerBlue : Game.getTeamBlue().values()) {
                            PlayerBlue.sendMessage("§8[§9Team§8] §9" + player.getName() + "§8: §f" + event.getMessage());
                        }
                    } else  if (BedwarsCore.getInstance().getTeamManager().isInTeam(player, Game.getTeamYellow())) {
                        for (Player PlayerYellow : Game.getTeamYellow().values()) {
                            PlayerYellow.sendMessage("§8[§eTeam§8] §e" + player.getName() + "§8: §f" + event.getMessage());
                        }
                    } else  if (BedwarsCore.getInstance().getTeamManager().isInTeam(player, Game.getTeamGreen())) {
                        for (Player PlayerGreen : Game.getTeamGreen().values()) {
                            PlayerGreen.sendMessage("§8[§aTeam§8] §a" + player.getName() + "§8: §f" + event.getMessage());
                        }
                    }
                }
            }
        }
    }
}
