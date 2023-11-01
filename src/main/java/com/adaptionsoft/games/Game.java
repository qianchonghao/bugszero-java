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

	public void roll(int roll) {
		// @Chamber todo: del
		if (Boolean.FALSE.equals(Constants.refactorSwitch)) {
			oldGame.roll(roll);
			return;
		}

		System.out.println(getPlayerName() + " is the current player");
		System.out.println("They have rolled a " + roll);

		if (getInPenaltyBox()) {
			if (roll % 2 != 0) {
				// @Chamber todo: isGettingOutOfPenaltyBox = canGetOutOfPenaltyBox(roll)
				setGettingOutOfPenaltyBox(true);
				System.out.println(getPlayerName() + " is getting out of the penalty box");
				movePlayerAndAskQuestion(roll);
			} else {
				System.out.println(getPlayerName() + " is not getting out of the penalty box");
				setGettingOutOfPenaltyBox(false);
			}
		} else {
			movePlayerAndAskQuestion(roll);
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
		if (!getInPenaltyBox() || isGettingOutOfPenaltyBox()){
			notWinner = doAnswerCorrect();
		}
		nextPlayer();
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
		nextPlayer();
		return true;
	}

	private void nextPlayer() {
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

	// @Chamber todo: extract PositionManager, then del
	private Integer getPosition(){
		return currentPlayer().getPosition();
	}

	private boolean isGettingOutOfPenaltyBox() {
		return currentPlayer().getGettingOutOfPenaltyBox();
	}

	private void setGettingOutOfPenaltyBox(Boolean value) {
		currentPlayer().setGettingOutOfPenaltyBox(value);
	}
}
