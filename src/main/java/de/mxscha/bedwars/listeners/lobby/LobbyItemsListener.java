package de.mxscha.bedwars.listeners.lobby;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import de.mxscha.bedwars.BedwarsCore;
import de.mxscha.bedwars.utils.game.extra.GameStates;
import de.mxscha.bedwars.utils.manager.inventory.Inventories;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class LobbyItemsListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (GameStates.getGameState() == 1) {
            if(event.getAction().isRightClick()) {
                if (event.getItem() == null) return;
                switch (event.getItem().getItemMeta().getDisplayName()) {
                    case "§8» §aTeam Auswahl" -> {
                        event.setCancelled(true);
                        Inventories.open(player, 1);
                    }
                    case "§8» §6Abstimmung" -> {
                        event.setCancelled(true);
                        Inventories.open(player, 2);
                    }
                    case "§8» §cZurück zur Lobby" -> {
                        event.setCancelled(true);
                        ByteArrayDataOutput out = ByteStreams.newDataOutput();
                        out.writeUTF("Connect");
                        out.writeUTF(BedwarsCore.getLobbyName());
                        player.sendPluginMessage(BedwarsCore.getInstance(), "BungeeCord", out.toByteArray());
                    }
                }
            }
        } else if (GameStates.getGameState() == 3) {
            if(event.getAction().isRightClick()) {
                if (event.getItem() == null) return;
                if ("§8» §cZurück zur Lobby".equals(event.getItem().getItemMeta().getDisplayName())) {
                    event.setCancelled(true);
                    ByteArrayDataOutput out = ByteStreams.newDataOutput();
                    out.writeUTF("Connect");
                    out.writeUTF(BedwarsCore.getLobbyName());
                    player.sendPluginMessage(BedwarsCore.getInstance(), "BungeeCord", out.toByteArray());
                }
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player player) {
            if (GameStates.getGameState() == 1) {
                switch (event.getView().getTitle()) {
                    case "§8» §aTeam Auswahl":
                        event.setCancelled(true);
                        break;
                    case "§8» §6Voting":
                        event.setCancelled(true);
                        break;
                }
            }
        }
    }
}
