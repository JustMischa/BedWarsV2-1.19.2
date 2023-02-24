package de.mxscha.bedwars.listeners.ingame;

import de.mxscha.bedwars.BedwarsCore;
import de.mxscha.bedwars.BedwarsCore;
import de.mxscha.bedwars.utils.game.Game;
import de.mxscha.bedwars.utils.game.extra.GameStates;
import de.mxscha.bedwars.utils.game.extra.Stats;
import de.mxscha.bedwars.utils.manager.maps.MapLocationManager;
import de.mxscha.bedwars.utils.manager.team.TeamManager;
import de.mxscha.bedwars.utils.text.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class IngameDeathListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (GameStates.getGameState() == 2) {
            Player player = event.getPlayer();
            Location deathLocation = MapLocationManager.getLocation("death", BedwarsCore.getInstance().getGame().getGameMap(), false);
            Location playersLocation = player.getLocation();
            if (playersLocation.getY() <= deathLocation.getY()) {
                player.damage(20);
            }
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        event.deathMessage(null);
        Player player = event.getPlayer();
        event.getDrops().clear();
        if (player.getKiller() != null) {
            Player killer = player.getKiller();
            TeamManager tm = BedwarsCore.getInstance().getTeamManager();
            if (tm.isInTeam(player, Game.getTeamRed())) {
                if (Stats.isAliveBedRed()) {
                    if (tm.isInTeam(killer, Game.getTeamBlue())) {
                        Bukkit.broadcastMessage(Messages.PREFIX.get() + "§c" + player.getName() + " §7wurde von §9" + killer.getName() + "§7 getötet!");
                        Stats.addKill("blau");
                    } else if (tm.isInTeam(killer, Game.getTeamYellow())) {
                        Bukkit.broadcastMessage(Messages.PREFIX.get() + "§c" + player.getName() + " §7wurde von §e" + killer.getName() + "§7 getötet!");
                        Stats.addKill("gelb");
                    } else if (tm.isInTeam(killer, Game.getTeamGreen())) {
                        Bukkit.broadcastMessage(Messages.PREFIX.get() + "§c" + player.getName() + " §7wurde von §a" + killer.getName() + "§7 getötet!");
                        Stats.addKill("grün");
                    }
                } else {
                    if (tm.isInTeam(killer, Game.getTeamBlue())) {
                        Bukkit.broadcastMessage(Messages.PREFIX.get() + "§c" + player.getName() + " §7wurde von §9" + killer.getName() + "§7 getötet!");
                        Stats.addKill("blau");
                    } else if (tm.isInTeam(killer, Game.getTeamYellow())) {
                        Bukkit.broadcastMessage(Messages.PREFIX.get() + "§c" + player.getName() + " §7wurde von §e" + killer.getName() + "§7 getötet!");
                        Stats.addKill("gelb");
                    } else if (tm.isInTeam(killer, Game.getTeamGreen())) {
                        Bukkit.broadcastMessage(Messages.PREFIX.get() + "§c" + player.getName() + " §7wurde von §a" + killer.getName() + "§7 getötet!");
                        Stats.addKill("grün");
                    }
                    BedwarsCore.getInstance().getTeamManager().removeFromTeam(player);
                    if (Game.getTeamRed().size() > 0) {
                        sendLater(Messages.PREFIX.get() + "§cTeam Rot §4hat noch §e1 Spieler§4!");
                    } else {
                        sendLater(Messages.PREFIX.get() + "§cTeam Rot §4wurde vernichtet!");
                    }
                    BedwarsCore.getInstance().getSpectatorManager().setSpectator(player);
                }
            }
            if (tm.isInTeam(player, Game.getTeamBlue())) {
                if (Stats.isAliveBedBlue()) {
                    if (tm.isInTeam(killer, Game.getTeamRed())) {
                        Bukkit.broadcastMessage(Messages.PREFIX.get() + "§9" + player.getName() + " §7wurde von §c" + killer.getName() + "§7 getötet!");
                        Stats.addKill("rot");
                    } else if (tm.isInTeam(killer, Game.getTeamYellow())) {
                        Bukkit.broadcastMessage(Messages.PREFIX.get() + "§9" + player.getName() + " §7wurde von §e" + killer.getName() + "§7 getötet!");
                        Stats.addKill("gelb");
                    } else if (tm.isInTeam(killer, Game.getTeamGreen())) {
                        Bukkit.broadcastMessage(Messages.PREFIX.get() + "§9" + player.getName() + " §7wurde von §a" + killer.getName() + "§7 getötet!");
                        Stats.addKill("grün");
                    }
                } else {
                    if (tm.isInTeam(killer, Game.getTeamRed())) {
                        Bukkit.broadcastMessage(Messages.PREFIX.get() + "§9" + player.getName() + " §7wurde von §c" + killer.getName() + "§7 getötet!");
                        Stats.addKill("rot");
                    } else if (tm.isInTeam(killer, Game.getTeamYellow())) {
                        Bukkit.broadcastMessage(Messages.PREFIX.get() + "§9" + player.getName() + " §7wurde von §e" + killer.getName() + "§7 getötet!");
                        Stats.addKill("gelb");
                    } else if (tm.isInTeam(killer, Game.getTeamGreen())) {
                        Bukkit.broadcastMessage(Messages.PREFIX.get() + "§9" + player.getName() + " §7wurde von §a" + killer.getName() + "§7 getötet!");
                        Stats.addKill("grün");
                    }
                    BedwarsCore.getInstance().getTeamManager().removeFromTeam(player);
                    if (Game.getTeamBlue().size() > 0) {
                        sendLater(Messages.PREFIX.get() + "§9Team Blau §4hat noch §e1 Spieler§4!");
                    } else {
                        sendLater(Messages.PREFIX.get() + "§9Team Blau §4wurde vernichtet!");
                    }
                    BedwarsCore.getInstance().getSpectatorManager().setSpectator(player);
                }
            }
            if (tm.isInTeam(player, Game.getTeamYellow())) {
                if (Stats.isAliveBedYellow()) {
                    if (tm.isInTeam(killer, Game.getTeamRed())) {
                        Bukkit.broadcastMessage(Messages.PREFIX.get() + "§e" + player.getName() + " §7wurde von §c" + killer.getName() + "§7 getötet!");
                        Stats.addKill("rot");
                    } else if (tm.isInTeam(killer, Game.getTeamBlue())) {
                        Bukkit.broadcastMessage(Messages.PREFIX.get() + "§e" + player.getName() + " §7wurde von §9" + killer.getName() + "§7 getötet!");
                        Stats.addKill("blau");
                    } else if (tm.isInTeam(killer, Game.getTeamGreen())) {
                        Bukkit.broadcastMessage(Messages.PREFIX.get() + "§e" + player.getName() + " §7wurde von §a" + killer.getName() + "§7 getötet!");
                        Stats.addKill("grün");
                    }
                } else {
                    if (tm.isInTeam(killer, Game.getTeamRed())) {
                        Bukkit.broadcastMessage(Messages.PREFIX.get() + "§e" + player.getName() + " §7wurde von §c" + killer.getName() + "§7 getötet!");
                        Stats.addKill("rot");
                    } else if (tm.isInTeam(killer, Game.getTeamBlue())) {
                        Bukkit.broadcastMessage(Messages.PREFIX.get() + "§e" + player.getName() + " §7wurde von §9" + killer.getName() + "§7 getötet!");
                        Stats.addKill("blau");
                    } else if (tm.isInTeam(killer, Game.getTeamGreen())) {
                        Bukkit.broadcastMessage(Messages.PREFIX.get() + "§e" + player.getName() + " §7wurde von §a" + killer.getName() + "§7 getötet!");
                        Stats.addKill("grün");
                    }
                    BedwarsCore.getInstance().getTeamManager().removeFromTeam(player);
                    if (Game.getTeamYellow().size() > 0) {
                        sendLater(Messages.PREFIX.get() + "§eTeam Gelb §4hat noch §e1 Spieler§4!");
                    } else {
                        sendLater(Messages.PREFIX.get() + "§eTeam Gelb §4wurde vernichtet!");
                    }
                    BedwarsCore.getInstance().getSpectatorManager().setSpectator(player);
                }
            }
            if (tm.isInTeam(player, Game.getTeamGreen())) {
                if (Stats.isAliveBedGreen()) {
                    if (tm.isInTeam(killer, Game.getTeamRed())) {
                        Bukkit.broadcastMessage(Messages.PREFIX.get() + "§a" + player.getName() + " §7wurde von §c" + killer.getName() + "§7 getötet!");
                        Stats.addKill("rot");
                    } else if (tm.isInTeam(killer, Game.getTeamYellow())) {
                        Bukkit.broadcastMessage(Messages.PREFIX.get() + "§a" + player.getName() + " §7wurde von §e" + killer.getName() + "§7 getötet!");
                        Stats.addKill("gelb");
                    } else if (tm.isInTeam(killer, Game.getTeamBlue())) {
                        Bukkit.broadcastMessage(Messages.PREFIX.get() + "§a" + player.getName() + " §7wurde von §9" + killer.getName() + "§7 getötet!");
                        Stats.addKill("blau");
                    }
                } else {
                    if (tm.isInTeam(killer, Game.getTeamRed())) {
                        Bukkit.broadcastMessage(Messages.PREFIX.get() + "§a" + player.getName() + " §7wurde von §c" + killer.getName() + "§7 getötet!");
                        Stats.addKill("rot");
                    } else if (tm.isInTeam(killer, Game.getTeamYellow())) {
                        Bukkit.broadcastMessage(Messages.PREFIX.get() + "§a" + player.getName() + " §7wurde von §e" + killer.getName() + "§7 getötet!");
                        Stats.addKill("gelb");
                    } else if (tm.isInTeam(killer, Game.getTeamBlue())) {
                        Bukkit.broadcastMessage(Messages.PREFIX.get() + "§a" + player.getName() + " §7wurde von §9" + killer.getName() + "§7 getötet!");
                        Stats.addKill("blau");
                    }
                    BedwarsCore.getInstance().getTeamManager().removeFromTeam(player);
                    if (Game.getTeamGreen().size() > 0) {
                        sendLater(Messages.PREFIX.get() + "§aTeam Grün §4hat noch §e1 Spieler§4!");
                    } else {
                        sendLater(Messages.PREFIX.get() + "§aTeam Grün §4wurde vernichtet!");
                    }
                    BedwarsCore.getInstance().getSpectatorManager().setSpectator(player);
                }
            }
        } else {
            TeamManager tm = BedwarsCore.getInstance().getTeamManager();
            if (tm.isInTeam(player, Game.getTeamRed())) {
                if (Stats.isAliveBedRed()) {
                    Bukkit.broadcastMessage(Messages.PREFIX.get() + "§c" + player.getName() + " §7ist gestorben!");
                } else {
                    Bukkit.broadcastMessage(Messages.PREFIX.get() + "§c" + player.getName() + " §7ist gestorben!");
                    BedwarsCore.getInstance().getTeamManager().removeFromTeam(player);
                    if (Game.getTeamRed().size() > 0) {
                        sendLater(Messages.PREFIX.get() + "§cTeam Rot §4hat noch §e1 Spieler§4!");
                    } else {
                        sendLater(Messages.PREFIX.get() + "§cTeam Rot §4wurde vernichtet!");
                    }
                    BedwarsCore.getInstance().getSpectatorManager().setSpectator(player);
                }
            }
            if (tm.isInTeam(player, Game.getTeamBlue())) {
                if (Stats.isAliveBedBlue()) {
                    Bukkit.broadcastMessage(Messages.PREFIX.get() + "§9" + player.getName() + " §7ist gestorben!");
                } else {
                    Bukkit.broadcastMessage(Messages.PREFIX.get() + "§9" + player.getName() + " §7ist gestorben!");
                    BedwarsCore.getInstance().getTeamManager().removeFromTeam(player);
                    if (Game.getTeamBlue().size() > 0) {
                        sendLater(Messages.PREFIX.get() + "§9Team Blau §4hat noch §e1 Spieler§4!");
                    } else {
                        sendLater(Messages.PREFIX.get() + "§9Team Blau §4wurde vernichtet!");
                    }
                    BedwarsCore.getInstance().getSpectatorManager().setSpectator(player);
                }
            }
            if (tm.isInTeam(player, Game.getTeamYellow())) {
                if (Stats.isAliveBedYellow()) {
                    Bukkit.broadcastMessage(Messages.PREFIX.get() + "§e" + player.getName() + " §7ist gestorben!");
                } else {
                    Bukkit.broadcastMessage(Messages.PREFIX.get() + "§e" + player.getName() + " §7ist gestorben!");
                    BedwarsCore.getInstance().getTeamManager().removeFromTeam(player);
                    if (Game.getTeamYellow().size() > 0) {
                        sendLater(Messages.PREFIX.get() + "§eTeam Gelb §4hat noch §e1 Spieler§4!");
                    } else {
                        sendLater(Messages.PREFIX.get() + "§eTeam Gelb §4wurde vernichtet!");
                    }
                    BedwarsCore.getInstance().getSpectatorManager().setSpectator(player);
                }
            }
            if (tm.isInTeam(player, Game.getTeamGreen())) {
                if (Stats.isAliveBedGreen()) {
                    Bukkit.broadcastMessage(Messages.PREFIX.get() + "§a" + player.getName() + " §7ist gestorben!");
                } else {
                    Bukkit.broadcastMessage(Messages.PREFIX.get() + "§a" + player.getName() + " §7ist gestorben!");
                    BedwarsCore.getInstance().getTeamManager().removeFromTeam(player);
                    if (Game.getTeamGreen().size() > 0) {
                        sendLater(Messages.PREFIX.get() + "§aTeam Grün §4hat noch §e1 Spieler§4!");
                    } else {
                        sendLater(Messages.PREFIX.get() + "§aTeam Grün §4wurde vernichtet!");
                    }
                    BedwarsCore.getInstance().getSpectatorManager().setSpectator(player);
                }
            }
        }
        respawn(event.getPlayer());
        BedwarsCore.getInstance().getGame().checkForEnd();
    }

    private void sendLater(String message) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(message);
            }
        }.runTaskLater(BedwarsCore.getInstance(), 1);
    }

    public static void respawn(Player player) {
        new BukkitRunnable() {
            @Override
            public void run() {
                player.spigot().respawn();
            }
        }.runTaskLater(BedwarsCore.getInstance(), 3);
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        String map = BedwarsCore.getInstance().getGame().getGameMap();
        if (GameStates.getGameState() == 2) {
            if (BedwarsCore.getInstance().getMapManager().isMapExists(map)) {
                Location SpawnRot = MapLocationManager.getLocation("SpawnRot", map, true);
                Location SpawnGelb = MapLocationManager.getLocation("SpawnGelb", map, true);
                Location SpawnGrün = MapLocationManager.getLocation("SpawnGrün", map, true);
                Location SpawnBlau = MapLocationManager.getLocation("SpawnBlau", map, true);
                if (Game.getTeamRed().containsValue(player)) {
                    event.setRespawnLocation(SpawnRot);
                    player.teleport(SpawnRot);
                }
                if (Game.getTeamBlue().containsValue(player)) {
                    event.setRespawnLocation(SpawnBlau);
                    player.teleport(SpawnBlau);
                }
                if (Game.getTeamYellow().containsValue(player)) {
                    event.setRespawnLocation(SpawnGelb);
                    player.teleport(SpawnGelb);
                }
                if (Game.getTeamGreen().containsValue(player)) {
                    event.setRespawnLocation(SpawnGrün);
                    player.teleport(SpawnGrün);
                }
            }
        }
    }
}
