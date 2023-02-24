package de.mxscha.bedwars.utils.manager.build;

import de.mxscha.bedwars.BedwarsCore;
import de.mxscha.bedwars.utils.text.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Villager;

import java.util.HashMap;

public class MapResetManager {

    public static HashMap<Block, Location> map = new HashMap<>();

    public static void Reset() {
        Bukkit.getConsoleSender().sendMessage(Messages.PREFIX.get() + "Es wurden" + map.size() + " Bloecke gelöscht!");
        map.forEach((block, location) -> block.setType(Material.AIR));
        removeItems();
        BedwarsCore.getInstance().getShopManager().despawn();
    }

    public static void removeItems() {
        for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (entity instanceof org.bukkit.entity.Item)
                    entity.remove();
                if (entity instanceof Villager) {
                    entity.remove();
                }
            }
        }
    }
}