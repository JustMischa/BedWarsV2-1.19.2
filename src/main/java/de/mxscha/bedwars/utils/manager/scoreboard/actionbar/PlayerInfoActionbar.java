package de.mxscha.bedwars.utils.manager.scoreboard.actionbar;

import de.mxscha.bedwars.BedwarsCore;
import de.mxscha.bedwars.utils.game.Game;
import de.mxscha.bedwars.utils.text.Messages;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class PlayerInfoActionbar {

    private static BukkitTask info;

    public static void send() {
        info = new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Game.getPlayers()) {
                    if (Game.getTeamRed().containsValue(player)) {
                        player.sendActionBar("§cTeam Rot");
                    } else if (Game.getTeamBlue().containsValue(player)) {
                        player.sendActionBar("§9Team Blau");
                    } else if (Game.getTeamYellow().containsValue(player)) {
                        player.sendActionBar("§eTeam Gelb");
                    } else if (Game.getTeamGreen().containsValue(player)) {
                        player.sendActionBar("§aTeam Grün");
                    } else
                        player.sendActionBar("§eWähle ein Team...");
                }
            }
        }.runTaskTimer(BedwarsCore.getInstance(), 0, 15);
    }
}