package com.example.cive_field_cup;

public class TeamModel {
    String team="team",coach="coach";
    int id, points,position,goals;

    public TeamModel(){

    }

    public TeamModel(int id,String team, String coach, int points, int position, int goals) {
        this.id = id;
        this.team = team;
        this.coach = coach;
        this.points = points;
        this.position = position;
        this.goals = goals;

    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
