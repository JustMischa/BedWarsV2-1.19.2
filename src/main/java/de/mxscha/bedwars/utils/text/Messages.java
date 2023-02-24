package de.mxscha.bedwars.utils.text;

import org.bukkit.ChatColor;

public enum Messages {

    PREFIX("&2&lBedWars &8| &f"),
    NO_PERM(PREFIX.get() + "&cDazu hast du keine Rechte!"),
    PLS_USE(PREFIX.get() + "&cBitte benutze&8: &f"), 
    TEAM_IS_FULL(PREFIX.get() + "&cDieses Team ist bereits Voll!"),
    IS_NOW_IN_TEAM_RED(PREFIX.get() + "&7Du bist nun in &c&lTeam Rot&7!"),
    IS_NOW_IN_TEAM_BLUE(PREFIX.get() + "&7Du bist nun in &9&lTeam Blau&7!"),
    IS_NOW_IN_TEAM_YELLOW(PREFIX.get() + "&7Du bist nun in &e&lTeam Gelb&7!"),
    IS_NOW_IN_TEAM_GREEN(PREFIX.get() + "&7Du bist nun in &a&lTeam Grün&7!"),
    IS_ALREADY_IN_TEAM(PREFIX.get() + "&cDu bist bereits in diesem Team!");

    private final String message;
    Messages(String message) {
        this.message = message;
    }

    public String get() {
        return ChatColor.translateAlternateColorCodes('&', this.message);
    }
}