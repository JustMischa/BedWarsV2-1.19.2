package de.mxscha.bedwars.utils.game.extra;

import de.mxscha.bedwars.BedwarsCore;
import de.mxscha.bedwars.utils.manager.maps.MapLocationManager;
import de.mxscha.bedwars.utils.manager.maps.MapManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Bed;

import java.util.HashSet;
import java.util.Set;

public class Beds {

    public static void setBeds(String map) {
        if (BedwarsCore.getInstance().getMapManager().isMapExists(map)) {
            Location BedRed1 = MapLocationManager.getLocation("BedRot1", map, false);
            Location BedRed2 = MapLocationManager.getLocation("BedRot2", map, false);
            Location BedBlue1 = MapLocationManager.getLocation("BedBlau1", map, false);
            Location BedBlue2 = MapLocationManager.getLocation("BedBlau2", map, false);
            Location BedYellow1 = MapLocationManager.getLocation("BedGelb1", map, false);
            Location BedYellow2 = MapLocationManager.getLocation("BedGelb2", map, false);
            Location BedGreen1 = MapLocationManager.getLocation("BedGrün1", map, false);
            Location BedGreen2 = MapLocationManager.getLocation("BedGrün2", map, false);

            if (Stats.isAliveBedRed()) {
                setBed(BedRed2.getBlock(), BedRed1.getBlock().getFace(BedRed2.getBlock()), Material.RED_BED);
            }
            if (Stats.isAliveBedBlue()) {
                setBed(BedBlue2.getBlock(), BedBlue1.getBlock().getFace(BedBlue2.getBlock()), Material.BLUE_BED);
            }
            if (Stats.isAliveBedYellow()) {
                setBed(BedYellow2.getBlock(), BedYellow1.getBlock().getFace(BedYellow2.getBlock()), Material.YELLOW_BED);
            }
            if (Stats.isAliveBedGreen()) {
                setBed(BedGreen2.getBlock(), BedGreen1.getBlock().getFace(BedGreen2.getBlock()), Material.LIME_BED);
            }
        }
    }

    private static void setBed(Block start, BlockFace facing, Material material) {
        Set<Block> bedBlocks = new HashSet<>();
        for (Bed.Part part : Bed.Part.values()) {
            bedBlocks.add(start);
            start.setBlockData(Bukkit.createBlockData(material, data -> {
                ((Bed) data).setPart(part);
                ((Bed) data).setFacing(facing);
            }));
            start = start.getRelative(facing.getOppositeFace());
        }
    }

}
