package de.mxscha.bedwars.utils.manager.team;

import de.mxscha.bedwars.utils.game.Game;
import de.mxscha.bedwars.utils.manager.scoreboard.tablist.TablistManager;
import de.mxscha.bedwars.utils.text.Messages;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class TeamManager {

    public Player getPlayer(int player, HashMap<String, Player> team) {
        if (player == 1) {
            return team.get("player1");
        } else
            return team.get("player2");
    }

    public boolean isInSameTeam(Player player, Player player2, HashMap<String, Player> team) {
        if (isInTeam(player,team)) {
            return isInTeam(player2, team);
        }
        return false;
    }

    public boolean isInTeam(Player player, HashMap<String, Player> team) {
        if (team.containsValue(player)) {
            return true;
        } else
            return false;
    }

    public boolean isInTeam(Player player) {
        if (isInTeam(player, Game.getTeamRed()) || isInTeam(player, Game.getTeamBlue()) ||
                isInTeam(player, Game.getTeamYellow()) || isInTeam(player, Game.getTeamGreen())) {
            return true;
        } else {
            return false;
        }
    }

    public void setRandomTeam(Player player) {
        if (!isInTeam(player)) {
            player.sendMessage(Messages.PREFIX.get() + "§cDu wurdest einem Team zugewiesen!");
            if (!isFull(Game.getTeamRed())) {
                setTeam(player, Game.getTeamRed());
            } else {
                if (!isFull(Game.getTeamBlue())) {
                    setTeam(player, Game.getTeamBlue());
                } else {
                    if (!isFull(Game.getTeamYellow())) {
                        setTeam(player, Game.getTeamYellow());
                    } else {
                        if (!isFull(Game.getTeamGreen())) {
                            setTeam(player, Game.getTeamGreen());
                        }
                    }
                }
            }
        }
        TablistManager.setAllPlayersTeam();
    }

    public void setTeam(Player player, HashMap<String, Player> team) {
        removeFromTeam(player);
        if (team != null) {
            if (team.get("player1") == null) {
                team.put("player1", player);
            } else {
                team.put("player2", player);
            }
        }
        player.closeInventory();
        TablistManager.setAllPlayersTeam();
    }

    public void removeFromTeam(Player player) {
        if (isInTeam(player)) {
            if (isInTeam(player, Game.getTeamRed())) {
                removeFromTeam(player, Game.getTeamRed());
            }
            if (isInTeam(player, Game.getTeamBlue())) {
                removeFromTeam(player, Game.getTeamBlue());
            }
            if (isInTeam(player, Game.getTeamYellow())) {
                removeFromTeam(player, Game.getTeamYellow());
            }
            if (isInTeam(player, Game.getTeamGreen())) {
                removeFromTeam(player, Game.getTeamGreen());
            }
        }
    }

    public void removeFromTeam(Player player, HashMap<String, Player> team) {
        if (getPlayer(1, team) == player) {
            team.remove("player1", player);
        } else if (getPlayer(2, team) == player) {
            team.remove("player2", player);
        }
    }

    public boolean isFull(HashMap<String, Player> team) {
        return team.size() == 2;
    }

    public boolean isEmpty(HashMap<String, Player> team) {
        return team.isEmpty();
    }

}
