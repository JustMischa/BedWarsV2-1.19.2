package de.mxscha.bedwars.utils.manager.inventory;

import de.mxscha.bedwars.utils.manager.inventory.item.ItemCreator;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

public class InventoryProperties {

    public static void fillWithGlass(Inventory inventory) {
        for (int i = 0; i < 27; i++) {
            inventory.setItem(i, new ItemCreator(Material.GRAY_STAINED_GLASS_PANE).setName(" ").toItemStack());
        }
    }
}
