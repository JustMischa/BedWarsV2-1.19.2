package de.mxscha.bedwars.utils.manager.spectator;

import de.mxscha.bedwars.BedwarsCore;
import de.mxscha.bedwars.utils.game.Game;
import de.mxscha.bedwars.utils.manager.locations.ConfigLocationUtil;
import de.mxscha.bedwars.utils.manager.maps.MapLocationManager;
import de.mxscha.bedwars.utils.manager.scoreboard.tablist.TablistManager;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SpectatorManager {

    public void setSpectator(Player player) {
        BedwarsCore.getInstance().getTeamManager().removeFromTeam(player);
        Game.getSpectators().add(player);
        BedwarsCore.getInstance().getInventoryManager().giveSpectatorItems(player);
        later(player);
        TablistManager.setAll(player);
        player.setGameMode(GameMode.SURVIVAL);
        player.setAllowFlight(true);
        player.setFlying(true);
        for (Player players : Game.getPlayers()) {
            players.hidePlayer(BedwarsCore.getInstance(), player);
        }
        for (Player spectators : Game.getSpectators()) {
            player.showPlayer(BedwarsCore.getInstance(), spectators);
        }
    }

    private static void later(Player player) {
        new BukkitRunnable() {
            @Override
            public void run() {
                player.teleport(MapLocationManager.getLocation("Spectator", BedwarsCore.getInstance().getGame().getGameMap(), true));
            }
        }.runTaskLater(BedwarsCore.getInstance(), 10);
    }
}
