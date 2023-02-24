package de.mxscha.bedwars.utils.manager.countdown;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import de.mxscha.bedwars.BedwarsCore;
import de.mxscha.bedwars.utils.game.Game;
import de.mxscha.bedwars.utils.manager.build.MapResetManager;
import de.mxscha.bedwars.utils.text.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class RestartCountdown {

    private boolean running;
    private BukkitTask id;
    private int seconds = 30;

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
                    case 30: case 15: case 10: case 5: case 4: case 3: case 2:
                        for (Player players : Game.getPlayers()) {
                            players.sendMessage(Messages.PREFIX.get() + "§cDer Server startet in §e" + seconds + " Sekunden §cneu!");
                            players.playSound(players, Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
                        }
                        break;
                    case 1:
                        for (Player players : Game.getPlayers()) {
                            players.sendMessage(Messages.PREFIX.get() + "§cDer Server startet in §e" + seconds + " Sekunde §cneu!");
                            players.playSound(players, Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
                        }
                        break;
                    case 0:
                        for (Player players : Game.getPlayers()) {
                            ByteArrayDataOutput out = ByteStreams.newDataOutput();
                            out.writeUTF("Connect");
                            out.writeUTF(BedwarsCore.getLobbyName());
                            players.sendPluginMessage(BedwarsCore.getInstance(), "BungeeCord", out.toByteArray());
                        }
                        MapResetManager.Reset();
                        Bukkit.getServer().shutdown();
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
