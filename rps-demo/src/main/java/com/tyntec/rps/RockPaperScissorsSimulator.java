package com.tyntec.rps;

import com.tyntec.rps.Constants.RockPaperScissorsConstants;

public class RockPaperScissorsSimulator {

	public static void main(String[] args) throws Exception {
		Player playerA = new Player(true);
		playerA.setPlayerName("PLAYER A");
		playerA.setSelectedChoice(RockPaperScissorsConstants.PLAYERCHOICES.PAPER);

		Player playerB = new Player();
		playerB.setPlayerName("PLAYER B");

		Game game = new Game(playerA, playerB);
		game.setNumberOfPlayers(2);

		game.playRockPaperScissors();
		game.printGameResults();
	}
}
