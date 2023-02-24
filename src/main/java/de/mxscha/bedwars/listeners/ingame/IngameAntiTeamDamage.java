package de.mxscha.bedwars.listeners.ingame;

import de.mxscha.bedwars.BedwarsCore;
import de.mxscha.bedwars.utils.game.Game;
import de.mxscha.bedwars.utils.game.extra.GameStates;
import de.mxscha.bedwars.utils.manager.team.TeamManager;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scoreboard.Team;

public class IngameAntiTeamDamage implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (event.getDamager() instanceof Player damager) {
                if (GameStates.getGameState() == 2) {
                    TeamManager tm = BedwarsCore.getInstance().getTeamManager();
                    if (tm.isInSameTeam(player, damager, Game.getTeamRed())) {
                        event.setCancelled(true);
                    }
                    if (tm.isInSameTeam(player, damager, Game.getTeamBlue())) {
                        event.setCancelled(true);
                    }
                    if (tm.isInSameTeam(player, damager, Game.getTeamYellow())) {
                        event.setCancelled(true);
                    }
                    if (tm.isInSameTeam(player, damager, Game.getTeamGreen())) {
                        event.setCancelled(true);
                    }
                }
            } else if (event.getDamager() instanceof Arrow arrow) {
                if (arrow.getShooter() instanceof Player damager) {
                    if (GameStates.getGameState() == 2) {
                        TeamManager tm = BedwarsCore.getInstance().getTeamManager();
                        if (tm.isInSameTeam(player, damager, Game.getTeamRed())) {
                            event.setCancelled(true);
                        }
                        if (tm.isInSameTeam(player, damager, Game.getTeamBlue())) {
                            event.setCancelled(true);
                        }
                        if (tm.isInSameTeam(player, damager, Game.getTeamYellow())) {
                            event.setCancelled(true);
                        }
                        if (tm.isInSameTeam(player, damager, Game.getTeamGreen())) {
                            event.setCancelled(true);
                        }
                    }
                }
            }
        }
    }

}
