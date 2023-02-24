package de.mxscha.bedwars.listeners.ingame;

import de.mxscha.bedwars.BedwarsCore;
import de.mxscha.bedwars.utils.game.Game;
import de.mxscha.bedwars.utils.game.extra.GameStates;
import de.mxscha.bedwars.utils.game.extra.Stats;
import de.mxscha.bedwars.utils.manager.build.BuildManager;
import de.mxscha.bedwars.utils.manager.team.TeamManager;
import de.mxscha.bedwars.utils.text.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class IngameBedBreakListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (GameStates.getGameState() == 2) {
            Player player = event.getPlayer();
            if (!BuildManager.canBuild(player)) {
                TeamManager tm = BedwarsCore.getInstance().getTeamManager();
                switch (event.getBlock().getType()) {
                    case RED_BED -> {
                        event.setDropItems(false);
                        if (!tm.isInTeam(player, Game.getTeamRed())) {
                            event.setCancelled(false);
                            Stats.setAliveBedRed(false);
                            for (Player online : Bukkit.getOnlinePlayers()) {
                                online.playSound(online.getLocation(), Sound.ENTITY_WITHER_DEATH, 1, 1);
                            }
                            for (Player red : Game.getTeamRed().values()) {
                                red.sendTitle("§4Dein Bett wurde zerstört!", "§cDu wirst nicht mehr Respawnen!");
                            }
                            if (BedwarsCore.getInstance().getTeamManager().isInTeam(player, Game.getTeamBlue())) {
                                Bukkit.broadcastMessage(Messages.PREFIX.get() + "§4Das §cRote Bett §4wurde von §9" + player.getName() + " §4zerstört!");
                            }
                            if (BedwarsCore.getInstance().getTeamManager().isInTeam(player, Game.getTeamYellow())) {
                                Bukkit.broadcastMessage(Messages.PREFIX.get() + "§4Das §cRote Bett §4wurde von §e" + player.getName() + " §4zerstört!");
                            }
                            if (BedwarsCore.getInstance().getTeamManager().isInTeam(player, Game.getTeamGreen())) {
                                Bukkit.broadcastMessage(Messages.PREFIX.get() + "§4Das §cRote Bett §4wurde von §a" + player.getName() + " §4zerstört!");
                            }
                        } else {
                            event.setCancelled(true);
                            player.sendMessage(Messages.PREFIX.get() + "§cDu darfst dein Bett nicht zerstören!");
                        }
                    }
                    case BLUE_BED -> {
                        event.setDropItems(false);
                        if (!tm.isInTeam(player, Game.getTeamBlue())) {
                            event.setCancelled(false);
                            Stats.setAliveBedBlue(false);
                            for (Player online : Bukkit.getOnlinePlayers()) {
                                online.playSound(online.getLocation(), Sound.ENTITY_WITHER_DEATH, 1, 1);
                            }
                            for (Player red : Game.getTeamBlue().values()) {
                                red.sendTitle("§4Dein Bett wurde zerstört!", "§cDu wirst nicht mehr Respawnen!");
                            }
                            if (BedwarsCore.getInstance().getTeamManager().isInTeam(player, Game.getTeamRed())) {
                                Bukkit.broadcastMessage(Messages.PREFIX.get() + "§4Das §9Blaue Bett §4wurde von §c" + player.getName() + " §4zerstört!");
                            }
                            if (BedwarsCore.getInstance().getTeamManager().isInTeam(player, Game.getTeamYellow())) {
                                Bukkit.broadcastMessage(Messages.PREFIX.get() + "§4Das §9Blaue Bett §4wurde von §e" + player.getName() + " §4zerstört!");
                            }
                            if (BedwarsCore.getInstance().getTeamManager().isInTeam(player, Game.getTeamGreen())) {
                                Bukkit.broadcastMessage(Messages.PREFIX.get() + "§4Das §9Blaue Bett §4wurde von §a" + player.getName() + " §4zerstört!");
                            }
                        } else {
                            event.setCancelled(true);
                            player.sendMessage(Messages.PREFIX.get() + "§cDu darfst dein Bett nicht zerstören!");
                        }
                    }
                    case YELLOW_BED -> {
                        event.setDropItems(false);
                        if (!tm.isInTeam(player, Game.getTeamYellow())) {
                            event.setCancelled(false);
                            Stats.setAliveBedYellow(false);
                            for (Player online : Bukkit.getOnlinePlayers()) {
                                online.playSound(online.getLocation(), Sound.ENTITY_WITHER_DEATH, 1, 1);
                            }
                            for (Player red : Game.getTeamYellow().values()) {
                                red.sendTitle("§4Dein Bett wurde zerstört!", "§cDu wirst nicht mehr Respawnen!");
                            }
                            if (BedwarsCore.getInstance().getTeamManager().isInTeam(player, Game.getTeamRed())) {
                                Bukkit.broadcastMessage(Messages.PREFIX.get() + "§4Das §eGelbe Bett §4wurde von §c" + player.getName() + " §4zerstört!");
                            }
                            if (BedwarsCore.getInstance().getTeamManager().isInTeam(player, Game.getTeamBlue())) {
                                Bukkit.broadcastMessage(Messages.PREFIX.get() + "§4Das §eGelbe Bett §4wurde von §9" + player.getName() + " §4zerstört!");
                            }
                            if (BedwarsCore.getInstance().getTeamManager().isInTeam(player, Game.getTeamGreen())) {
                                Bukkit.broadcastMessage(Messages.PREFIX.get() + "§4Das §eGelbe Bett §4wurde von §a" + player.getName() + " §4zerstört!");
                            }
                        } else {
                            event.setCancelled(true);
                            player.sendMessage(Messages.PREFIX.get() + "§cDu darfst dein Bett nicht zerstören!");
                        }
                    }
                    case LIME_BED -> {
                        event.setDropItems(false);
                        if (!tm.isInTeam(player, Game.getTeamGreen())) {
                            event.setCancelled(false);
                            Stats.setAliveBedGreen(false);
                            for (Player online : Bukkit.getOnlinePlayers()) {
                                online.playSound(online.getLocation(), Sound.ENTITY_WITHER_DEATH, 1, 1);
                            }
                            for (Player red : Game.getTeamGreen().values()) {
                                red.sendTitle("§4Dein Bett wurde zerstört!", "§cDu wirst nicht mehr Respawnen!");
                            }
                            if (BedwarsCore.getInstance().getTeamManager().isInTeam(player, Game.getTeamRed())) {
                                Bukkit.broadcastMessage(Messages.PREFIX.get() + "§4Das §aGrüne Bett §4wurde von §c" + player.getName() + " §4zerstört!");
                            }
                            if (BedwarsCore.getInstance().getTeamManager().isInTeam(player, Game.getTeamBlue())) {
                                Bukkit.broadcastMessage(Messages.PREFIX.get() + "§4Das §aGrüne Bett §4wurde von §9" + player.getName() + " §4zerstört!");
                            }
                            if (BedwarsCore.getInstance().getTeamManager().isInTeam(player, Game.getTeamYellow())) {
                                Bukkit.broadcastMessage(Messages.PREFIX.get() + "§4Das §aGrüne Bett §4wurde von §e" + player.getName() + " §4zerstört!");
                            }
                        } else {
                            event.setCancelled(true);
                            player.sendMessage(Messages.PREFIX.get() + "§cDu darfst dein Bett nicht zerstören!");
                        }
                    }
                }
            }
        }
    }
}
