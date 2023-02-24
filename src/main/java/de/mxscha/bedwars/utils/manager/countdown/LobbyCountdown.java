package de.mxscha.bedwars.utils.manager.countdown;

import de.mxscha.bedwars.BedwarsCore;
import de.mxscha.bedwars.utils.game.Game;
import de.mxscha.bedwars.utils.text.Messages;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class LobbyCountdown {

    private boolean running;
    private BukkitTask id;
    private int seconds = 60;
    private String defined_map;

    public void start() {
        if (running)
            running = false;
        if (id != null)
            id.cancel();
        running = true;
        id = new BukkitRunnable() {
            @Override
            public void run() {
                for (Player players : Game.getPlayers()) {
                    players.setLevel(seconds);
                }
                switch (seconds) {
                    case 60: case 30: case 15: case 10: case 4: case 3: case 2:
                        for (Player players : Game.getPlayers()) {
                            players.sendMessage(Messages.PREFIX.get() + "§7Das Spiel startet in §2§l" + seconds + " Sekunden§7!");
                            players.playSound(players, Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
                        }
                        break;
                    case 5:
                        for (Player players : Game.getPlayers()) {
                            players.sendMessage(Messages.PREFIX.get() + "§7Das Spiel startet in §2§l" + seconds + " Sekunden§7!");
                            players.playSound(players, Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
                            if (BedwarsCore.getInstance().getMapVoting().hasNobodyVoted()) {
                                defined_map = BedwarsCore.getInstance().getMapManager().getRandomPlayableMap();
                            } else
                                defined_map = BedwarsCore.getInstance().getMapVoting().getMapWithMostVotes();
                            players.sendMessage(Messages.PREFIX.get() + " ");
                            players.sendMessage(Messages.PREFIX.get() + "§7Es wird die Map §a§l" + defined_map + " §7gespielt!");
                            players.sendMessage(Messages.PREFIX.get() + " ");
                        }
                        break;
                    case 1:
                        for (Player players : Game.getPlayers()) {
                            players.sendMessage(Messages.PREFIX.get() + "§7Das Spiel startet in §2§l" + seconds + " Sekunde§7!");
                            players.playSound(players, Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
                        }
                        break;
                    case 0:
                        BedwarsCore.getInstance().getGame().startDefaultGame(defined_map);
                        stop();
                        break;
                }
                seconds--;
            }
        }.runTaskTimer(BedwarsCore.getInstance(), 0, 20);
    }

    public void stop() {
        if (running)
            running = false;
        if (id != null)
            id.cancel();
        seconds = 60;
    }

    public int getSeconds() {
        return seconds;
    }

    public boolean isRunning() {
        return running;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
}
