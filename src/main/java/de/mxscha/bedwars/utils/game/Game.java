package de.mxscha.bedwars.utils.game;

import de.mxscha.bedwars.BedwarsCore;
import de.mxscha.bedwars.utils.game.extra.Beds;
import de.mxscha.bedwars.utils.game.extra.GameStates;
import de.mxscha.bedwars.utils.manager.countdown.RestartCountdown;
import de.mxscha.bedwars.utils.manager.locations.ConfigLocationUtil;
import de.mxscha.bedwars.utils.manager.scoreboard.EndingScoreboard;
import de.mxscha.bedwars.utils.manager.scoreboard.IngameScoreboard;
import de.mxscha.bedwars.utils.manager.scoreboard.actionbar.PlayerInfoActionbar;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {

    private static final HashMap<String, Player> teamRed = new HashMap<>();
    private static final HashMap<String, Player> teamBlue = new HashMap<>();
    private static final HashMap<String, Player> teamYellow = new HashMap<>();
    private static final HashMap<String, Player> teamGreen = new HashMap<>();
    private static final ArrayList<Player> spectators = new ArrayList<>();
    private static final ArrayList<Player> players = new ArrayList<>();
    String gameMap;
    private static String winnerTeam;

    public void startLobbyPhase() {
        GameStates.setGameState(1);
        PlayerInfoActionbar.send();
    }

    public void startDefaultGame(String mapName) {
        GameStates.setGameState(2);
        this.gameMap = mapName;
        Beds.setBeds(gameMap);
        BedwarsCore.getInstance().getShopManager().spawn();
        Bukkit.getOnlinePlayers().forEach(BedwarsCore.getInstance().getTeamManager()::setRandomTeam);
        BedwarsCore.getInstance().getMapTeleport().teleportPlayersToMap(gameMap);
        Bukkit.getOnlinePlayers().forEach(BedwarsCore.getInstance().getInventoryManager()::removeItems);
        Bukkit.getOnlinePlayers().forEach(IngameScoreboard::new);
        BedwarsCore.getInstance().getSpawner().startSpawn(gameMap);
    }

    public void startEndingPhase(String winnerTeam) {
        Game.winnerTeam = winnerTeam;
        GameStates.setGameState(3);
        Location lobby = new ConfigLocationUtil("Lobby").loadLocation();
        if (lobby != null)
            Bukkit.getOnlinePlayers().forEach(Game::later);
        Bukkit.getOnlinePlayers().forEach(BedwarsCore.getInstance().getInventoryManager()::giveEndingItems);
        Bukkit.getOnlinePlayers().forEach(EndingScoreboard::new);

        switch (winnerTeam) {
            case "Red" -> Bukkit.getOnlinePlayers().forEach(player -> {
                player.sendTitle("§cTeam Rot", "§7hat das Spiel gewonnen!");
                player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
            });
            case "Blue" -> Bukkit.getOnlinePlayers().forEach(player -> {
                player.sendTitle("§9Team Blau", "§7hat das Spiel gewonnen!");
                player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
            });
            case "Yellow" -> Bukkit.getOnlinePlayers().forEach(player -> {
                player.sendTitle("§eTeam Gelb", "§7hat das Spiel gewonnen!");
                player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
            });
            case "Green" -> Bukkit.getOnlinePlayers().forEach(player -> {
                player.sendTitle("§aTeam Grün", "§7hat das Spiel gewonnen!");
                player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
            });
        }
        new RestartCountdown().start();
    }

    private static void later(Player player) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Location lobby = new ConfigLocationUtil("Lobby").loadLocation();
                if (lobby != null)
                    player.teleport(lobby);
            }
        }.runTaskLater(BedwarsCore.getInstance(), 15);
    }

    public static ArrayList<Player> getSpectators() {
        return spectators;
    }

    public void checkForEnd() {
        if (!getTeamRed().isEmpty()) {
            if (getTeamBlue().isEmpty()) {
                if (getTeamYellow().isEmpty()) {
                    if (getTeamGreen().isEmpty()) {
                        startEndingPhase("Red");
                    }
                }
            }
        } else {
            if (!getTeamBlue().isEmpty()) {
                if (getTeamRed().isEmpty()) {
                    if (getTeamYellow().isEmpty()) {
                        if (getTeamGreen().isEmpty()) {
                            startEndingPhase("Blue");
                        }
                    }
                }
            } else {
                if (!getTeamYellow().isEmpty()) {
                    if (getTeamBlue().isEmpty()) {
                        if (getTeamRed().isEmpty()) {
                            if (getTeamGreen().isEmpty()) {
                                startEndingPhase("Yellow");
                            }
                        }
                    }
                } else {
                    if (!getTeamGreen().isEmpty()) {
                        if (getTeamBlue().isEmpty()) {
                            if (getTeamYellow().isEmpty()) {
                                if (getTeamRed().isEmpty()) {
                                    startEndingPhase("Green");
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public String getGameMap() {
        return gameMap;
    }

    public static ArrayList<Player> getPlayers() {
        return players;
    }

    public static HashMap<String, Player> getTeamBlue() {
        return teamBlue;
    }

    public static HashMap<String, Player> getTeamGreen() {
        return teamGreen;
    }

    public static HashMap<String, Player> getTeamRed() {
        return teamRed;
    }

    public static HashMap<String, Player> getTeamYellow() {
        return teamYellow;
    }

    public String getWinnerTeam() {
        return winnerTeam;
    }
}
