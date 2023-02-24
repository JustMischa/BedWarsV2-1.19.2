package de.mxscha.bedwars.listeners.lobby;

import de.mxscha.bedwars.BedwarsCore;
import de.mxscha.bedwars.utils.game.extra.GameStates;
import de.mxscha.bedwars.utils.manager.inventory.Inventories;
import de.mxscha.bedwars.utils.manager.inventory.item.ItemCreator;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Arrays;

public class LobbyVotingListener implements Listener {

    @EventHandler
    public void onInventory(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player player) {
            if (GameStates.getGameState() == 1) {
                if (event.getView().getTitle().equals("§8» §6Abstimmung")) {
                    event.setCancelled(true);
                    if (event.getCurrentItem() == null) return;
                    if (!event.getCurrentItem().hasItemMeta()) return;
                    if (event.getCurrentItem().getType() == Material.MAP) {
                        // removing the Color and "|" Symbol at the start of the name
                        String vote = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName().substring(7));
                        BedwarsCore.getInstance().getMapVoting().addVote(vote, player, event.getSlot());
                        player.closeInventory();
                        event.getInventory().setItem(event.getSlot(), new ItemCreator(Material.MAP).setName(" §8| §e" + vote)
                                .setLore(" §8● §7Klicke zum Abstimmen!", "", " §8| §a" + BedwarsCore.getInstance().getMapVoting().getVotes(vote) + " Votes").toItemStack());
                    }
                 }
            }
        }
    }
}
