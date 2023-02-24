package de.mxscha.bedwars.utils.manager.build;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class BuildManager {

    private static final ArrayList<Player> building = new ArrayList<>();

    public static boolean canBuild(Player player) {
        return building.contains(player);
    }

    public static void allowBuild(Player player) {
        building.add(player);
    }

    public static void disallowBuild(Player player) {
        building.remove(player);
    }
}
