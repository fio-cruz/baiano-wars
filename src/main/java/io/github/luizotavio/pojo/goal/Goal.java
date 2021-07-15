package io.github.luizotavio.pojo.goal;

import io.github.luizotavio.pojo.match.Match;

public abstract class Goal {

    private final int seconds;
    private final String name;

    public Goal(int seconds, String name) {
        this.seconds = seconds;
        this.name = name;
    }

    public int getSeconds() {
        return seconds;
    }

    public String getName() {
        return name;
    }

    public abstract void onExecute(Match match);
}
