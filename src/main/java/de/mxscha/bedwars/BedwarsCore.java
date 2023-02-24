package de.mxscha.bedwars;

import de.mxscha.bedwars.commands.BedWarsCommand;
import de.mxscha.bedwars.commands.BedWarsStartCommand;
import de.mxscha.bedwars.commands.autocomplete.BedWarsTabCompletion;
import de.mxscha.bedwars.listeners.ingame.*;
import de.mxscha.bedwars.listeners.ingame.spectator.SpectatorDamageListener;
import de.mxscha.bedwars.listeners.ingame.spectator.SpectatorDropItemListener;
import de.mxscha.bedwars.listeners.lobby.*;
import de.mxscha.bedwars.listeners.lobby.cancel.LobbyBuildListener;
import de.mxscha.bedwars.listeners.lobby.cancel.LobbyDamageListener;
import de.mxscha.bedwars.listeners.lobby.cancel.LobbyDropItemListener;
import de.mxscha.bedwars.utils.game.Game;
import de.mxscha.bedwars.utils.game.extra.Spawner;
import de.mxscha.bedwars.utils.game.map.MapTeleport;
import de.mxscha.bedwars.utils.manager.countdown.LobbyCountdown;
import de.mxscha.bedwars.utils.manager.inventory.Inventories;
import de.mxscha.bedwars.utils.manager.inventory.PlayerInventoryManager;
import de.mxscha.bedwars.utils.manager.maps.MapManager;
import de.mxscha.bedwars.utils.manager.shop.ShopManager;
import de.mxscha.bedwars.utils.manager.spectator.SpectatorManager;
import de.mxscha.bedwars.utils.manager.team.TeamManager;
import de.mxscha.bedwars.utils.manager.voting.MapVoting;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class BedwarsCore extends JavaPlugin {

    private static final String LOBBY_NAME = "Lobby-1";
    private static BedwarsCore instance;
    private MapManager mapManager;
    private Game game;
    private PlayerInventoryManager inventoryManager;
    private TeamManager teamManager;
    private LobbyCountdown lobbyCountdown;
    private MapTeleport mapTeleport;
    private MapVoting mapVoting;
    private ShopManager shopManager;
    private SpectatorManager spectatorManager;
    private Spawner spawner;

    @Override
    public void onEnable() {
        instance = this;
        initBedWars(Bukkit.getPluginManager());
        game.startLobbyPhase();
    }

    private void initBedWars(PluginManager pluginManager) {
        mapManager = new MapManager();
        game = new Game();
        inventoryManager = new PlayerInventoryManager();
        teamManager = new TeamManager();
        lobbyCountdown = new LobbyCountdown();
        mapTeleport = new MapTeleport();
        mapVoting = new MapVoting();
        shopManager = new ShopManager();
        spectatorManager = new SpectatorManager();
        spawner = new Spawner();

        new Inventories();

        pluginManager.registerEvents(new LobbyJoinListener(), this);
        pluginManager.registerEvents(new LobbyQuitListener(), this);
        pluginManager.registerEvents(new LobbyItemsListener(), this);
        pluginManager.registerEvents(new LobbyVotingListener(), this);
        pluginManager.registerEvents(new LobbyTeamSelectListener(), this);
        pluginManager.registerEvents(new LobbyBuildListener(), this);
        pluginManager.registerEvents(new LobbyChatListener(), this);
        pluginManager.registerEvents(new LobbyDamageListener(), this);
        pluginManager.registerEvents(new LobbyDropItemListener(), this);

        pluginManager.registerEvents(new SpectatorDamageListener(), this);
        pluginManager.registerEvents(new SpectatorDropItemListener(), this);

        pluginManager.registerEvents(new IngameDeathListener(), this);
        pluginManager.registerEvents(new IngameAntiTeamDamage(), this);
        pluginManager.registerEvents(new IngameBuildListener(), this);
        pluginManager.registerEvents(new IngameChatListener(), this);
        pluginManager.registerEvents(new IngameBedBreakListener(), this);


        getCommand("bedwars").setExecutor(new BedWarsCommand());
        getCommand("bedwars").setTabCompleter(new BedWarsTabCompletion());
        getCommand("start").setExecutor(new BedWarsStartCommand());

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }

    @Override
    public void onDisable() {
    }

    public Spawner getSpawner() {
        return spawner;
    }

    public SpectatorManager getSpectatorManager() {
        return spectatorManager;
    }

    public ShopManager getShopManager() {
        return shopManager;
    }

    public MapTeleport getMapTeleport() {
        return mapTeleport;
    }

    public MapVoting getMapVoting() {
        return mapVoting;
    }
    public LobbyCountdown getLobbyCountdown() {
        return lobbyCountdown;
    }

    public static String getLobbyName() {
        return LOBBY_NAME;
    }

    public TeamManager getTeamManager() {
        return teamManager;
    }

    public Game getGame() {
        return game;
    }

    public PlayerInventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public MapManager getMapManager() {
        return mapManager;
    }

    public static BedwarsCore getInstance() {
        return instance;
    }
}
