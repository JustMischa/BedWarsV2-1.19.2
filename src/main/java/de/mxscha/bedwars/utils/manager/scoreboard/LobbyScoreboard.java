package de.mxscha.bedwars.utils.manager.scoreboard;

import de.mxscha.bedwars.BedwarsCore;
import de.mxscha.bedwars.utils.game.Game;
import de.mxscha.bedwars.utils.game.extra.GameStates;
import de.mxscha.bedwars.utils.manager.scoreboard.extras.ScoreboardBuilder;
import de.mxscha.bedwars.utils.manager.scoreboard.tablist.TablistManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class LobbyScoreboard extends ScoreboardBuilder {

    private BukkitTask id;
    private boolean running;

    public LobbyScoreboard(Player player) {
        super(player, "        §2§lBedWars        ");
        TablistManager.setAll(player);
    }

    public void createScoreboard() {
        setScore("§8§m                               ", 5);
        if (BedwarsCore.getInstance().getMapVoting().hasNobodyVoted()) {
            setScore(" §8● §7Map§8: §cunbestimmt", 4);
        } else
            setScore(" §8● §7Meiste Stimmen§8: §a§l" + BedwarsCore.getInstance().getMapVoting().getMapWithMostVotes() + " §7(§2" +
                    BedwarsCore.getInstance().getMapVoting().getVotes(BedwarsCore.getInstance().getMapVoting().getMapWithMostVotes()) + "§7)", 4);
        setScore("", 3);
        setScore(" §8● §7Spieler§8: §b" + Game.getPlayers().size(), 2);
        setScore("§8§m                               ", 1);
        update();
    }

    public void delete() {
        if (running) {
            if (id != null) {
                id.cancel();
                id = null;
            }
        }
    }

    public void update() {
        if (id != null)
            id.cancel();
        running = true;
        id = new BukkitRunnable() {
            @Override
            public void run() {
                if (GameStates.getGameState() == 1) {
                    setScore("§8§m                               ", 5);
                    if (BedwarsCore.getInstance().getMapVoting().hasNobodyVoted()) {
                        setScore(" §8● §7Map§8: §c§lunbestimmt", 4);
                    } else
                        setScore(" §8● §7Most Voted§8: §a§l" + BedwarsCore.getInstance().getMapVoting().getMapWithMostVotes() + " §7(§2" +
                                BedwarsCore.getInstance().getMapVoting().getVotes(BedwarsCore.getInstance().getMapVoting().getMapWithMostVotes()) + "§7)", 4);
                    setScore("", 3);
                    setScore(" §8● §7Spieler§8: §b" + Game.getPlayers().size(), 2);
                    setScore("§8§m                               ", 1);
                } else
                    id.cancel();
            }
        }.runTaskTimer(BedwarsCore.getInstance(), 0, 5);
    }
}
