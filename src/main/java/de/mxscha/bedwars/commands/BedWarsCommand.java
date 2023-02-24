package de.mxscha.bedwars.commands;

import de.mxscha.bedwars.BedwarsCore;
import de.mxscha.bedwars.utils.manager.locations.ConfigLocationUtil;
import de.mxscha.bedwars.utils.manager.maps.MapLocationManager;
import de.mxscha.bedwars.utils.text.Messages;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BedWarsCommand implements CommandExecutor {

    private String selectedMap = "";

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if(player.hasPermission("bedwars.admin")) {
                switch (args.length) {
                    case 0:
                        player.sendMessage(Messages.PLS_USE.get() + "§e/Bedwars help§c!");
                        break;
                    case 1:
                        switch (args[0].toLowerCase()) {
                            case "help":
                                player.sendMessage("§8§m                                                                       ");
                                player.sendMessage(" §8» §e/bedwars §7add Map <MapName> §8● §7...");
                                player.sendMessage(" §8» §e/bedwars §7select Map <MapName> §8● §7...");
                                player.sendMessage(" §8» §e/bedwars §7unselect Map <MapName> §8● §7...");
                                player.sendMessage(" §8» §e/bedwars §7setup Map §8● §7...");
                                player.sendMessage(" §8» §e/bedwars §7setup Team <Team> §8● §7...");
                                player.sendMessage(" §8» §e/bedwars §7setup Spawners §8● §7...");
                                player.sendMessage("§8§m                                                                       ");
                                break;
                        }
                        break;
                    case 2:
                        switch (args[0].toLowerCase()) {
                            case "setup":
                                switch (args[1].toLowerCase()) {
                                    case "map":
                                        if (!selectedMap.equals("")) {
                                            player.sendMessage("§8§m                                                                            ");
                                            player.sendMessage(" §8» §7Ausgewählte Map§8: §a§l" + selectedMap);
                                            player.sendMessage(" ");

                                            var Text = new TextComponent(" §8» ");
                                            var Middle = new TextComponent(" §8● §");
                                            var ClickHere = new TextComponent("§7Klicke ");
                                            var toSet = new TextComponent(" §7um diese Location zu Setzen!");

                                            var set1 = new TextComponent("§8[§6§lHier§8]");
                                            var set2 = new TextComponent("§8[§6§lHier§8]");
                                            var set3 = new TextComponent("§8[§6§lHier§8]");

                                            var SetLobby = new TextComponent("§aLobby Spawn");
                                            var SetSpectator = new TextComponent("§aSpectators");
                                            var SetDeath = new TextComponent("§aDeath height");

                                            set1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§a§lSetzen").color(ChatColor.GREEN).create()));
                                            set2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§a§lSetzen").color(ChatColor.GREEN).create()));
                                            set3.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§a§lSetzen").color(ChatColor.GREEN).create()));

                                            set1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/bedwars setup set lobby"));
                                            set2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/bedwars setup set spectators"));
                                            set3.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/bedwars setup set deathheight"));

                                            player.sendMessage(Text, SetLobby, Middle, ClickHere, set1, toSet);
                                            player.sendMessage(Text, SetSpectator, Middle,  ClickHere, set2, toSet);
                                            player.sendMessage(Text, SetDeath, Middle,  ClickHere, set3, toSet);

                                            player.sendMessage("§8§m                                                                            ");
                                        } else
                                            player.sendMessage(Messages.PREFIX.get() + "§cBitte wähle eine Map aus!");
                                        break;
                                    case "spawners":
                                        if (!selectedMap.equals("")) {
                                            player.sendMessage("§8§m                                                                            ");
                                            player.sendMessage(" §8» §7Ausgewählte Map§8: §a§l" + selectedMap);
                                            player.sendMessage(" ");

                                            var Text = new TextComponent(" §8» ");
                                            var Middle = new TextComponent(" §8● §");
                                            var ClickHere = new TextComponent("§7Klicke ");
                                            var toSet = new TextComponent(" §7um diese Location zu Setzen!");

                                            var set1 = new TextComponent("§8[§6§lHier§8]");
                                            var set2 = new TextComponent("§8[§6§lHier§8]");
                                            var set3 = new TextComponent("§8[§6§lHier§8]");
                                            var set4 = new TextComponent("§8[§6§lHier§8]");

                                            var set5 = new TextComponent("§8[§6§lHier§8]");
                                            var set6 = new TextComponent("§8[§6§lHier§8]");
                                            var set7 = new TextComponent("§8[§6§lHier§8]");
                                            var set8 = new TextComponent("§8[§6§lHier§8]");

                                            var set9 = new TextComponent("§8[§6§lHier§8]");
                                            var set10 = new TextComponent("§8[§6§lHier§8]");
                                            var set11 = new TextComponent("§8[§6§lHier§8]");
                                            var set12 = new TextComponent("§8[§6§lHier§8]");

                                            var BronzeRot = new TextComponent("§cBronze Spawner Rot");
                                            var BronzeBlau = new TextComponent("§cBronze Spawner §9Blau");
                                            var BronzeGelb = new TextComponent("§cBronze Spawner §eGelb");
                                            var BronzeGrün = new TextComponent("§cBronze Spawner §aGrün");

                                            var EisenRot = new TextComponent("§fEisen Spawner Rot");
                                            var EisenBlau = new TextComponent("§fEisen Spawner §9Blau");
                                            var EisenGelb = new TextComponent("§fEisen Spawner §eGelb");
                                            var EisenGrün = new TextComponent("§fEisen Spawner §aGrün");

                                            var GoldRot = new TextComponent("§6Gold Spawner Rot");
                                            var GoldBlau = new TextComponent("§6Gold Spawner §9Blau");
                                            var GoldGelb = new TextComponent("§6Gold Spawner §eGelb");
                                            var GoldGrün = new TextComponent("§6Gold Spawner §aGrün");

                                            set1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§a§lSetzen").color(ChatColor.GREEN).create()));
                                            set2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§a§lSetzen").color(ChatColor.GREEN).create()));
                                            set3.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§a§lSetzen").color(ChatColor.GREEN).create()));
                                            set4.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§a§lSetzen").color(ChatColor.GREEN).create()));
                                            set5.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§a§lSetzen").color(ChatColor.GREEN).create()));
                                            set6.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§a§lSetzen").color(ChatColor.GREEN).create()));
                                            set7.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§a§lSetzen").color(ChatColor.GREEN).create()));
                                            set8.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§a§lSetzen").color(ChatColor.GREEN).create()));
                                            set9.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§a§lSetzen").color(ChatColor.GREEN).create()));
                                            set10.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§a§lSetzen").color(ChatColor.GREEN).create()));
                                            set11.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§a§lSetzen").color(ChatColor.GREEN).create()));
                                            set12.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§a§lSetzen").color(ChatColor.GREEN).create()));

                                            set1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/bedwars setup set spawner bronze rot"));
                                            set2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/bedwars setup set spawner bronze blau"));
                                            set3.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/bedwars setup set spawner bronze gelb"));
                                            set4.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/bedwars setup set spawner bronze grün"));
                                            set5.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/bedwars setup set spawner eisen rot"));
                                            set6.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/bedwars setup set spawner eisen blau"));
                                            set7.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/bedwars setup set spawner eisen gelb"));
                                            set8.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/bedwars setup set spawner eisen grün"));
                                            set9.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/bedwars setup set spawner gold rot"));
                                            set10.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/bedwars setup set spawner gold blau"));
                                            set11.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/bedwars setup set spawner gold gelb"));
                                            set12.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/bedwars setup set spawner gold grün"));

                                            player.sendMessage(Text, BronzeRot, Middle, ClickHere, set1, toSet);
                                            player.sendMessage(Text, BronzeBlau, Middle,  ClickHere, set2, toSet);
                                            player.sendMessage(Text, BronzeGelb, Middle,  ClickHere, set3, toSet);
                                            player.sendMessage(Text, BronzeGrün, Middle,  ClickHere, set4, toSet);
                                            player.sendMessage(Text, EisenRot, Middle, ClickHere, set5, toSet);
                                            player.sendMessage(Text, EisenBlau, Middle,  ClickHere, set6, toSet);
                                            player.sendMessage(Text, EisenGelb, Middle,  ClickHere, set7, toSet);
                                            player.sendMessage(Text, EisenGrün, Middle,  ClickHere, set8, toSet);
                                            player.sendMessage(Text, GoldRot, Middle, ClickHere, set9, toSet);
                                            player.sendMessage(Text, GoldBlau, Middle,  ClickHere, set10, toSet);
                                            player.sendMessage(Text, GoldGelb, Middle,  ClickHere, set11, toSet);
                                            player.sendMessage(Text, GoldGrün, Middle,  ClickHere, set12, toSet);

                                            player.sendMessage("§8§m                                                                            ");
                                        } else
                                            player.sendMessage(Messages.PREFIX.get() + "§cBitte wähle eine Map aus!");
                                        break;
                                }
                                break;
                        }
                        break;
                    case 3:
                        switch (args[0].toLowerCase()) {
                            case "add":
                                if (args[1].toLowerCase().equals("map")) {
                                    String mapName = args[2];
                                    if (!BedwarsCore.getInstance().getMapManager().isMapExists(mapName)) {
                                        BedwarsCore.getInstance().getMapManager().createMap(mapName);
                                        player.sendMessage(Messages.PREFIX.get() + "§7Du hast die Map §8[§6"+mapName+"§8] §7erstellt!");
                                    } else
                                        player.sendMessage(Messages.PREFIX.get() + "§cEine Map mit diesem Namen existstiert bereits!");
                                }
                                break;
                            case "select":
                                if (args[1].toLowerCase().equals("map")) {
                                    String mapName = args[2];
                                    if (BedwarsCore.getInstance().getMapManager().isMapExists(mapName)) {
                                        if (!selectedMap.equals(mapName)) {
                                            selectedMap = mapName;
                                            player.sendMessage(Messages.PREFIX.get() + "§7Du hast die Map §8[§6"+mapName+"§8] §7ausgewählt!");
                                        } else
                                            player.sendMessage(Messages.PREFIX.get() + "§cDiese Map ist bereits ausgewählt!");
                                    } else
                                        player.sendMessage(Messages.PREFIX.get() + "§cEine Map mit diesem Namen existstiert nicht!");
                                }
                                break;
                            case "unselect":
                                if (args[1].toLowerCase().equals("map")) {
                                    String mapName = args[2];
                                    if (BedwarsCore.getInstance().getMapManager().isMapExists(mapName)) {
                                           if (!selectedMap.equals("")) {
                                            if (selectedMap.equals(mapName)) {
                                                selectedMap = "";
                                                player.sendMessage(Messages.PREFIX.get() + "§7Die Map §8[§6"+mapName+"§8] §7ist nicht mehr ausgewählt!");
                                            } else
                                                player.sendMessage(Messages.PREFIX.get() + "§cDiese Map ist nicht ausgewählt!");
                                        }
                                    } else
                                        player.sendMessage(Messages.PREFIX.get() + "§cEine Map mit diesem Namen existstiert nicht!");
                                }
                                break;
                            case "setup":
                                if (args[1].toLowerCase().equals("team")) {
                                    switch (args[2].toLowerCase()) {
                                        case "rot":
                                               if (!selectedMap.equals("")) {
                                                player.sendMessage("§8§m                                                                            ");
                                                player.sendMessage(" §8» §7Ausgewählte Map§8: §a§l" + selectedMap);
                                                player.sendMessage(" §8» §7Ausgewähltes Team§8: §c§lRot");
                                                player.sendMessage(" ");

                                                var Text = new TextComponent(" §8» ");
                                                var Middle = new TextComponent(" §8● §");
                                                var ClickHere = new TextComponent("§7Klicke ");
                                                var toSet = new TextComponent(" §7um diese Location zu Setzen!");

                                                var set1 = new TextComponent("§8[§6§lHier§8]");
                                                var set2 = new TextComponent("§8[§6§lHier§8]");
                                                var set3 = new TextComponent("§8[§6§lHier§8]");
                                                var set4 = new TextComponent("§8[§6§lHier§8]");

                                                var SetSpawn = new TextComponent("§cSpawn");
                                                var SetShop = new TextComponent("§cShop");
                                                var SetBed1 = new TextComponent("§c1 Betthälfte");
                                                var SetBed2 = new TextComponent("§c2 Betthälfte");

                                                set1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§c§lSetzen").color(ChatColor.RED).create()));
                                                set2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§c§lSetzen").color(ChatColor.RED).create()));
                                                set3.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§c§lSetzen").color(ChatColor.RED).create()));
                                                set4.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§c§lSetzen").color(ChatColor.RED).create()));

                                                set1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/bedwars setup set spawn rot"));
                                                set2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/bedwars setup set shop rot"));
                                                set3.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/bedwars setup set bed rot 1"));
                                                set4.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/bedwars setup set bed rot 2"));

                                                player.sendMessage(Text, SetSpawn, Middle, ClickHere, set1, toSet);
                                                player.sendMessage(Text, SetShop, Middle,  ClickHere, set2, toSet);
                                                player.sendMessage(Text, SetBed1, Middle,  ClickHere, set3, toSet);
                                                player.sendMessage(Text, SetBed2, Middle,  ClickHere, set4, toSet);

                                                player.sendMessage("§8§m                                                                            ");
                                            } else
                                                player.sendMessage(Messages.PREFIX.get() + "§cBitte wähle eine Map aus!");
                                            break;
                                        case "grün":
                                               if (!selectedMap.equals("")) {
                                                player.sendMessage("§8§m                                                                            ");
                                                player.sendMessage(" §8» §7Ausgewählte Map§8: §a§l" + selectedMap);
                                                player.sendMessage(" §8» §7Ausgewähltes Team§8: §a§lGrün");
                                                player.sendMessage(" ");

                                                var Text = new TextComponent(" §8» ");
                                                var Middle = new TextComponent(" §8● §");
                                                var ClickHere = new TextComponent("§7Klicke ");
                                                var toSet = new TextComponent(" §7um diese Location zu Setzen!");

                                                var set1 = new TextComponent("§8[§6§lHier§8]");
                                                var set2 = new TextComponent("§8[§6§lHier§8]");
                                                var set3 = new TextComponent("§8[§6§lHier§8]");
                                                var set4 = new TextComponent("§8[§6§lHier§8]");

                                                var SetSpawn = new TextComponent("§aSpawn");
                                                var SetShop = new TextComponent("§aShop");
                                                var SetBed1 = new TextComponent("§a1 Betthälfte");
                                                var SetBed2 = new TextComponent("§a2 Betthälfte");

                                                set1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§a§lSetzen").color(ChatColor.GREEN).create()));
                                                set2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§a§lSetzen").color(ChatColor.GREEN).create()));
                                                set3.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§a§lSetzen").color(ChatColor.GREEN).create()));
                                                set4.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§a§lSetzen").color(ChatColor.GREEN).create()));

                                                set1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/bedwars setup set spawn grün"));
                                                set2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/bedwars setup set shop grün"));
                                                set3.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/bedwars setup set bed grün 1"));
                                                set4.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/bedwars setup set bed grün 2"));

                                                player.sendMessage(Text, SetSpawn, Middle, ClickHere, set1, toSet);
                                                player.sendMessage(Text, SetShop, Middle,  ClickHere, set2, toSet);
                                                player.sendMessage(Text, SetBed1, Middle,  ClickHere, set3, toSet);
                                                player.sendMessage(Text, SetBed2, Middle,  ClickHere, set4, toSet);

                                                player.sendMessage("§8§m                                                                            ");
                                            } else
                                                player.sendMessage(Messages.PREFIX.get() + "§cBitte wähle eine Map aus!");
                                            break;
                                        case "blau":
                                               if (!selectedMap.equals("")) {
                                                player.sendMessage("§8§m                                                                            ");
                                                player.sendMessage(" §8» §7Ausgewählte Map§8: §a§l" + selectedMap);
                                                player.sendMessage(" §8» §7Ausgewähltes Team§8: §9§lBlau");
                                                player.sendMessage(" ");

                                                var Text = new TextComponent(" §8» ");
                                                var Middle = new TextComponent(" §8● §");
                                                var ClickHere = new TextComponent("§7Klicke ");
                                                var toSet = new TextComponent(" §7um diese Location zu Setzen!");

                                                var set1 = new TextComponent("§8[§6§lHier§8]");
                                                var set2 = new TextComponent("§8[§6§lHier§8]");
                                                var set3 = new TextComponent("§8[§6§lHier§8]");
                                                var set4 = new TextComponent("§8[§6§lHier§8]");

                                                var SetSpawn = new TextComponent("§9Spawn");
                                                var SetShop = new TextComponent("§9Shop");
                                                var SetBed1 = new TextComponent("§91 Betthälfte");
                                                var SetBed2 = new TextComponent("§92 Betthälfte");

                                                set1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§9§lSetzen").color(ChatColor.BLUE).create()));
                                                set2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§9§lSetzen").color(ChatColor.BLUE).create()));
                                                set3.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§9§lSetzen").color(ChatColor.BLUE).create()));
                                                set4.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§9§lSetzen").color(ChatColor.BLUE).create()));

                                                set1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/bedwars setup set spawn blau"));
                                                set2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/bedwars setup set shop blau"));
                                                set3.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/bedwars setup set bed blau 1"));
                                                set4.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/bedwars setup set bed blau 2"));

                                                player.sendMessage(Text, SetSpawn, Middle, ClickHere, set1, toSet);
                                                player.sendMessage(Text, SetShop, Middle,  ClickHere, set2, toSet);
                                                player.sendMessage(Text, SetBed1, Middle,  ClickHere, set3, toSet);
                                                player.sendMessage(Text, SetBed2, Middle,  ClickHere, set4, toSet);

                                                player.sendMessage("§8§m                                                                            ");
                                            } else
                                                player.sendMessage(Messages.PREFIX.get() + "§cBitte wähle eine Map aus!");
                                            break;
                                        case "gelb":
                                               if (!selectedMap.equals("")) {
                                                player.sendMessage("§8§m                                                                            ");
                                                player.sendMessage(" §8» §7Ausgewählte Map§8: §a§l" + selectedMap);
                                                player.sendMessage(" §8» §7Ausgewähltes Team§8: §e§lGelb");
                                                player.sendMessage(" ");

                                                var Text = new TextComponent(" §8» ");
                                                var Middle = new TextComponent(" §8● §");
                                                var ClickHere = new TextComponent("§7Klicke ");
                                                var toSet = new TextComponent(" §7um diese Location zu Setzen!");

                                                var set1 = new TextComponent("§8[§6§lHier§8]");
                                                var set2 = new TextComponent("§8[§6§lHier§8]");
                                                var set3 = new TextComponent("§8[§6§lHier§8]");
                                                var set4 = new TextComponent("§8[§6§lHier§8]");

                                                var SetSpawn = new TextComponent("§eSpawn");
                                                var SetShop = new TextComponent("§eShop");
                                                var SetBed1 = new TextComponent("§e1 Betthälfte");
                                                var SetBed2 = new TextComponent("§e2 Betthälfte");

                                                set1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§e§lSetzen").color(ChatColor.YELLOW).create()));
                                                set2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§e§lSetzen").color(ChatColor.YELLOW).create()));
                                                set3.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§e§lSetzen").color(ChatColor.YELLOW).create()));
                                                set4.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§e§lSetzen").color(ChatColor.YELLOW).create()));

                                                set1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/bedwars setup set spawn gelb"));
                                                set2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/bedwars setup set shop gelb"));
                                                set3.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/bedwars setup set bed gelb 1"));
                                                set4.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/bedwars setup set bed gelb 2"));

                                                player.sendMessage(Text, SetSpawn, Middle, ClickHere, set1, toSet);
                                                player.sendMessage(Text, SetShop, Middle,  ClickHere, set2, toSet);
                                                player.sendMessage(Text, SetBed1, Middle,  ClickHere, set3, toSet);
                                                player.sendMessage(Text, SetBed2, Middle,  ClickHere, set4, toSet);

                                                player.sendMessage("§8§m                                                                            ");
                                            } else
                                                player.sendMessage(Messages.PREFIX.get() + "§cBitte wähle eine Map aus!");
                                            break;

                                    }
                                } else if (args[1].toLowerCase().equals("set")) {
                                    switch (args[2].toLowerCase()) {
                                        case "lobby":
                                               if (!selectedMap.equals("")) {
                                                new ConfigLocationUtil(player.getLocation(), "Lobby").saveLocation();
                                                player.sendMessage(Messages.PREFIX.get() + "§7Du hast den §6Lobby Spawn §7gesetzt!");
                                            } else
                                                player.sendMessage(Messages.PREFIX.get() + "§cBitte wähle eine Map aus!");
                                            break;
                                        case "spectators":
                                               if (!selectedMap.equals("")) {
                                                MapLocationManager.setLocation(player.getLocation(), "Spectators", selectedMap);
                                                player.sendMessage(Messages.PREFIX.get() + "§7Du hast den §6Specator Spawn §7gesetzt!");
                                            } else
                                                player.sendMessage(Messages.PREFIX.get() + "§cBitte wähle eine Map aus!");
                                            break;
                                        case "deathheight":
                                               if (!selectedMap.equals("")) {
                                                MapLocationManager.setLocation(player.getLocation(), "death", selectedMap);
                                                player.sendMessage(Messages.PREFIX.get() + "§7Du hast die §6Todeshöhe §7gesetzt!");
                                            } else
                                                player.sendMessage(Messages.PREFIX.get() + "§cBitte wähle eine Map aus!");
                                            break;
                                    }
                                }
                                break;

                        }
                        break;
                    case 4:
                        if (args[0].toLowerCase().equals("setup")) {
                            if (args[1].toLowerCase().equals("set")) {
                                switch (args[2].toLowerCase()) {
                                    case "spawn":
                                        switch (args[3].toLowerCase()) {
                                            case "rot":
                                                   if (!selectedMap.equals("")) {
                                                    MapLocationManager.setLocation(player.getLocation(), "SpawnRot", selectedMap);
                                                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast den Spawn von §cTeam Rot §7gesetzt!");
                                                } else
                                                    player.sendMessage(Messages.PREFIX.get() + "§cBitte wähle eine Map aus!");
                                                break;
                                            case "blau":
                                                   if (!selectedMap.equals("")) {
                                                    MapLocationManager.setLocation(player.getLocation(), "SpawnBlau", selectedMap);
                                                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast den Spawn von §9Team Blau §7gesetzt!");
                                                } else
                                                    player.sendMessage(Messages.PREFIX.get() + "§cBitte wähle eine Map aus!");
                                                break;
                                            case "grün":
                                                   if (!selectedMap.equals("")) {
                                                    MapLocationManager.setLocation(player.getLocation(), "SpawnGrün", selectedMap);
                                                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast den Spawn von §aTeam Grün §7gesetzt!");
                                                } else
                                                    player.sendMessage(Messages.PREFIX.get() + "§cBitte wähle eine Map aus!");
                                                break;
                                            case "gelb":
                                                   if (!selectedMap.equals("")) {
                                                    MapLocationManager.setLocation(player.getLocation(), "SpawnGelb", selectedMap);
                                                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast den Spawn von §eTeam Gelb §7gesetzt!");
                                                } else
                                                    player.sendMessage(Messages.PREFIX.get() + "§cBitte wähle eine Map aus!");
                                                break;
                                        }
                                        break;
                                    case "shop":
                                        switch (args[3].toLowerCase()) {
                                            case "rot":
                                                   if (!selectedMap.equals("")) {
                                                    MapLocationManager.setLocation(player.getLocation(), "ShopRot", selectedMap);
                                                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast den Shop von §cTeam Rot §7gesetzt!");
                                                } else
                                                    player.sendMessage(Messages.PREFIX.get() + "§cBitte wähle eine Map aus!");
                                                break;
                                            case "blau":
                                                   if (!selectedMap.equals("")) {
                                                    MapLocationManager.setLocation(player.getLocation(), "ShopBlau", selectedMap);
                                                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast den Shop von §9Team Blau §7gesetzt!");
                                                } else
                                                    player.sendMessage(Messages.PREFIX.get() + "§cBitte wähle eine Map aus!");
                                                break;
                                            case "grün":
                                                   if (!selectedMap.equals("")) {
                                                    MapLocationManager.setLocation(player.getLocation(), "ShopGrün", selectedMap);
                                                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast den Shop von §aTeam Grün §7gesetzt!");
                                                } else
                                                    player.sendMessage(Messages.PREFIX.get() + "§cBitte wähle eine Map aus!");
                                                break;
                                            case "gelb":
                                                   if (!selectedMap.equals("")) {
                                                    MapLocationManager.setLocation(player.getLocation(), "ShopGelb", selectedMap);
                                                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast den Shop von §eTeam Gelb §7gesetzt!");
                                                } else
                                                    player.sendMessage(Messages.PREFIX.get() + "§cBitte wähle eine Map aus!");
                                                break;
                                        }
                                        break;
                                }
                            }
                        }
                        break;
                    case 5:
                        if (args[0].toLowerCase().equals("setup")) {
                            if (args[1].toLowerCase().equals("set")) {
                                if (args[2].toLowerCase().equals("bed")) {
                                    switch (args[3].toLowerCase()) {
                                        case "rot":
                                               if (!selectedMap.equals("")) {
                                                if (args[4].equals("1")) {
                                                    MapLocationManager.setLocation(player.getLocation(), "BedRot1", selectedMap);
                                                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast die 1. Betthälfte von §cTeam Rot §7gesetzt!");
                                                } else if (args[4].equals("2")) {
                                                    MapLocationManager.setLocation(player.getLocation(), "BedRot2", selectedMap);
                                                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast die 2. Betthälfte von §cTeam Rot §7gesetzt!");
                                                }
                                            } else
                                                player.sendMessage(Messages.PREFIX.get() + "§cBitte wähle eine Map aus!");
                                            break;
                                        case "blau":
                                               if (!selectedMap.equals("")) {
                                                if (args[4].equals("1")) {
                                                    MapLocationManager.setLocation(player.getLocation(), "BedBlau1", selectedMap);
                                                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast die 1. Betthälfte von §9Team Blau §7gesetzt!");
                                                } else if (args[4].equals("2")) {
                                                    MapLocationManager.setLocation(player.getLocation(), "BedBlau2", selectedMap);
                                                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast die 2. Betthälfte von §9Team Blau §7gesetzt!");
                                                }
                                            } else
                                                player.sendMessage(Messages.PREFIX.get() + "§cBitte wähle eine Map aus!");
                                            break;
                                        case "grün":
                                               if (!selectedMap.equals("")) {
                                                if (args[4].equals("1")) {
                                                    MapLocationManager.setLocation(player.getLocation(), "BedGrün1", selectedMap);
                                                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast die 1. Betthälfte von §aTeam Grün §7gesetzt!");
                                                } else if (args[4].equals("2")) {
                                                    MapLocationManager.setLocation(player.getLocation(), "BedGrün2", selectedMap);
                                                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast die 2. Betthälfte von §aTeam Grün §7gesetzt!");
                                                }
                                            } else
                                                player.sendMessage(Messages.PREFIX.get() + "§cBitte wähle eine Map aus!");
                                            break;
                                        case "gelb":
                                               if (!selectedMap.equals("")) {
                                                if (args[4].equals("1")) {
                                                    MapLocationManager.setLocation(player.getLocation(), "BedGelb1", selectedMap);
                                                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast die 1. Betthälfte von §eTeam Gelb §7gesetzt!");
                                                } else if (args[4].equals("2")) {
                                                    MapLocationManager.setLocation(player.getLocation(), "BedGelb2", selectedMap);
                                                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast die 2. Betthälfte von §eTeam Gelb §7gesetzt!");
                                                }
                                            } else
                                                player.sendMessage(Messages.PREFIX.get() + "§cBitte wähle eine Map aus!");
                                            break;
                                    }
                                } else if (args[2].toLowerCase().equals("spawner")) {
                                    if (args[3].toLowerCase().equals("bronze")) {
                                        if (!selectedMap.equals("")) {
                                            switch (args[4].toLowerCase()) {
                                                case "rot":
                                                    MapLocationManager.setLocation(player.getLocation(), "BronzeSpawnerRot", selectedMap);
                                                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast den Bronze Spawner von §cTeam Rot §7gesetzt!");
                                                    break;
                                                case "blau":
                                                    MapLocationManager.setLocation(player.getLocation(), "BronzeSpawnerBlau", selectedMap);
                                                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast den Bronze Spawner von §9Team Blau §7gesetzt!");
                                                    break;
                                                case "gelb":
                                                    MapLocationManager.setLocation(player.getLocation(), "BronzeSpawnerGelb", selectedMap);
                                                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast den Bronze Spawner von §eTeam Gelb §7gesetzt!");
                                                    break;
                                                case "grün":
                                                    MapLocationManager.setLocation(player.getLocation(), "BronzeSpawnerGrün", selectedMap);
                                                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast den Bronze Spawner von §aTeam Grün §7gesetzt!");
                                                    break;
                                            }
                                        } else
                                            player.sendMessage(Messages.PREFIX.get() + "§cBitte wähle eine Map aus!");
                                    } else if (args[3].toLowerCase().equals("eisen")) {
                                        if (!selectedMap.equals("")) {
                                            switch (args[4].toLowerCase()) {
                                                case "rot":
                                                    MapLocationManager.setLocation(player.getLocation(), "IronSpawnerRot", selectedMap);
                                                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast den §fEisen §7Spawner von §cTeam Rot §7gesetzt!");
                                                    break;
                                                case "blau":
                                                    MapLocationManager.setLocation(player.getLocation(), "IronSpawnerBlau", selectedMap);
                                                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast den §fEisen §7Spawner von §9Team Blau §7gesetzt!");
                                                    break;
                                                case "gelb":
                                                    MapLocationManager.setLocation(player.getLocation(), "IronSpawnerGelb", selectedMap);
                                                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast den §fEisen §7Spawner von §eTeam Gelb §7gesetzt!");
                                                    break;
                                                case "grün":
                                                    MapLocationManager.setLocation(player.getLocation(), "IronSpawnerGrün", selectedMap);
                                                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast den §fEisen §7Spawner von §aTeam Grün §7gesetzt!");
                                                    break;
                                            }
                                        } else
                                            player.sendMessage(Messages.PREFIX.get() + "§cBitte wähle eine Map aus!");
                                    } else if (args[3].toLowerCase().equals("gold")) {
                                        if (!selectedMap.equals("")) {
                                            switch (args[4].toLowerCase()) {
                                                case "rot":
                                                    MapLocationManager.setLocation(player.getLocation(), "GoldSpawnerRot", selectedMap);
                                                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast den §6Gold §7Spawner von §cTeam Rot §7gesetzt!");
                                                    break;
                                                case "blau":
                                                    MapLocationManager.setLocation(player.getLocation(), "GoldSpawnerBlau", selectedMap);
                                                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast den §6Gold §7Spawner von §9Team Blau §7gesetzt!");
                                                    break;
                                                case "gelb":
                                                    MapLocationManager.setLocation(player.getLocation(), "GoldSpawnerGelb", selectedMap);
                                                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast den §6Gold §7Spawner von §eTeam Gelb §7gesetzt!");
                                                    break;
                                                case "grün":
                                                    MapLocationManager.setLocation(player.getLocation(), "GoldSpawnerGrün", selectedMap);
                                                    player.sendMessage(Messages.PREFIX.get() + "§7Du hast den §6Gold §7Spawner von §aTeam Grün §7gesetzt!");
                                                    break;
                                            }
                                        } else
                                            player.sendMessage(Messages.PREFIX.get() + "§cBitte wähle eine Map aus!");
                                    }
                                }
                            }
                        }
                        break;
                }
            } else
                player.sendMessage(Messages.NO_PERM.get());
        }
        return false;
    }
}
