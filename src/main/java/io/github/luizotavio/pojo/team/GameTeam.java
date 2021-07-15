package io.github.luizotavio.pojo.team;

import org.bukkit.ChatColor;

public enum GameTeam {
    SALVADOR(ChatColor.GOLD),
    SANTANA(ChatColor.AQUA),
    VITORIA(ChatColor.DARK_GREEN),
    BARREIRAS(ChatColor.YELLOW),
    ITABUNA(ChatColor.GREEN),
    PETROLINA(ChatColor.WHITE);

    private final ChatColor chatColor;

    GameTeam(ChatColor chatColor) {
        this.chatColor = chatColor;
    }

    public ChatColor getChatColor() {
        return chatColor;
    }
}
