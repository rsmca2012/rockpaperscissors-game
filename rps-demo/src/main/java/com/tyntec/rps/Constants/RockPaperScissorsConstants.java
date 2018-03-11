package com.tyntec.rps.Constants;


public class RockPaperScissorsConstants {

	public static final int MAXIMUM_ALLOWED_MOVES = 100;
	public static final int MAXIMUM_ALLOWED_PLAYERS = 2;
	
	public enum RESULT {
		WIN, LOSE, TIE
	}

	public enum PLAYERCHOICES {
		ROCK, PAPER, SCISSORS;
		private PLAYERCHOICES succeeds;

		static {
			ROCK.succeeds = SCISSORS;
			PAPER.succeeds = ROCK;
			SCISSORS.succeeds = PAPER;
		}

		public boolean succeeds(PLAYERCHOICES other) {
			return succeeds == other;
		}
	}
}
