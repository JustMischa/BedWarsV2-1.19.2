package de.mxscha.bedwars.utils.manager.maps;

import de.mxscha.bedwars.BedwarsCore;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class MapManager {

    private static final File file = new File("plugins/BedWars", "maps.yml");
    private static final FileConfiguration config = YamlConfiguration.loadConfiguration(file);

    public static void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createMap(String name) {
        config.set("Maps." + name + ".exists", true);
        save();
    }

    public void removeMap(String map) {
        config.set("Maps." + map + ".exists", false);
        save();
    }

    public ArrayList<String> getMaps() {
        ArrayList<String> maps = new ArrayList<>();
        if (config.getConfigurationSection("Maps") == null) return maps;
        for (String current : config.getConfigurationSection("Maps").getKeys(false)) {
            if (isMapExists(current))
                maps.add(current);
        }
        return maps;
    }

    public ArrayList<String> getPlayableMaps() {
        ArrayList<String> maps = new ArrayList<>();
        for (String current : config.getConfigurationSection("Maps").getKeys(false)) {
            if (isMapExists(current) && isMapPlayable(current))
                maps.add(current);
        }
        return maps;
    }

    public ArrayList<String> getThreeRandomMaps() {
        ArrayList<String> availableMaps = getMaps();
        ArrayList<String> chosenMaps = new ArrayList<>();
        for(int i = 0; i <= 3; i++) {
            String map = getRandomMap(availableMaps);
            chosenMaps.add(map);
            availableMaps.remove(map);
        }
        return chosenMaps;
    }

    public String getRandomPlayableMap() {
        return BedwarsCore.getInstance().getMapManager().getRandomMap(BedwarsCore.getInstance().getMapManager().getPlayableMaps());
    }

    public String getRandomMap(ArrayList<String> mapList) {
        String map = null;
        if (mapList.size() != 1) {
            for (int i = 0; i < mapList.size(); i++) {
                Collections.shuffle(mapList);
                map = mapList.get(i);
            }
        } else
            map = mapList.get(0);
        return map;
    }

    public boolean isMapPlayable(String map) {
        if (isMapExists(map)) {
            int mapDetails = 2;
            ConfigurationSection section = config.getConfigurationSection("Maps." + map);
            assert section != null;
            return section.getKeys(true).size() >= mapDetails;
        }
        return false;
    }

    public boolean isMapExists(String map) {
        return config.getBoolean("Maps." + map + ".exists");
    }

    public static FileConfiguration getConfig() {
        return config;
    }

    public static File getFile() {
        return file;
    }
}
