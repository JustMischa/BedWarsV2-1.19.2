package de.mxscha.bedwars.utils.manager.scoreboard;

import de.mxscha.bedwars.BedwarsCore;
import de.mxscha.bedwars.utils.game.Game;
import de.mxscha.bedwars.utils.game.extra.GameStates;
import de.mxscha.bedwars.utils.game.extra.Stats;
import de.mxscha.bedwars.utils.manager.scoreboard.extras.ScoreboardBuilder;
import de.mxscha.bedwars.utils.manager.scoreboard.tablist.TablistManager;
import de.mxscha.bedwars.utils.manager.team.TeamManager;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class IngameScoreboard extends ScoreboardBuilder {

    private static BukkitTask id;
    private boolean running;

    public IngameScoreboard(Player player) {
        super(player, "        §2§lBedWars        ");
        TablistManager.setAll(player);
    }

    public void createScoreboard() {
        setScore("§8§m                               ", 10);
        setScore(" §8● §7Map§8: §a" + BedwarsCore.getInstance().getGame().getGameMap(), 9);
        setScore("§a", 8);

        // teams & kills -> update();

        setScore("§b", 3);
        setScore("§8§m                               ", 1);
        update();
    }

    @Override
    public void update() {
        if (id != null)
            id.cancel();
        id = new BukkitRunnable() {
            @Override
            public void run() {
                TeamManager tm = BedwarsCore.getInstance().getTeamManager();

                if (Stats.isAliveBedRed()) {
                    setScore(" §8» §cRot§8: §a✔", 7);
                } else {
                    if (tm.isEmpty(Game.getTeamRed())) {
                        setScore(" §8» §cRot§8: §c✗", 7);
                    } else
                        setScore(" §8» §cRot§8: §c✗ §7(§c"+Game.getTeamRed().size()+"§7)", 7);
                }
                if (Stats.isAliveBedBlue()) {
                    setScore(" §8» §9Blau§8: §a✔",  6);
                } else {
                    if (tm.isEmpty(Game.getTeamBlue())) {
                        setScore(" §8» §9Blau§8: §c✗", 6);
                    } else
                        setScore(" §8» §9Blau§8: §c✗ §7(§9"+Game.getTeamBlue().size()+"§7)", 6);
                }
                if (Stats.isAliveBedYellow()) {
                    setScore(" §8» §eGelb§8: §a✔",5);
                } else {
                    if (tm.isEmpty(Game.getTeamBlue())) {
                        setScore(" §8» §eGelb§8: §c✗", 5);
                    } else
                        setScore(" §8» §eGelb§8: §c✗ §7(§e"+Game.getTeamGreen().size()+"§7)", 5);
                }
                if (Stats.isAliveBedGreen()) {
                    setScore(" §8» §aGrün§8: §a✔",4);
                } else {
                    if (tm.isEmpty(Game.getTeamGreen())) {
                        setScore(" §8» §aGrün§8: §c✗", 4);
                    } else
                        setScore(" §8» §aGrün§8: §c✗ §7(§a"+Game.getTeamGreen().size()+"§7)", 4);
                }

                if (tm.isInTeam(player, Game.getTeamRed())) {
                    setScore(" §8● §7Team Kills§8: §c" + Stats.getKillsRed(), 2);
                }
                if (tm.isInTeam(player, Game.getTeamBlue())) {
                    setScore(" §8● §7Team Kills§8: §9" + Stats.getKillsBlue(), 2);
                }
                if (tm.isInTeam(player, Game.getTeamYellow())) {
                    setScore(" §8● §7Team Kills§8: §e" + Stats.getKillsYellow(), 2);
                }
                if (tm.isInTeam(player, Game.getTeamGreen())) {
                    setScore(" §8● §7Team Kills§8: §a" + Stats.getKillsGreen(), 2);
                }
            }
        }.runTaskTimer(BedwarsCore.getInstance(), 0, 5);
    }

    public static BukkitTask getId() {
        return id;
    }
}
