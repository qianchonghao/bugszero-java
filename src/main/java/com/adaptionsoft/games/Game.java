package com.adaptionsoft.games;

import com.adaptionsoft.games.contants.Constants;

import java.util.ArrayList;

public class Game {
    ArrayList<Player> players = new ArrayList();
	// @Chamber todo: 以下四个属性待抽取：places，purses，inPenaltyBox，isGettingOutOfPenaltyBox
	// 对于要抽取出去的属性分为 set & get两种使用方式

	// @Chamber todo: 显然问题的种类是会拓展的，那么在拓展种类时，如何避免霰弹式修改
//    LinkedList popQuestions = new LinkedList();
//    LinkedList scienceQuestions = new LinkedList();
//    LinkedList sportsQuestions = new LinkedList();
//    LinkedList rockQuestions = new LinkedList();

	QuestionPool questionPool;
    int currentPlayer = 0;

	// @Chamber todo: del
	OldGame oldGame;
    public Game(){
		// @Chamber todo: del
		if (Boolean.FALSE.equals(Constants.refactorSwitch)) {
			oldGame = new OldGame();
			return;
		}
		questionPool = new QuestionPool();
	}

	private boolean isPlayable() {
		return (players.size() >= 2);
	}

	public boolean add(String playerName) {
		// @Chamber todo: del
		if (Boolean.FALSE.equals(Constants.refactorSwitch)) {
			return oldGame.add(playerName);
		}

	    players.add(new Player(playerName));
	    System.out.println(playerName + " was added");
	    System.out.println("They are player number " + players.size());
		return true;
	}

	public Boolean roll(int roll) {
		// @Chamber todo: del


		System.out.println(getPlayerName() + " is the current player");
		System.out.println("They have rolled a " + roll);

		if (getInPenaltyBox()) {
			if (roll % 2 != 0) {
				currentPlayer().outOfPenalty();
				movePlayerAndAskQuestion(roll);
				return true;
			} else {
				currentPlayer().stillInPenalty();
				return false;
			}
		} else {
			movePlayerAndAskQuestion(roll);
			return true;
		}
	}

	private void movePlayerAndAskQuestion(int roll) {
		currentPlayer().moveForwardByRoll(roll);
		questionPool.askQuestion(currentPlayer().getPosition());
	}

	public boolean correctAnswer() {
		// @Chamber todo: del
		if (Boolean.FALSE.equals(Constants.refactorSwitch)) {
			return oldGame.wasCorrectlyAnswered();
		}

		Boolean notWinner = true;
		if (!getInPenaltyBox()){
			notWinner = doAnswerCorrect();
		}
		return notWinner;
	}

	private boolean doAnswerCorrect() {
		currentPlayer().correctAnswer();
		return  currentPlayer().notWinner();
	}

	public boolean wrongAnswer(){
		// @Chamber todo: del
		if (Boolean.FALSE.equals(Constants.refactorSwitch)) {
			return oldGame.wrongAnswer();
		}

		currentPlayer().wrongAnswer();
		return true;
	}

	void nextPlayer() {
		currentPlayer++;
		if (currentPlayer == players.size()) currentPlayer = 0;
	}

	private Player currentPlayer() {
		return players.get(currentPlayer);
	}

	private String getPlayerName() {
		return currentPlayer().getName();
	}

	private boolean getInPenaltyBox() {
		return currentPlayer().getInPenaltyBox();
	}

}
