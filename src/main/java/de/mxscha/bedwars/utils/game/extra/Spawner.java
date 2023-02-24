package de.mxscha.bedwars.utils.game.extra;

import de.mxscha.bedwars.BedwarsCore;
import de.mxscha.bedwars.utils.manager.inventory.item.ItemCreator;
import de.mxscha.bedwars.utils.manager.maps.MapLocationManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class Spawner {

    private static final ArrayList<Location> Bronze = new ArrayList<>();
    private static final ArrayList<Location> Iron = new ArrayList<>();
    private static final ArrayList<Location> Gold = new ArrayList<>();

    public void add(String map) {
        if (BedwarsCore.getInstance().getMapManager().isMapExists(map)) {
            Location BronzeRed = MapLocationManager.getLocation("BronzeSpawnerRot", map, false);
            Location IronRed = MapLocationManager.getLocation("IronSpawnerRot", map, false);
            Location GoldRed = MapLocationManager.getLocation("GoldSpawnerRot", map, false);

            Location BronzeBlue = MapLocationManager.getLocation("BronzeSpawnerBlau", map, false);
            Location IronBlue = MapLocationManager.getLocation("IronSpawnerBlau", map, false);
            Location GoldBlue = MapLocationManager.getLocation("GoldSpawnerBlau", map, false);

            Location BronzeYellow = MapLocationManager.getLocation("BronzeSpawnerGelb", map, false);
            Location IronYellow = MapLocationManager.getLocation("IronSpawnerGelb", map, false);
            Location GoldYellow = MapLocationManager.getLocation("GoldSpawnerGelb", map, false);

            Location BronzeGreen = MapLocationManager.getLocation("BronzeSpawnerGrün", map, false);
            Location IronGreen = MapLocationManager.getLocation("IronSpawnerGrün", map, false);
            Location GoldGreen = MapLocationManager.getLocation("GoldSpawnerGrün", map, false);

            Bronze.add(BronzeRed); Bronze.add(BronzeBlue); Bronze.add(BronzeYellow); Bronze.add(BronzeGreen);
            Iron.add(IronRed); Iron.add(IronBlue); Iron.add(IronYellow); Iron.add(IronGreen);
            Gold.add(GoldRed); Gold.add(GoldBlue); Gold.add(GoldYellow); Gold.add(GoldGreen);
        }
    }

    public void startSpawn(String map) {
        if (BedwarsCore.getInstance().getMapManager().isMapExists(map)) {
            add(map);
            for (Location bronze : Bronze) {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        bronze.getWorld().dropItem(bronze, new ItemCreator(Material.COPPER_INGOT).setName("§cBronze").toItemStack()).setVelocity(new Vector(0, -1, 0));
                    }
                }.runTaskTimer(BedwarsCore.getInstance(), 0, 20);
            }
            for (Location iron : Iron) {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        iron.getWorld().dropItem(iron, new ItemCreator(Material.RAW_IRON).setName("§fEisen").toItemStack()).setVelocity(new Vector(0, -1, 0));
                    }
                }.runTaskTimer(BedwarsCore.getInstance(), 20 * 30, 20 * 30);
            }
            for (Location gold : Gold) {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        gold.getWorld().dropItem(gold, new ItemCreator(Material.RAW_GOLD).setName("§6Gold").toItemStack()).setVelocity(new Vector(0, -1, 0));
                    }
                }.runTaskTimer(BedwarsCore.getInstance(), 20 * 60, 20 * 60);
            }

        }
    }
}
