package de.mxscha.bedwars.listeners.lobby;

import de.mxscha.bedwars.BedwarsCore;
import de.mxscha.bedwars.utils.game.Game;
import de.mxscha.bedwars.utils.game.extra.GameStates;
import de.mxscha.bedwars.utils.manager.inventory.Inventories;
import de.mxscha.bedwars.utils.manager.inventory.item.ItemCreator;
import de.mxscha.bedwars.utils.text.Messages;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class LobbyTeamSelectListener implements Listener {

    @EventHandler
    public void onInventory(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player player) {
            if (GameStates.getGameState() == 1) {
                event.setCancelled(true);
                if (event.getView().getTitle().equals("§8» §aTeam Auswahl")) {
                    if (event.getCurrentItem() == null) return;
                    if (!event.getCurrentItem().hasItemMeta()) return;
                    switch (event.getCurrentItem().getType()) {
                        case RED_BED -> {
                            if (!BedwarsCore.getInstance().getTeamManager().isFull(Game.getTeamRed())) {
                                player.closeInventory();
                                if (!BedwarsCore.getInstance().getTeamManager().isInTeam(player, Game.getTeamRed())) {
                                    BedwarsCore.getInstance().getTeamManager().setTeam(player, Game.getTeamRed());
                                    player.sendMessage(Messages.IS_NOW_IN_TEAM_RED.get());
                                    Inventories.updateInventories();
                                } else
                                    player.sendMessage(Messages.IS_ALREADY_IN_TEAM.get());
                            } else
                                player.sendMessage(Messages.TEAM_IS_FULL.get());
                        }
                        case BLUE_BED -> {
                            if (!BedwarsCore.getInstance().getTeamManager().isFull(Game.getTeamBlue())) {
                                player.closeInventory();
                                if (!BedwarsCore.getInstance().getTeamManager().isInTeam(player, Game.getTeamBlue())) {
                                    BedwarsCore.getInstance().getTeamManager().setTeam(player, Game.getTeamBlue());
                                    player.sendMessage(Messages.IS_NOW_IN_TEAM_BLUE.get());
                                    Inventories.updateInventories();
                                } else
                                    player.sendMessage(Messages.IS_ALREADY_IN_TEAM.get());
                            } else
                                player.sendMessage(Messages.TEAM_IS_FULL.get());
                        }
                        case YELLOW_BED -> {
                            if (!BedwarsCore.getInstance().getTeamManager().isFull(Game.getTeamYellow())) {
                                player.closeInventory();
                                if (!BedwarsCore.getInstance().getTeamManager().isInTeam(player, Game.getTeamYellow())) {
                                    BedwarsCore.getInstance().getTeamManager().setTeam(player, Game.getTeamYellow());
                                    player.sendMessage(Messages.IS_NOW_IN_TEAM_YELLOW.get());
                                    Inventories.updateInventories();
                                } else
                                    player.sendMessage(Messages.IS_ALREADY_IN_TEAM.get());
                            } else
                                player.sendMessage(Messages.TEAM_IS_FULL.get());
                        }
                        case LIME_BED -> {
                            if (!BedwarsCore.getInstance().getTeamManager().isFull(Game.getTeamGreen())) {
                                player.closeInventory();
                                if (!BedwarsCore.getInstance().getTeamManager().isInTeam(player, Game.getTeamGreen())) {
                                    BedwarsCore.getInstance().getTeamManager().setTeam(player, Game.getTeamGreen());
                                    player.sendMessage(Messages.IS_NOW_IN_TEAM_GREEN.get());
                                    Inventories.updateInventories();
                                } else
                                    player.sendMessage(Messages.IS_ALREADY_IN_TEAM.get());
                            } else
                                player.sendMessage(Messages.TEAM_IS_FULL.get());
                        }
                    }
                }
            }
        }
    }
}
