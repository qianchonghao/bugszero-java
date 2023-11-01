package com.adaptionsoft.games;


public class Player {
    private String name;
    private Boolean inPenaltyBox = false;

    private Boolean isGettingOutOfPenaltyBox = false;

    private Integer position = 0;

    private Integer score = 0;
    public Player(String playerName) {
        this.name=playerName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getInPenaltyBox() {
        return inPenaltyBox;
    }

    public void setInPenaltyBox(Boolean inPenaltyBox) {
        this.inPenaltyBox = inPenaltyBox;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Boolean getGettingOutOfPenaltyBox() {
        return isGettingOutOfPenaltyBox;
    }

    public void setGettingOutOfPenaltyBox(Boolean gettingOutOfPenaltyBox) {
        isGettingOutOfPenaltyBox = gettingOutOfPenaltyBox;
    }
}
