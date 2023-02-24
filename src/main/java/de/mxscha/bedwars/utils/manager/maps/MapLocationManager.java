package de.mxscha.bedwars.utils.manager.maps;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Objects;

public class MapLocationManager {

    private static final File file = MapManager.getFile();
    private static final FileConfiguration cfg = MapManager.getConfig();

    public static void setLocation(Location loc, String name, String map) {
        cfg.set("Maps." + map + "." + name + ".world", loc.getWorld().getName());
        cfg.set("Maps." + map + "." + name + ".x", loc.getX());
        cfg.set("Maps." + map + "." + name + ".y", loc.getY());
        cfg.set("Maps." + map + "." + name + ".z", loc.getZ());
        cfg.set("Maps." + map + "." + name + ".yaw", loc.getYaw());
        cfg.set("Maps." + map + "." + name + ".pitch", loc.getPitch());
        MapManager.save();
    }

    public static Location getLocation(String name, String map, boolean yp) {
        World world = Bukkit.getWorld(Objects.requireNonNull(cfg.getString("Maps." + map + "." + name + ".world")));
        double x = cfg.getDouble("Maps." + map + "." + name + ".x");
        double y = cfg.getDouble("Maps." + map + "." + name + ".y");
        double z = cfg.getDouble("Maps." + map + "." + name + ".z");
        Location location = new Location(world, x, y, z);
        if (yp) {
            location.setYaw(cfg.getInt("Maps." + map + "." + name + ".yaw"));
            location.setPitch(cfg.getInt("Maps." + map + "." + name + ".pitch"));
        }
        return location;
    }
}
