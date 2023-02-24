package de.mxscha.bedwars.utils.manager.scoreboard;

import de.mxscha.bedwars.BedwarsCore;
import de.mxscha.bedwars.utils.game.Game;
import de.mxscha.bedwars.utils.game.extra.GameStates;
import de.mxscha.bedwars.utils.manager.scoreboard.extras.ScoreboardBuilder;
import de.mxscha.bedwars.utils.manager.scoreboard.tablist.TablistManager;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class EndingScoreboard extends ScoreboardBuilder {

    private BukkitTask id;
    private boolean running;

    public EndingScoreboard(Player player) {
        super(player, "        §2§lBedWars        ");
        TablistManager.setAll(player);
    }

    public void createScoreboard() {
        IngameScoreboard.getId().cancel();
        setScore("§8§m                               ", 5);
        setScore(" §8● §7Map§8: §a" + BedwarsCore.getInstance().getGame().getGameMap(), 4);
        setScore("§b", 3);
        switch (BedwarsCore.getInstance().getGame().getWinnerTeam()) {
            case "Red" -> setScore(" §8● §7Gewinner§8: §cTeam Rot", 2);
            case "Blue" -> setScore(" §8● §7Gewinner§8: §9Team Blau", 2);
            case "Yellow" -> setScore(" §8● §7Gewinner§8: §eTeam Gelb", 2);
            case "Green" -> setScore(" §8● §7Gewinner§8: §aTeam Grün", 2);
        }
        setScore("§8§m                               ", 1);
    }

    @Override
    public void update() {

    }
}