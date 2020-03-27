package com.adaptionsoft.games.trivia;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

		assertThat(notAWinner).isTrue();
		assertThat(expectedOut).isEqualTo(outContent.toString());
		System.setOut(originalOut);
	}

	@ParameterizedTest
	@CsvSource({"1, Science","2, Sports", "3, Rock", "4, Pop", "5, Science", "6, Sports"})
	public void testParam(int randomRoll, String category) {
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
				"They have rolled a " + randomRoll + "\r\n" +
				"Chet's new location is " + randomRoll + "\r\n" +
				"The category is " + category + "\r\n" +
				category + " Question 0\r\n" +
				"Question was incorrectly answered\r\n" +
				"Chet was sent to the penalty box\r\n";

		Game aGame = new Game();
		aGame.add("Chet");
		aGame.add("Pat");
		aGame.add("Sue");


		aGame.roll(randomRoll);

		boolean notAWinner = aGame.wrongAnswer();

		assertThat(notAWinner).isTrue();
		assertThat(expectedOut).isEqualTo(outContent.toString());
		System.setOut(originalOut);
	}

}
