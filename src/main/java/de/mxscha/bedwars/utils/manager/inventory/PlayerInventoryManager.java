package de.mxscha.bedwars.utils.manager.inventory;

import de.mxscha.bedwars.utils.manager.inventory.item.ItemCreator;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class PlayerInventoryManager {

    public void giveLobbyItems(Player player) {
        removeItems(player);
        player.getInventory().setItem(0, new ItemCreator(Material.GRAY_BED).setName("§8» §aTeam Auswahl").toItemStack());
        player.getInventory().setItem(4, new ItemCreator(Material.HONEYCOMB).setName("§8» §6Abstimmung").toItemStack());
        player.getInventory().setItem(8, new ItemCreator(Material.REDSTONE).setName("§8» §cZurück zur Lobby").toItemStack());
        player.getInventory().setHeldItemSlot(0);
    }

    public void removeItems(Player player) {
        player.getInventory().clear();
        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
    }

    public void giveEndingItems(Player player) {
        player.getInventory().clear();
        player.getInventory().setItem(8, new ItemCreator(Material.REDSTONE).setName("§8» §cZurück zur Lobby").toItemStack());
    }

    public void giveSpectatorItems(Player player) {
        removeItems(player);
        player.getInventory().setItem(0, new ItemCreator(Material.COMPASS).setName("§8» §aZuschauen").toItemStack());
        player.getInventory().setItem(8, new ItemCreator(Material.REDSTONE).setName("§8» §cZurück zur Lobby").toItemStack());
    }
}