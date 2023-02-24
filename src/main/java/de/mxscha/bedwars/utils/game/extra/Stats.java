package de.mxscha.bedwars.utils.game.extra;

public class Stats {

    private static int killsRed = 0;
    private static int killsBlue = 0;
    private static int killsYellow = 0;
    private static int killsGreen = 0;
    private static boolean AliveBedRed = true;
    private static boolean AliveBedBlue = true;
    private static boolean AliveBedYellow = true;
    private static boolean AliveBedGreen = true;

    public static void addKill(String team) {
        switch (team.toLowerCase()) {
            case "rot" -> killsRed++;
            case "blau" -> killsBlue++;
            case "gelb" -> killsYellow++;
            case "grün" -> killsGreen++;
        }
    }

    public static void setAliveBedYellow(boolean aliveBedYellow) {
        AliveBedYellow = aliveBedYellow;
    }

    public static void setAliveBedRed(boolean aliveBedRed) {
        AliveBedRed = aliveBedRed;
    }

    public static void setAliveBedGreen(boolean aliveBedGreen) {
        AliveBedGreen = aliveBedGreen;
    }

    public static void setAliveBedBlue(boolean aliveBedBlue) {
        AliveBedBlue = aliveBedBlue;
    }

    public static boolean isAliveBedBlue() {
        return AliveBedBlue;
    }

    public static boolean isAliveBedGreen() {
        return AliveBedGreen;
    }

    public static boolean isAliveBedRed() {
        return AliveBedRed;
    }

    public static boolean isAliveBedYellow() {
        return AliveBedYellow;
    }

    public static int getKillsBlue() {
        return killsBlue;
    }

    public static int getKillsGreen() {
        return killsGreen;
    }

    public static int getKillsRed() {
        return killsRed;
    }

    public static int getKillsYellow() {
        return killsYellow;
    }

}
