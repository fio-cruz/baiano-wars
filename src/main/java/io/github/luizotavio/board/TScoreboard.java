package io.github.luizotavio.board;

import io.github.luizotavio.board.row.Row;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.*;

public abstract class TScoreboard {

    protected Scoreboard scoreboard;
    protected Objective mainObjective;

    public TScoreboard(String objective, String title) {
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

        mainObjective = scoreboard.registerNewObjective(objective, "dummy");

        mainObjective.setDisplaySlot(DisplaySlot.SIDEBAR);
        mainObjective.setDisplayName(title);
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public Objective getMainObjective() {
        return mainObjective;
    }

    public abstract void update();

    public void setRow(int index, String parse) {
        setRow(Row.byId(index), parse);
    }

    public void setRow(Row row, String parse) {
        if (parse.length() <= 16)
            setRow(row, parse, "");
        else if (parse.length() <= 32) {
            String prefix = parse.substring(0, 16);
            String suffix = parse.substring(16);

            if (prefix.endsWith("§")) {
                prefix = prefix.substring(0, 15);
                suffix = "§" + suffix;
            }

            suffix = ChatColor.getLastColors(prefix) + suffix;

            setRow(row, prefix, suffix);
        } else {
            throw new ArrayIndexOutOfBoundsException(parse.length() + " it's bigger than 32");
        }
    }

    public void setRow(int index, String prefix, String suffix) {
        setRow(Row.byId(index), prefix, suffix);
    }

    public void setRow(Row row, String prefix, String suffix) {
        String entry = row.getEntry();

        Team t = scoreboard.getTeam(entry);
        if (t == null) {
            t = scoreboard.registerNewTeam(entry);
        }
        if (!t.getPrefix().equals(prefix))
            t.setPrefix(prefix);

        if (!t.getSuffix().equals(suffix))
            t.setSuffix(suffix);

        if (!t.hasEntry(entry))
            t.addEntry(entry);

        Score score = mainObjective.getScore(entry);

        if (score.getScore() != row.getScore())
            score.setScore(row.getScore());
    }

    public void clearLine(int index) {
        Row row = Row.byId(index);
        scoreboard.resetScores(row.getEntry());

        Team t = scoreboard.getTeam(row.getEntry());
        if (t != null)
            t.unregister();
    }

    public void clearLines() {


        for (Row row : Row.values()) {
            scoreboard.resetScores(row.getEntry());

            Team t = scoreboard.getTeam(row.getEntry());
            if (t != null) {
                t.unregister();
            }
        }
    }

}