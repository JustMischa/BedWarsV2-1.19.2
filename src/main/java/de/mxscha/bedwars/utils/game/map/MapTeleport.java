package de.mxscha.bedwars.utils.game.map;

import de.mxscha.bedwars.BedwarsCore;
import de.mxscha.bedwars.utils.game.Game;
import de.mxscha.bedwars.utils.manager.maps.MapLocationManager;
import de.mxscha.bedwars.utils.text.Messages;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class MapTeleport {

    public void teleportPlayersToMap(String map) {
        if (BedwarsCore.getInstance().getMapManager().isMapExists(map)) {
            Location SpawnRot = MapLocationManager.getLocation("SpawnRot", map, true);
            Location SpawnGelb = MapLocationManager.getLocation("SpawnGelb", map, true);
            Location SpawnGrün = MapLocationManager.getLocation("SpawnGrün", map, true);
            Location SpawnBlau = MapLocationManager.getLocation("SpawnBlau", map, true);

            for (Player player : Game.getPlayers()) {
                if (Game.getTeamRed().containsValue(player)) {
                    player.teleport(SpawnRot);
                }
                if (Game.getTeamBlue().containsValue(player)) {
                    player.teleport(SpawnBlau);
                }
                if (Game.getTeamYellow().containsValue(player)) {
                    player.teleport(SpawnGelb);
                }
                if (Game.getTeamGreen().containsValue(player)) {
                    player.teleport(SpawnGrün);
                }
                player.sendMessage(Messages.PREFIX.get() + "§7Du wurdest auf die Map Teleportiert!");
            }
        }
    }

    public void teleportPlayerToSpawn(Player player, String map) {
        if (BedwarsCore.getInstance().getMapManager().isMapExists(map)) {
            Location SpawnRot = MapLocationManager.getLocation("SpawnRot", map, true);
            Location SpawnGelb = MapLocationManager.getLocation("SpawnGelb", map, true);
            Location SpawnGrün = MapLocationManager.getLocation("SpawnGrün", map, true);
            Location SpawnBlau = MapLocationManager.getLocation("SpawnBlau", map, true);
            if (Game.getTeamRed().containsValue(player)) {
                player.teleport(SpawnRot);
            }
            if (Game.getTeamBlue().containsValue(player)) {
                player.teleport(SpawnBlau);
            }
            if (Game.getTeamYellow().containsValue(player)) {
                player.teleport(SpawnGelb);
            }
            if (Game.getTeamGreen().containsValue(player)) {
                player.teleport(SpawnGrün);
            }
        }
    }
}
