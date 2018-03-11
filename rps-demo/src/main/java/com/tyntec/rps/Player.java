package com.tyntec.rps;

import java.util.Random;

import com.tyntec.rps.Constants.RockPaperScissorsConstants.PLAYERCHOICES;

public class Player {
	private int winCount;
	private int loseCount;
	private String playerName = "PLAYER " + new Random().nextInt(100);
	
	private boolean isPlayerWithPredefinedChoice = false;
	
	private PLAYERCHOICES selectedChoice;

	public PLAYERCHOICES getSelectedChoice() {
		return selectedChoice;
	}

	public void setSelectedChoice(PLAYERCHOICES selectedChoice) {
		this.selectedChoice = selectedChoice;
	}

	public boolean isPlayerWithPredefinedChoice() {
		return isPlayerWithPredefinedChoice;
	}

	public void setPlayerWithPredefinedChoice(boolean isPlayerWithPredefinedChoice) {
		this.isPlayerWithPredefinedChoice = isPlayerWithPredefinedChoice;
	}

	public Player() {
		winCount = 0;
		loseCount = 0;
	}
	public Player(boolean isPlayerWithPredefinedChoice) {
		winCount = 0;
		loseCount = 0;
		this.isPlayerWithPredefinedChoice = isPlayerWithPredefinedChoice;
	}

	public int getWinCount() {
		return winCount;
	}

	public void setWinCount(int winCount) {
		this.winCount = winCount;
	}

	public int getLoseCount() {
		return loseCount;
	}

	public void setLoseCount(int loseCount) {
		this.loseCount = loseCount;
	}

	public void incrementWinCount() {
		winCount++;
	}

	public void incrementLoseCount() {
		loseCount++;
	}
	
	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}


}
