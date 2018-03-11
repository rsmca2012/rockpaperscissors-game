package com.tyntec.rps;

import static com.tyntec.rps.Constants.RockPaperScissorsConstants.MAXIMUM_ALLOWED_MOVES;
import static com.tyntec.rps.Constants.RockPaperScissorsConstants.MAXIMUM_ALLOWED_PLAYERS;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import com.tyntec.rps.Constants.RockPaperScissorsConstants.PLAYERCHOICES;
import com.tyntec.rps.Constants.RockPaperScissorsConstants.RESULT;

public class TestUnitGame {

	@Test
	public void testGame_DefaultConstructor_InitializesGameAndPlayers() {
		Game game = new Game();
		assertEquals("Allowed Number of Moves is RockPaperScissorsGame is not initialized properly ",
				MAXIMUM_ALLOWED_MOVES, game.getNumberOfGames());
		assertEquals("Allowed Number of Players is not initialized properly ", MAXIMUM_ALLOWED_PLAYERS,
				game.getNumberOfPlayers());

		assertNotNull("Player1 is not initialized properly", game.getPlayerA());
		assertNotNull("Player2 is not initialized properly", game.getPlayerB());

		assertNull(game.getPlayerA().getSelectedChoice());
		assertNull(game.getPlayerB().getSelectedChoice());

	}
	
	@Test
	public void testGame_VerifyParameterizedConstructorsCalled_WhenDefaultConstructorIsCalled() {
		Powermockito

	}

	@Test
	public void testGame_ParameterizedConstructor_WithNumberOfPlayersAndNumberOfGames() {
		Game game = new Game(2, 10);
		assertEquals("Number of Moves is RockPaperScissorsGame is not initialized properly ", 10,
				game.getNumberOfGames());
		assertEquals("Number of Players is not initialized properly ", 2, game.getNumberOfPlayers());

		assertNotNull("Player1 is not initialized properly", game.getPlayerA());
		assertNotNull("Player2 is not initialized properly", game.getPlayerB());

		assertNull(game.getPlayerA().getSelectedChoice());
		assertNull(game.getPlayerB().getSelectedChoice());

	}

	@Test
	public void testGame_ParameterizedConstructor_WithPlayers() {
		Game game = new Game(new Player(), new Player());
		assertEquals("Number of Moves is RockPaperScissorsGame is not initialized properly ", MAXIMUM_ALLOWED_MOVES,
				game.getNumberOfGames());
		assertEquals("Number of Players is not initialized properly ", MAXIMUM_ALLOWED_PLAYERS,
				game.getNumberOfPlayers());

		assertNotNull("Player1 is not initialized properly", game.getPlayerA());
		assertNotNull("Player2 is not initialized properly", game.getPlayerB());
		assertEquals(0, game.getTieCount());

		assertNull(game.getPlayerA().getSelectedChoice());
		assertNull(game.getPlayerB().getSelectedChoice());

	}

	@Test
	public void testValidateGameConditions() {
		validateGameConditionsTest(MAXIMUM_ALLOWED_PLAYERS, MAXIMUM_ALLOWED_MOVES, false);
		validateGameConditionsTest(3, MAXIMUM_ALLOWED_MOVES, true);
		validateGameConditionsTest(0, MAXIMUM_ALLOWED_MOVES + 1, true);
		validateGameConditionsTest(0, MAXIMUM_ALLOWED_MOVES, true);
		validateGameConditionsTest(2, 0, true);
		validateGameConditionsTest(0, 0, true);
		validateGameConditionsTest(0, 0, true);
	}

	@Test
	public void testValidatePlayerData() {
		Game game = new Game();
		game.setPlayerA(null);
		game.setPlayerB(new Player());

		validatePlayerDataTest(game, true);

		game.setPlayerA(new Player());
		game.setPlayerB(null);

		validatePlayerDataTest(game, true);

		game = new Game();
		validatePlayerDataTest(game, false);

	}

