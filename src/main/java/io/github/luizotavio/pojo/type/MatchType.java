package io.github.luizotavio.pojo.type;

import io.github.luizotavio.pojo.team.GameTeam;

public enum MatchType {
    SOLO(GameTeam.values().length, 1);

    private int size, teamSize;

    MatchType(int size, int teamSize) {
        this.size = size;
        this.teamSize = teamSize;
    }

    public int getSize() {
        return size;
    }

    public int getTeamSize() {
        return teamSize;
    }
}
