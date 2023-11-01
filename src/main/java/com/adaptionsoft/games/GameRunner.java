
package com.adaptionsoft.games;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.Random;


public class GameRunner {

	private static boolean notAWinner;

	public static void main(String[] args) {
		Random rand = new Random();
		playGame(rand);

	}

	public static void playGame(Random rand) {
		Game aGame = new Game();
		List<String> players = ImmutableList.of("Chet","Pat","Sue");
		doPlayGame(rand, players, aGame);
	}

	private static void doPlayGame(Random rand, List<String> players, Game aGame) {
		for(String player: players) {
			aGame.add(player);
		}

		do {
			aGame.roll(rollDice(rand));
			if (isWrongAnswer(rand)) {
				notAWinner = aGame.wrongAnswer();
			} else {
				notAWinner = aGame.correctAnswer();
			}
		} while (notAWinner);
	}

	private static int rollDice(Random rand) {
		return rand.nextInt(5) + 1;
	}

	private static boolean isWrongAnswer(Random rand) {
		return rand.nextInt(9) == 7;
	}
}
