package de.mxscha.bedwars.listeners.lobby;

import de.mxscha.bedwars.BedwarsCore;
import de.mxscha.bedwars.utils.game.Game;
import de.mxscha.bedwars.utils.game.extra.GameStates;
import de.mxscha.bedwars.utils.manager.countdown.LobbyCountdown;
import de.mxscha.bedwars.utils.manager.inventory.PlayerInventoryManager;
import de.mxscha.bedwars.utils.manager.locations.ConfigLocationUtil;
import de.mxscha.bedwars.utils.manager.scoreboard.LobbyScoreboard;
import de.mxscha.bedwars.utils.text.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class LobbyJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (GameStates.getGameState() == 1) {
            Player player = event.getPlayer();
            // adding all to the inventory + actionbar
            BedwarsCore.getInstance().getInventoryManager().giveLobbyItems(player);

            // Scoreboard
            new LobbyScoreboard(player).createScoreboard();

            // adding the player into the game
            if (!Game.getPlayers().contains(player))
                Game.getPlayers().add(player);

            event.setJoinMessage(Messages.PREFIX.get() + "§f§n" + player.getName() + "§7 hat das Spiel betreten " +
                    "§8(§a"+Game.getPlayers().size()+"§8/§2"+(2*4)+"§8)");

            Location lobby = new ConfigLocationUtil("Lobby").loadLocation();
            if (lobby != null)
                player.teleport(lobby);

            if (!player.hasPlayedBefore()) {
                tryToTpAgain(player);
            }

            if (Game.getPlayers().size() >= 3) {
                if (!BedwarsCore.getInstance().getLobbyCountdown().isRunning()) {
                    BedwarsCore.getInstance().getLobbyCountdown().start();
                }
            }
        }
    }

    private void tryToTpAgain(Player player) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Location lobby = new ConfigLocationUtil("Lobby").loadLocation();
                if (lobby != null)
                    player.teleport(lobby);
            }
        }.runTaskLater(BedwarsCore.getInstance(), 20*2);
    }
}
