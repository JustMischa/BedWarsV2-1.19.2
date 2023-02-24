package de.mxscha.bedwars.utils.manager.inventory;

import de.mxscha.bedwars.BedwarsCore;
import de.mxscha.bedwars.utils.game.Game;
import de.mxscha.bedwars.utils.manager.inventory.item.ItemCreator;
import de.mxscha.bedwars.utils.manager.team.TeamManager;
import de.mxscha.bedwars.utils.manager.voting.MapVoting;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

public class Inventories {

    private static HashMap<Integer, Inventory> inventories;

    //Inventories
    private static Inventory TeamSelectInventory;
    private static Inventory VotingInventory;

    public Inventories() {
        inventories = new HashMap<>();
        TeamSelectInventory = Bukkit.createInventory(null, 9*3, "§8» §aTeam Auswahl");
        VotingInventory = Bukkit.createInventory(null, 9*3, "§8» §6Abstimmung");
        insertItems();
        addInList();
    }

    private void addInList() {
        inventories.put(1, TeamSelectInventory);
        inventories.put(2, VotingInventory);
    }

    public static void open(Player player, int ID) {
        if (inventories.containsKey(ID)) {
            updateInventories();
            player.openInventory(inventories.get(ID));
        }
    }

    private static void insertItems() {
        addItemsToTeamSelect();
        addItemsToVoting();
    }

    public static void updateInventories() {
        insertItems();
    }

    private static void addItemsToTeamSelect() {
        Inventory inventory = TeamSelectInventory;
        InventoryProperties.fillWithGlass(inventory);

        if (BedwarsCore.getInstance().getTeamManager().isEmpty(Game.getTeamRed())) {
            inventory.setItem(10, new ItemCreator(Material.RED_BED).setName("§8● §cTeam Rot").setLore("§8» ", "§8» ", "", "§8| §7Klicke zum beitreten!").toItemStack());
        } else {
            TeamManager tm = BedwarsCore.getInstance().getTeamManager();
            if (Game.getTeamRed().size() == 1) {
                Player player1 = tm.getPlayer(1, Game.getTeamRed());
                inventory.setItem(10, new ItemCreator(Material.RED_BED).setName("§8● §cTeam Rot").setLore("§8» §c" + player1.getName(), "§8» ", "", "§8| §7Klicke zum beitreten!").toItemStack());
            } else {
                Player player1 = tm.getPlayer(1, Game.getTeamRed());
                Player player2 = tm.getPlayer(2, Game.getTeamRed());
                inventory.setItem(10, new ItemCreator(Material.RED_BED).setName("§8● §cTeam Rot").setLore("§8» §c" + player1.getName(), "§8» §c" + player2.getName(), "" , "§8| §cVoll").toItemStack());
            }
        }
        if (BedwarsCore.getInstance().getTeamManager().isEmpty(Game.getTeamBlue())) {
            inventory.setItem(12, new ItemCreator(Material.BLUE_BED).setName("§8● §9Team Blau").setLore("§8» ", "§8» ", "", "§8| §7Klicke zum beitreten!").toItemStack());
        } else {
            TeamManager tm = BedwarsCore.getInstance().getTeamManager();
            if (Game.getTeamBlue().size() == 1) {
                Player player1 = tm.getPlayer(1, Game.getTeamBlue());
                inventory.setItem(12, new ItemCreator(Material.BLUE_BED).setName("§8● §9Team Blau").setLore("§8» §9" + player1.getName(), "§8» ", "", "§8| §7Klicke zum beitreten!").toItemStack());
            } else {
                Player player1 = tm.getPlayer(1, Game.getTeamBlue());
                Player player2 = tm.getPlayer(2, Game.getTeamBlue());
                inventory.setItem(12, new ItemCreator(Material.BLUE_BED).setName("§8● §9Team Blau").setLore("§8» §9" + player1.getName(), "§8» §9" + player2.getName(), "" , "§8| §cVoll").toItemStack());
            }
        }
        if (BedwarsCore.getInstance().getTeamManager().isEmpty(Game.getTeamYellow())) {
            inventory.setItem(14, new ItemCreator(Material.YELLOW_BED).setName("§8● §eTeam Gelb").setLore("§8» ", "§8» ", "", "§8| §7Klicke zum beitreten!").toItemStack());
        } else {
            TeamManager tm = BedwarsCore.getInstance().getTeamManager();
            if (Game.getTeamYellow().size() == 1) {
                Player player1 = tm.getPlayer(1, Game.getTeamYellow());
                inventory.setItem(14, new ItemCreator(Material.YELLOW_BED).setName("§8● §eTeam Gelb").setLore("§8» §e" + player1.getName(), "§8» ", "", "§8| §7Klicke zum beitreten!").toItemStack());
            } else {
                Player player1 = tm.getPlayer(1, Game.getTeamYellow());
                Player player2 = tm.getPlayer(2, Game.getTeamYellow());
                inventory.setItem(14, new ItemCreator(Material.YELLOW_BED).setName("§8● §eTeam Gelb").setLore("§8» §e" + player1.getName(), "§8» §e" + player2.getName(), "" , "§8| §cVoll").toItemStack());
            }
        }
        if (BedwarsCore.getInstance().getTeamManager().isEmpty(Game.getTeamGreen())) {
            inventory.setItem(16, new ItemCreator(Material.LIME_BED).setName("§8● §aTeam Grün").setLore("§8» ", "§8» ", "", "§8| §7Klicke zum beitreten!").toItemStack());
        } else {
            TeamManager tm = BedwarsCore.getInstance().getTeamManager();
            if (Game.getTeamGreen().size() == 1) {
                Player player1 = tm.getPlayer(1, Game.getTeamGreen());
                inventory.setItem(16, new ItemCreator(Material.LIME_BED).setName("§8● §aTeam Grün").setLore("§8» §a" + player1.getName(), "§8» ", "", "§8| §7Klicke zum beitreten!").toItemStack());
            } else {
                Player player1 = tm.getPlayer(1, Game.getTeamGreen());
                Player player2 = tm.getPlayer(2, Game.getTeamGreen());
                inventory.setItem(16, new ItemCreator(Material.LIME_BED).setName("§8● §aTeam Grün").setLore("§8» §a" + player1.getName(), "§8» §a" + player2.getName(), "" , "§8| §cVoll").toItemStack());
            }
        }
    }

    private static void addItemsToVoting() {
        Inventory inventory = VotingInventory;
        InventoryProperties.fillWithGlass(inventory);
        BedwarsCore.getInstance().getMapVoting().SetRandomMapsForInventory(inventory);
    }

    public static Inventory getVotingInventory() {
        return VotingInventory;
    }
}
