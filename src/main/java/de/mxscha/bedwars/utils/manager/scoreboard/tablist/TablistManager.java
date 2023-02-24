package de.mxscha.bedwars.utils.manager.scoreboard.tablist;

import de.mxscha.bedwars.utils.game.Game;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class TablistManager {
    public static void setAll(Player player) {
        setPlayerList(player);
        setAllPlayersTeam();
    }

    public static void setAllPlayersTeam() {
        Bukkit.getOnlinePlayers().forEach(TablistManager::setPlayerTeams);
    }

    private static void setPlayerTeams(Player player) {
        Scoreboard scoreboard = player.getScoreboard();

        Team TeamRed = scoreboard.getTeam("01rot");
        Team TeamBlue = scoreboard.getTeam("02blau");
        Team TeamYellow = scoreboard.getTeam("03gelb");
        Team TeamGreen = scoreboard.getTeam("04grün");
        Team players = scoreboard.getTeam("05spieler");
        Team spectator = scoreboard.getTeam("06Spectator");
        if (TeamRed == null)
            TeamRed = scoreboard.registerNewTeam("01rot");
        if (TeamBlue == null)
            TeamBlue = scoreboard.registerNewTeam("02blau");
        if (TeamYellow == null)
            TeamYellow = scoreboard.registerNewTeam("03gelb");
        if (TeamGreen == null)
            TeamGreen = scoreboard.registerNewTeam("04grün");
        if (players == null)
            players = scoreboard.registerNewTeam("05spieler");
        if (spectator == null)
            spectator = scoreboard.registerNewTeam("06Spectator");
        TeamRed.setPrefix("§cRot §8| §f");
        TeamBlue.setPrefix("§9Blau §8| §f");
        TeamYellow.setPrefix("§eGelb §8| §f");
        TeamGreen.setPrefix("§aGrün §8| §f");
        players.setPrefix("");
        spectator.setPrefix("§8[§ctot§8] §f");
        TeamRed.setColor(ChatColor.GRAY);
        TeamBlue.setColor(ChatColor.GRAY);
        TeamYellow.setColor(ChatColor.GRAY);
        TeamGreen.setColor(ChatColor.GRAY);
        players.setColor(ChatColor.GRAY);
        spectator.setColor(ChatColor.GRAY);

        for (Player target : Bukkit.getOnlinePlayers()) {
            if (Game.getTeamRed().containsValue(target)) {
                TeamRed.addEntry(target.getName());
                continue;
            }
            if (Game.getTeamBlue().containsValue(target)) {
                TeamBlue.addEntry(target.getName());
                continue;
            }
            if (Game.getTeamYellow().containsValue(target)) {
                TeamYellow.addEntry(target.getName());
                continue;
            }
            if (Game.getTeamGreen().containsValue(target)) {
                TeamGreen.addEntry(target.getName());
                continue;
            }
            if (Game.getSpectators().contains(target)) {
                spectator.addEntry(target.getName());
                continue;
            }
            players.addEntry(target.getName());
        }
    }

    private static void setPlayerList(Player player) {
        player.setPlayerListHeaderFooter("      " + "§bBedWars-1"+      "\n             ", "           §c§lCLOSED §a§lALPHA!\n§7wie man hier an der tab sehen kann!           \n                   ");
    }
}
