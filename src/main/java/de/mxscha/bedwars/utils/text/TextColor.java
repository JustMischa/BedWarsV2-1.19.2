package de.mxscha.bedwars.utils.text;

import net.md_5.bungee.api.ChatColor;

public enum TextColor {

    TEST_COLOR(ChatColor.of("")),
    PREFIX(ChatColor.of("#B40099"));

    private ChatColor color;

    TextColor(ChatColor color) {
        this.color = color;
    }

    public ChatColor get() {
        return color;
    }
}
