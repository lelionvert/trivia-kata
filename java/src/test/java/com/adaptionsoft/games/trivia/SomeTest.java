package com.adaptionsoft.games.trivia;

import static org.junit.Assert.*;

import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class SomeTest {

	// notAWinner is true == le premier rand.nextInt = 3  ,  rand.nextInt = 7

	@Test
	public void testThatTheGameReturnSameResultWhenRollFour() {
		PrintStream originalOut = System.out;
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		String expectedOut = "Chet was added\r\n" +
				"They are player number 1\r\n" +
				"Pat was added\r\n" +
				"They are player number 2\r\n" +
				"Chet is the current player\r\n" +
				"They have rolled a 4\r\n" +
				"Chet's new location is 4\r\n" +
				"The category is Pop\r\n" +
				"Pop Question 0\r\n" +
				"Question was incorrectly answered\r\n" +
				"Chet was sent to the penalty box\r\n";


		Game aGame = new Game();
		aGame.add("Chet");
		aGame.add("Pat");


		aGame.roll(4);
		boolean notAWinner = aGame.wrongAnswer();

		assertTrue(notAWinner);
		assertEquals(expectedOut, outContent.toString());
		System.setOut(originalOut);
	}

	@Test
	public void testThatTheGameReturnsTheSameResultWhenHavingThreePlayersAndRollingOne() {
		int randomRoll = 1;
		PrintStream originalOut = System.out;
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		String expectedOut = "Chet was added\r\n" +
				"They are player number 1\r\n" +
				"Pat was added\r\n" +
				"They are player number 2\r\n" +
				"Sue was added\r\n" +
				"They are player number 3\r\n" +
				"Chet is the current player\r\n" +
				"They have rolled a 1\r\n" +
				"Chet's new location is 1\r\n" +
				"The category is Science\r\n" +
				"Science Question 0\r\n" +
				"Question was incorrectly answered\r\n" +
				"Chet was sent to the penalty box\r\n";

		Game aGame = new Game();
		aGame.add("Chet");
		aGame.add("Pat");
		aGame.add("Sue");


		aGame.roll(randomRoll);

		boolean notAWinner = aGame.wrongAnswer();

		assertTrue(notAWinner);
		assertEquals(expectedOut, outContent.toString());
		System.setOut(originalOut);
	}

	@Test
	public void testThatTheGameReturnsTheSameResultWhenHavingThreePlayersAndRollingTwo() {
		int randomRoll = 2;
		PrintStream originalOut = System.out;
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		String expectedOut = "Chet was added\r\n" +
				"They are player number 1\r\n" +
				"Pat was added\r\n" +
				"They are player number 2\r\n" +
				"Sue was added\r\n" +
				"They are player number 3\r\n" +
				"Chet is the current player\r\n" +
				"They have rolled a 2\r\n" +
				"Chet's new location is 2\r\n" +
				"The category is Sports\r\n" +
				"Sports Question 0\r\n" +
				"Question was incorrectly answered\r\n" +
				"Chet was sent to the penalty box\r\n";

		Game aGame = new Game();
		aGame.add("Chet");
		aGame.add("Pat");
		aGame.add("Sue");


		aGame.roll(randomRoll);

		boolean notAWinner = aGame.wrongAnswer();

		assertTrue(notAWinner);
		assertEquals(expectedOut, outContent.toString());
		System.setOut(originalOut);
	}

	@Test
	public void testThatTheGameReturnsTheSameResultWhenHavingThreePlayersAndRollingThree() {
		int randomRoll = 3;
		PrintStream originalOut = System.out;
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		String expectedOut = "Chet was added\r\n" +
				"They are player number 1\r\n" +
				"Pat was added\r\n" +
				"They are player number 2\r\n" +
				"Sue was added\r\n" +
				"They are player number 3\r\n" +
				"Chet is the current player\r\n" +
				"They have rolled a 3\r\n" +
				"Chet's new location is 3\r\n" +
				"The category is Rock\r\n" +
				"Rock Question 0\r\n" +
				"Question was incorrectly answered\r\n" +
				"Chet was sent to the penalty box\r\n";

		Game aGame = new Game();
		aGame.add("Chet");
		aGame.add("Pat");
		aGame.add("Sue");


		aGame.roll(randomRoll);

		boolean notAWinner = aGame.wrongAnswer();

		assertTrue(notAWinner);
		assertEquals(expectedOut, outContent.toString());
		System.setOut(originalOut);
	}

	@Test
	public void testThatTheGameReturnsTheSameResultWhenHavingThreePlayersAndRollingFour() {
		int randomRoll = 4;
		PrintStream originalOut = System.out;
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		String expectedOut = "Chet was added\r\n" +
				"They are player number 1\r\n" +
				"Pat was added\r\n" +
				"They are player number 2\r\n" +
				"Sue was added\r\n" +
				"They are player number 3\r\n" +
				"Chet is the current player\r\n" +
				"They have rolled a 4\r\n" +
				"Chet's new location is 4\r\n" +
				"The category is Pop\r\n" +
				"Pop Question 0\r\n" +
				"Question was incorrectly answered\r\n" +
				"Chet was sent to the penalty box\r\n";

		Game aGame = new Game();
		aGame.add("Chet");
		aGame.add("Pat");
		aGame.add("Sue");


		aGame.roll(randomRoll);

		boolean notAWinner = aGame.wrongAnswer();

		assertTrue(notAWinner);
		assertEquals(expectedOut, outContent.toString());
		System.setOut(originalOut);
	}

	@Test
	public void testThatTheGameReturnsTheSameResultWhenHavingThreePlayersAndRollingFive() {
		int randomRoll = 5;
		PrintStream originalOut = System.out;
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		String expectedOut = "Chet was added\r\n" +
				"They are player number 1\r\n" +
				"Pat was added\r\n" +
				"They are player number 2\r\n" +
				"Sue was added\r\n" +
				"They are player number 3\r\n" +
				"Chet is the current player\r\n" +
				"They have rolled a 5\r\n" +
				"Chet's new location is 5\r\n" +
				"The category is Science\r\n" +
				"Science Question 0\r\n" +
				"Question was incorrectly answered\r\n" +
				"Chet was sent to the penalty box\r\n";

		Game aGame = new Game();
		aGame.add("Chet");
		aGame.add("Pat");
		aGame.add("Sue");


		aGame.roll(randomRoll);

		boolean notAWinner = aGame.wrongAnswer();

		assertTrue(notAWinner);
		assertEquals(expectedOut, outContent.toString());
		System.setOut(originalOut);
	}

	@Test
	public void testThatTheGameReturnsTheSameResultWhenHavingThreePlayersAndRollingSix() {
		int randomRoll = 6;
		PrintStream originalOut = System.out;
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		String expectedOut = "Chet was added\r\n" +
				"They are player number 1\r\n" +
				"Pat was added\r\n" +
				"They are player number 2\r\n" +
				"Sue was added\r\n" +
				"They are player number 3\r\n" +
				"Chet is the current player\r\n" +
				"They have rolled a 6\r\n" +
				"Chet's new location is 6\r\n" +
				"The category is Sports\r\n" +
				"Sports Question 0\r\n" +
				"Question was incorrectly answered\r\n" +
				"Chet was sent to the penalty box\r\n";

		Game aGame = new Game();
		aGame.add("Chet");
		aGame.add("Pat");
		aGame.add("Sue");


		aGame.roll(randomRoll);

		boolean notAWinner = aGame.wrongAnswer();

		assertTrue(notAWinner);
		assertEquals(expectedOut, outContent.toString());
		System.setOut(originalOut);
	}
}