	@Test
	public void testGetPlayerChoice() {
		Game game = new Game(new Player(), new Player());
		game.getPlayerA().setSelectedChoice(PLAYERCHOICES.PAPER);
		game.getPlayerB().setSelectedChoice(null);

		assertSame(PLAYERCHOICES.PAPER, game.getPlayerChoice(game.getPlayerA()));
		assertNotNull("Selected Choice must be initialized properly with randomized choice",
				game.getPlayerChoice(game.getPlayerB()));

		game.getPlayerA().setSelectedChoice(null);
		game.getPlayerB().setSelectedChoice(null);
		assertNotNull("Selected Choice for Player 1 must be initialized properly with randomized choice",
				game.getPlayerChoice(game.getPlayerA()));
		assertNotNull("Selected Choice for Player 2 must be initialized properly with randomized choice",
				game.getPlayerChoice(game.getPlayerB()));

	}
	
	@Test
	public void testManipulateGameResults() {
		
		manipulateGameResultsTest(RESULT.TIE, PLAYERCHOICES.PAPER, PLAYERCHOICES.PAPER);
		manipulateGameResultsTest(RESULT.TIE, PLAYERCHOICES.ROCK, PLAYERCHOICES.ROCK);
		manipulateGameResultsTest(RESULT.TIE, PLAYERCHOICES.SCISSORS, PLAYERCHOICES.SCISSORS);
		
		manipulateGameResultsTest(RESULT.WIN, PLAYERCHOICES.SCISSORS, PLAYERCHOICES.PAPER);
		manipulateGameResultsTest(RESULT.WIN, PLAYERCHOICES.PAPER, PLAYERCHOICES.ROCK);
		manipulateGameResultsTest(RESULT.WIN, PLAYERCHOICES.ROCK, PLAYERCHOICES.SCISSORS);
		
		manipulateGameResultsTest(RESULT.LOSE, PLAYERCHOICES.PAPER, PLAYERCHOICES.SCISSORS);
		manipulateGameResultsTest(RESULT.LOSE, PLAYERCHOICES.ROCK, PLAYERCHOICES.PAPER);
		manipulateGameResultsTest(RESULT.LOSE, PLAYERCHOICES.SCISSORS, PLAYERCHOICES.ROCK);

	}
	
	private void manipulateGameResultsTest(RESULT expectedResult, PLAYERCHOICES player1Choice, PLAYERCHOICES player2Choice) {
		Game game = new Game();
		game.manipulateGameResults(player1Choice, player2Choice);
		
		if (expectedResult == RESULT.WIN) {
			assertEquals(1, game.getPlayerA().getWinCount());
			assertEquals(0, game.getPlayerB().getWinCount());
			assertEquals(0, game.getTieCount());
		} else if(expectedResult == RESULT.LOSE) {
			assertEquals(0, game.getPlayerA().getWinCount());
			assertEquals(1, game.getPlayerB().getWinCount());
			assertEquals(0, game.getTieCount());
		} else {
			assertEquals(0, game.getPlayerA().getWinCount());
			assertEquals(0, game.getPlayerB().getWinCount());
			assertEquals(1, game.getTieCount());	
		}
		
	}

	private void validatePlayerDataTest(Game game, boolean isIllegalArgumentExceptionExpected) {
		try {
			game.validatePlayerData();
			if (isIllegalArgumentExceptionExpected) {
				fail("IllegalArgument Exception Expected");
			}
		} catch (IllegalArgumentException ex) {
			if (!isIllegalArgumentExceptionExpected) {
				fail("IllegalArgument Exception Not Expected");
			}
		}
	}

	private void validateGameConditionsTest(int numberOfPlayers, int numberOfGames,
			boolean isIllegalArgumentExceptionExpected) {
		try {
			Game game = new Game();
			game.validateGameConditions(numberOfPlayers, numberOfGames);
			if (isIllegalArgumentExceptionExpected) {
				fail("Illegal Argument Exception expected");
			}
		} catch (IllegalArgumentException ex) {
			if (!isIllegalArgumentExceptionExpected) {
				fail("Illegal Argument Exception not expected");
			}
		}

	}

}
