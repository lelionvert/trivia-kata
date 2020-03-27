package com.adaptionsoft.games.trivia;

import static org.junit.Assert.*;

import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class SomeTest {

	@Test
	public void true_is_true() throws Exception {
		assertTrue(true);
	}

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
	public void name() {
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


		aGame.roll(1);

		boolean notAWinner = aGame.wrongAnswer();

		assertTrue(notAWinner);
		assertEquals(expectedOut, outContent.toString());
		System.setOut(originalOut);
	}
}
