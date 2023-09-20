package com.example.cive_field_cup;

public class FixturesModel {
//    String match_played,date,ground,day,time;
    String  date,day,time,match_played,ground;

    public FixturesModel(){}

    public FixturesModel(String date, String day, String time, String match_played, String ground) {
        this.date = date;
        this.day = day;
        this.time = time;
        this.match_played = match_played;
        this.ground = ground;
    }

    public String getMatch_played() {
        return match_played;
    }

    public void setMatch_played(String match_played) {
        this.match_played = match_played;
    }

    public String getGround() {
        return ground;
    }

    public void setGround(String ground) {
        this.ground = ground;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
