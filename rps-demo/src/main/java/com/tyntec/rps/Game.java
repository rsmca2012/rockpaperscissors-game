package com.tyntec.rps;

import static com.tyntec.rps.Constants.RockPaperScissorsConstants.MAXIMUM_ALLOWED_MOVES;
import static com.tyntec.rps.Constants.RockPaperScissorsConstants.MAXIMUM_ALLOWED_PLAYERS;
import static com.tyntec.rps.Constants.RockPaperScissorsConstants.RESULT.LOSE;
import static com.tyntec.rps.Constants.RockPaperScissorsConstants.RESULT.TIE;
import static com.tyntec.rps.Constants.RockPaperScissorsConstants.RESULT.WIN;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.lang.UnsupportedOperationException;

import com.tyntec.rps.Constants.RockPaperScissorsConstants.PLAYERCHOICES;
import com.tyntec.rps.Constants.RockPaperScissorsConstants.RESULT;

public class Game  {

	private int numberOfGames;
	private int numberOfPlayers;
	private int tieCount;
	private Player playerA;
	private Player playerB;
	
	public int getTieCount() {
		return tieCount;
	}

	public void setTieCount(int tieCount) {
		this.tieCount = tieCount;
	}


	public Player getPlayerA() {
		return playerA;
	}

	public void setPlayerA(Player playerA) {
		this.playerA = playerA;
	}

	public Player getPlayerB() {
		return playerB;
	}

	public void setPlayerB(Player playerB) {
		this.playerB = playerB;
	}

	public Game() {
		this(MAXIMUM_ALLOWED_PLAYERS, MAXIMUM_ALLOWED_MOVES);
	}

	public Game(int numberOfPlayers, int numberOfGames) throws IllegalArgumentException {
		this((Player) null, (Player) null);
		this.numberOfPlayers = numberOfPlayers;
		this.numberOfGames = numberOfGames;
	}

	protected void validateGameConditions(int numberOfPlayers, int numberOfGames) throws IllegalArgumentException {
		if (numberOfPlayers > MAXIMUM_ALLOWED_PLAYERS || numberOfPlayers <= 0)
			throw new IllegalArgumentException("Exactly Two players are required to play this game");

		if (numberOfGames > MAXIMUM_ALLOWED_MOVES || numberOfGames <= 0)
			throw new IllegalArgumentException("Game cannot be played as allowed moves are not fitting to the game conditions");

	}
	
	void ensureThatPlayerChoicesAreCorrectlySet(PLAYERCHOICES playerChoiceA, PLAYERCHOICES playerChoiceB) throws UnsupportedOperationException{
		List<PLAYERCHOICES> choices = Arrays.asList(PLAYERCHOICES.values());

		 if (playerChoiceA == null || playerChoiceB == null || (playerChoiceA!=null && !choices.contains(playerChoiceA)) || (playerChoiceB!=null && !choices.contains(playerChoiceB)) ) {
			 throw new UnsupportedOperationException(String.format("Player Choices are not Set correctly . Player 1 Choice %s Player 2 Choice %s", playerChoiceA, playerChoiceB));
		 }
	}

	void validatePlayerData() {
		if (isNullOrEmpty(playerA) || isNullOrEmpty(playerB))
			throw new IllegalArgumentException("Players are not set correctly for the game");

	}

	public Game(Player playerA, Player playerB) {
		tieCount = 0;
		this.numberOfPlayers = this.numberOfPlayers > 0 ? this.numberOfPlayers : MAXIMUM_ALLOWED_PLAYERS;
		this.numberOfGames = this.numberOfGames > 0 ? this.numberOfGames : MAXIMUM_ALLOWED_MOVES;
		validateGameConditions(numberOfPlayers, numberOfGames);
		initPlayers(playerA, playerB);
	}

	public int getNumberOfGames() {
		return numberOfGames;
	}

	public void setNumberOfGames(int numberOfGames) {
		this.numberOfGames = numberOfGames;
	}

	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	protected void initPlayers(Player playerA, Player playerB) {
		this.playerA = isNullOrEmpty(playerA) ? playerA = new Player() : playerA;
		this.playerB = isNullOrEmpty(playerB) ? playerB = new Player() : playerB;
	}

	boolean isNullOrEmpty(Object o) {
		return (o == null || (o != null && o instanceof String && o.toString().length() == 0)) ? true : false;
	}

	protected PLAYERCHOICES getRandomizedChoice() {
		return PLAYERCHOICES.values()[new Random().nextInt(PLAYERCHOICES.values().length)];
	}

	public void playRockPaperScissors() throws UnsupportedOperationException {

		for (int i = 0; i < numberOfGames; i++) {
			manipulateGameResults(getPlayerChoice(playerA), getPlayerChoice(playerB));

		}
	}

	PLAYERCHOICES getPlayerChoice(Player player) {
		return !isNullOrEmpty(player.getSelectedChoice()) ? player.getSelectedChoice() : getRandomizedChoice();
	}

	void manipulateGameResults(PLAYERCHOICES choicePlayerA, PLAYERCHOICES choicePlayerB) throws UnsupportedOperationException {
		ensureThatPlayerChoicesAreCorrectlySet(choicePlayerA, choicePlayerB);
		RESULT result = (choicePlayerA == choicePlayerB) ? TIE : (choicePlayerA.succeeds(choicePlayerB) ? WIN : LOSE);

		setTieCount(result == TIE ? tieCount + 1 : tieCount);
		playerA.setWinCount(result == WIN ? playerA.getWinCount() + 1 : playerA.getWinCount());
		playerB.setWinCount(result == LOSE ? playerB.getWinCount() + 1 : playerB.getWinCount());

	}

	void printGameResults() {
		System.out.println(playerA.getPlayerName() + " wins " + playerA.getWinCount() + " of " + numberOfGames + " GAMES");
		System.out.println(playerB.getPlayerName() + " wins " + playerB.getWinCount() + " of " + numberOfGames + " GAMES");
		System.out.println("Tie " + tieCount + " of " + numberOfGames + " GAMES");
	}

}
