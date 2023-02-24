package de.mxscha.bedwars.utils.manager.shop;

import de.mxscha.bedwars.BedwarsCore;
import de.mxscha.bedwars.utils.game.Game;
import de.mxscha.bedwars.utils.manager.maps.MapLocationManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;

public class ShopManager {

    public void spawn() {
        String map = BedwarsCore.getInstance().getGame().getGameMap();
        if (BedwarsCore.getInstance().getMapManager().isMapExists(map)) {
            Location ShopRed = MapLocationManager.getLocation("ShopRot", map, true);
            Location ShopBlue = MapLocationManager.getLocation("ShopBlau", map, true);
            Location ShopYellow = MapLocationManager.getLocation("ShopGelb", map, true);
            Location ShopGreen = MapLocationManager.getLocation("ShopGrün", map, true);

            Villager rot = (Villager) ShopRed.getWorld().spawnEntity(ShopRed, EntityType.VILLAGER);
            Villager blau = (Villager) ShopBlue.getWorld().spawnEntity(ShopBlue, EntityType.VILLAGER);
            Villager gelb = (Villager) ShopYellow.getWorld().spawnEntity(ShopYellow, EntityType.VILLAGER);
            Villager gruen = (Villager) ShopGreen.getWorld().spawnEntity(ShopGreen, EntityType.VILLAGER);

            addDetails(rot);
            addDetails(blau);
            addDetails(gelb);
            addDetails(gruen);
        }
    }

    public void despawn() {
        String map = BedwarsCore.getInstance().getGame().getGameMap();
        if (BedwarsCore.getInstance().getMapManager().isMapExists(map)) {
            World PlayMap = Bukkit.getWorld(BedwarsCore.getInstance().getGame().getGameMap());
            if (PlayMap != null) {
                for (Entity villagers : PlayMap.getEntities()) {
                    if (villagers.getType() == EntityType.VILLAGER) {
                        villagers.remove();
                    }
                }
            }
        }
    }

    private void addDetails(Villager villager) {
        villager.setCustomName("§6Shop");
        villager.setProfession(Villager.Profession.NONE);
        villager.setCustomNameVisible(true);
        villager.setCollidable(false);
        villager.setInvulnerable(true);
        villager.setGravity(false);
        villager.setAI(false);
        villager.setSilent(true);
    }
}
