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

    public void moveForwardByRoll(int roll) {
        position = position + roll;
        if (getPosition() > 11) {
            position = getPosition() - 12;
        }

        System.out.println(name
                + "'s new location is "
                + getPosition());
    }

    public Boolean isWinner(){
        return !(score == 6);
    }

    public void wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(name + " was sent to the penalty box");
        inPenaltyBox = true;
    }

    public void correctAnswerV2() {
        // @Chamber todo : 单词错误 correct
        System.out.println("Answer was corrent!!!!");
        score++;
        System.out.println(name
                + " now has "
                + getScore()
                + " Gold Coins.");
    }

    public void correctAnswerV1() {
        System.out.println("Answer was correct!!!!");
        score++;
        System.out.println(name
                + " now has "
                + getScore()
                + " Gold Coins.");
    }
}
