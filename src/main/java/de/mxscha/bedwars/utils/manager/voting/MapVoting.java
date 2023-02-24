package de.mxscha.bedwars.utils.manager.voting;

import de.mxscha.bedwars.BedwarsCore;
import de.mxscha.bedwars.utils.manager.inventory.Inventories;
import de.mxscha.bedwars.utils.manager.inventory.item.ItemCreator;
import de.mxscha.bedwars.utils.text.Messages;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class MapVoting {

    private final HashMap<String, Integer> voting = new HashMap<>();
    private final HashMap<Player, String> voted = new HashMap<>();
    private final HashMap<Player, Integer> slot = new HashMap<>();


    public void addVote(String map, Player player, int slot) {
        if (!voted.containsKey(player)) {
                voting.put(map, getVotes(map)+1);
                voted.put(player, map);
                player.sendMessage(Messages.PREFIX.get() + "§7Du hast für die Map §a§l" + map + " §7abgestimmt!");
                this.slot.put(player, slot);
        } else
            player.sendMessage(Messages.PREFIX.get() + "§cDu hast bereits Abgestimt!");
    }

    public void removeVote(Player player) {
        if (voted.containsKey(player)) {
            String vote = voted.get(player);
            voting.put(getPlayersVote(player), getVotes(vote)-1);
            voted.remove(player);
            if (slot.containsKey(player)) {
                Inventories.getVotingInventory().setItem(slot.get(player), new ItemCreator(Material.MAP).setName(" §8| §e" + vote)
                        .setLore("§7Klicke zum Abstimmen!", "", "§8| §a" + getVotes(vote) + " Votes").toItemStack());
            }
            slot.remove(player);
        }
    }

    public int getVotes(String map) {
        if (BedwarsCore.getInstance().getMapManager().isMapExists(map)) {
            if (voting.containsKey(map)) {
                return voting.get(map);
            } else
                voting.put(map, 0);
        }
        return 0;
    }

    public String getMapWithMostVotes() {
        // Maximal values
        int maxValue = Collections.max(voting.values());
        // Alle maps die den maxValue haben
        List<String> maxMaps = new ArrayList<>();

        // Nur maps mit der maximalen value in maxMaps speichern
        for(String map : voting.keySet()) {
            if(getVotes(map) == maxValue)
                maxMaps.add(map);
        }

        // Nehme eine Random Map von den maxMaps

        return maxMaps.get(new Random().nextInt(maxMaps.size()));
    }

    public String getPlayersVote(Player player) {
        if (voted.containsKey(player)) {
            return voted.get(player);
        }
        return null;
    }

    public boolean hasNobodyVoted() {
        return voted.size() == 0;
    }

    public void SetRandomMapsForInventory(Inventory inventory) {
        if (BedwarsCore.getInstance().getMapManager().getMaps().size() >= 3) {
            ArrayList<String> maps = BedwarsCore.getInstance().getMapManager().getThreeRandomMaps();
            inventory.setItem(11, new ItemCreator(Material.MAP).setName(" §8| §e" + maps.get(0))
                    .setLore(" §8● §7Klicke zum Abstimmen!", "", " §8| §a" + getVotes(maps.get(0)) + " Votes").toItemStack());
            inventory.setItem(13, new ItemCreator(Material.MAP).setName(" §8| §e" + maps.get(1))
                    .setLore(" §8● §7Klicke zum Abstimmen!", "", " §8| §a" + getVotes(maps.get(1)) + " Votes").toItemStack());
            inventory.setItem(15, new ItemCreator(Material.MAP).setName(" §8| §e" + maps.get(2))
                    .setLore(" §8● §7Klicke zum Abstimmen!", "", " §8| §a" + getVotes(maps.get(2)) + " Votes").toItemStack());
        } else {
            if (BedwarsCore.getInstance().getMapManager().getMaps().size() == 2) {
                inventory.setItem(11, new ItemCreator(Material.MAP).setName(" §8| §e" + BedwarsCore.getInstance().getMapManager().getMaps().get(0))
                        .setLore(" §8● §7Klicke zum Abstimmen!", "", " §8| §a" + getVotes(BedwarsCore.getInstance().getMapManager().getMaps().get(0)) + " Votes").toItemStack());
                inventory.setItem(15, new ItemCreator(Material.MAP).setName(" §8| §e" + BedwarsCore.getInstance().getMapManager().getMaps().get(1))
                        .setLore(" §8● §7Klicke zum Abstimmen!", "", " §8| §a" + getVotes(BedwarsCore.getInstance().getMapManager().getMaps().get(1)) + " Votes").toItemStack());
            } else {
                if (BedwarsCore.getInstance().getMapManager().getMaps().size() != 0) {
                    inventory.setItem(13, new ItemCreator(Material.MAP).setName(" §8| §e" + BedwarsCore.getInstance().getMapManager().getMaps().get(0))
                            .setLore(" §8● §7Klicke zum Abstimmen!", "", " §8| §a" + getVotes(BedwarsCore.getInstance().getMapManager().getMaps().get(0)) + " Votes").toItemStack());;
                } else {
                    inventory.setItem(13, new ItemCreator(Material.BARRIER).setName(" §8| §cEs gibt derzeit keine Map!").toItemStack());
                }
            }
        }
    }

    public HashMap<Player, Integer> getSlot() {
        return slot;
    }
}
