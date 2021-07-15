package io.github.luizotavio.pojo.statistic;

import org.bukkit.ChatColor;

public enum StatisticType {
    TAPIOCA(ChatColor.WHITE),
    ACARAJE(ChatColor.GOLD),
    PIRAO(ChatColor.YELLOW);

    private final ChatColor chatColor;

    StatisticType(ChatColor chatColor) {
        this.chatColor = chatColor;
    }

    public ChatColor getChatColor() {
        return chatColor;
    }
}
