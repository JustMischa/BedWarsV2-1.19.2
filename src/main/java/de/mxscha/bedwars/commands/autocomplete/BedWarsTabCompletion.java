package de.mxscha.bedwars.commands.autocomplete;

import de.mxscha.bedwars.BedwarsCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BedWarsTabCompletion implements TabCompleter {

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        switch (args.length) {
            case 1:
                List<String> commands = new ArrayList<>();
                commands.add("help");
                commands.add("add");
                commands.add("select");
                commands.add("unselect");
                commands.add("setup");
                return commands;
            case 2:
                List<String> arg0 = new ArrayList<>();
                if (args[0].toLowerCase().equals("setup")) {
                    arg0.add("map");
                    arg0.add("team");
                    arg0.add("spawners");
                    return arg0;
                } else {
                    if (!args[0].toLowerCase().equals("help")) {
                        arg0.add("map");
                        return arg0;
                    }
                }
                break;
            case 3:
                if (args[0].toLowerCase().equals("setup")) {
                    if (args[1].toLowerCase().equals("team")) {
                        List<String> arg1 = new ArrayList<>();
                        arg1.add("rot");
                        arg1.add("grün");
                        arg1.add("gelb");
                        arg1.add("blau");
                        return arg1;
                    }
                } else if (args[0].toLowerCase().equals("select") || args[0].toLowerCase().equals("unselect")) {
                    if (args[1].toLowerCase().equals("map")) {
                        List<String> arg1 = new ArrayList<>();
                        for (String maps : BedwarsCore.getInstance().getMapManager().getMaps()) {
                            arg1.add(maps);
                        }
                        return arg1;
                    }
                }
                break;
        }
        return null;
    }
}
