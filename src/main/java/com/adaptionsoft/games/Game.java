package com.adaptionsoft.games;

import com.adaptionsoft.games.contants.Constants;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
    ArrayList<Player> players = new ArrayList();
	// @Chamber todo: 以下四个属性待抽取：places，purses，inPenaltyBox，isGettingOutOfPenaltyBox
	// 对于要抽取出去的属性分为 set & get两种使用方式

	// @Chamber todo: 显然问题的种类是会拓展的，那么在拓展种类时，如何避免霰弹式修改
    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();

    int currentPlayer = 0;

	// @Chamber todo: del
	OldGame oldGame;
    public Game(){
		// @Chamber todo: del
		if (Boolean.FALSE.equals(Constants.refactorSwitch)) {
			oldGame = new OldGame();
			return;
		}

    	for (int i = 0; i < 50; i++) {
			popQuestions.addLast("Pop Question " + i);
			scienceQuestions.addLast(("Science Question " + i));
			sportsQuestions.addLast(("Sports Question " + i));
			rockQuestions.addLast(createRockQuestion(i));
    	}
    }

	private String createRockQuestion(int index){
		return "Rock Question " + index;
	}

	private boolean isPlayable() {
		return (howManyPlayers() >= 2);
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

	private int howManyPlayers() {
		return players.size();
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
			if (canGetOutOfPenaltyBox(roll)) {
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

	private static boolean canGetOutOfPenaltyBox(int roll) {
		return roll % 2 != 0;
	}

	private void movePlayerAndAskQuestion(int roll) {
		currentPlayer().moveForwardByRoll(roll);
		System.out.println("The category is " + currentCategory());
		askQuestion();
	}

	private void askQuestion() {
		if (currentCategory() == "Pop")
			System.out.println(popQuestions.removeFirst());
		if (currentCategory() == "Science")
			System.out.println(scienceQuestions.removeFirst());
		if (currentCategory() == "Sports")
			System.out.println(sportsQuestions.removeFirst());
		if (currentCategory() == "Rock")
			System.out.println(rockQuestions.removeFirst());
	}


	private String currentCategory() {
		if (getPosition() == 0) return "Pop";
		if (getPosition() == 4) return "Pop";
		if (getPosition() == 8) return "Pop";
		if (getPosition() == 1) return "Science";
		if (getPosition() == 5) return "Science";
		if (getPosition() == 9) return "Science";
		if (getPosition() == 2) return "Sports";
		if (getPosition() == 6) return "Sports";
		if (getPosition() == 10) return "Sports";
		return "Rock";
	}

	public boolean wasCorrectlyAnswered() {
		// @Chamber todo: del
		if (Boolean.FALSE.equals(Constants.refactorSwitch)) {
			return oldGame.wasCorrectlyAnswered();
		}

		if (getInPenaltyBox()){
			if (isGettingOutOfPenaltyBox()) {
				// @Chamber todo: 此处有bug，顺序是：1. 判断是否回答正确 2. 给予奖金 3. 移动至下位玩家
				nextPlayer();
				currentPlayer().correctAnswerV1();
				return currentPlayer().isWinner();
			} else {
				nextPlayer();
				return true;
			}
		} else {
			currentPlayer().correctAnswerV2();
			boolean winner = currentPlayer().isWinner();
			nextPlayer();
			return winner;
		}
	}


	private void nextPlayer() {
		currentPlayer++;
		if (currentPlayer == players.size()) currentPlayer = 0;
	}

	private Player currentPlayer() {
		return players.get(currentPlayer);
	}

	// @Chamber 错误回答
	public boolean wrongAnswer(){
		// @Chamber todo: del
		if (Boolean.FALSE.equals(Constants.refactorSwitch)) {
			return oldGame.wrongAnswer();
		}

		currentPlayer().wrongAnswer();
		nextPlayer();
		return true;
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
