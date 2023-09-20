package com.example.cive_field_cup;

public class ResulstModel {

    String game_played,gameResults,yellow,red,motm;

    public ResulstModel(){

    }

    public ResulstModel(String game_played, String gameResults, String yellow, String red, String motm) {
        this.game_played = game_played;
        this.gameResults = gameResults;
        this.yellow = yellow;
        this.red = red;
        this.motm = motm;
    }

    public String getGame_played() {
        return game_played;
    }

    public void setGame_played(String game_played) {
        this.game_played = game_played;
    }

    public String getGameResults() {
        return gameResults;
    }

    public void setGameResults(String gameResults) {
        this.gameResults = gameResults;
    }

    public String getYellow() {
        return yellow;
    }

    public void setYellow(String yellow) {
        this.yellow = yellow;
    }

    public String getRed() {
        return red;
    }

    public void setRed(String red) {
        this.red = red;
    }

    public String getMotm() {
        return motm;
    }

    public void setMotm(String motm) {
        this.motm = motm;
    }
}
