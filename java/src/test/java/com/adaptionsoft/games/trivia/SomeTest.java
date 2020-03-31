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
import java.util.Random;

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

	private void main(long seed){
		Game aGame = new Game();

		aGame.add("Chet");
		aGame.add("Pat");
		aGame.add("Sue");

		Random rand = new Random(seed);

		boolean notAWinner;
		do {

			aGame.roll(rand.nextInt(5) + 1);

			if (rand.nextInt(9) == 7) {
				notAWinner = aGame.wrongAnswer();
			} else {
				notAWinner = aGame.wasCorrectlyAnswered();
			}



		} while (notAWinner);
	}

	@Test
	public void mainTest(){
		PrintStream originalOut = System.out;
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		int randomRoll = 1;
		String category = "Science";
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
				"Answer was correct!!!!\r\n" +
				"Chet now has 1 Gold Coins.\r\n" +
				"Pat is the current player\r\n" +
				"They have rolled a 3\r\n" +
				"Pat's new location is 3\r\n" +
				"The category is Rock\r\n" +
				"Rock Question 0\r\n" +
				"Answer was correct!!!!\r\n" +
				"Pat now has 1 Gold Coins.\r\n" +
				"Sue is the current player\r\n" +
				"They have rolled a 5\r\n" +
				"Sue's new location is 5\r\n" +
				"The category is Science\r\n" +
				"Science Question 1\r\n" +
				"Answer was correct!!!!\r\n" +
				"Sue now has 1 Gold Coins.\r\n" +
				"Chet is the current player\r\n" +
				"They have rolled a 5\r\n" +
				"Chet's new location is 6\r\n" +
				"The category is Sports\r\n" +
				"Sports Question 0\r\n" +
				"Answer was correct!!!!\r\n" +
				"Chet now has 2 Gold Coins.\r\n" +
				"Pat is the current player\r\n" +
				"They have rolled a 4\r\n" +
				"Pat's new location is 7\r\n" +
				"The category is Rock\r\n" +
				"Rock Question 1\r\n" +
				"Answer was correct!!!!\r\n" +
				"Pat now has 2 Gold Coins.\r\n" +
				"Sue is the current player\r\n" +
				"They have rolled a 5\r\n" +
				"Sue's new location is 10\r\n" +
				"The category is Sports\r\n" +
				"Sports Question 1\r\n" +
				"Question was incorrectly answered\r\n" +
				"Sue was sent to the penalty box\r\n" +
				"Chet is the current player\r\n" +
				"They have rolled a 3\r\n" +
				"Chet's new location is 9\r\n" +
				"The category is Science\r\n" +
				"Science Question 2\r\n" +
				"Answer was correct!!!!\r\n" +
				"Chet now has 3 Gold Coins.\r\n" +
				"Pat is the current player\r\n" +
				"They have rolled a 3\r\n" +
				"Pat's new location is 10\r\n" +
				"The category is Sports\r\n" +
				"Sports Question 2\r\n" +
				"Question was incorrectly answered\r\n" +
				"Pat was sent to the penalty box\r\n" +
				"Sue is the current player\r\n" +
				"They have rolled a 3\r\n" +
				"Sue is getting out of the penalty box\r\n" +
				"Sue's new location is 1\r\n" +
				"The category is Science\r\n" +
				"Science Question 3\r\n" +
				"Answer was correct!!!!\r\n" +
				"Sue now has 2 Gold Coins.\r\n" +
				"Chet is the current player\r\n" +
				"They have rolled a 2\r\n" +
				"Chet's new location is 11\r\n" +
				"The category is Rock\r\n" +
				"Rock Question 2\r\n" +
				"Answer was correct!!!!\r\n" +
				"Chet now has 4 Gold Coins.\r\n" +
				"Pat is the current player\r\n" +
				"They have rolled a 2\r\n" +
				"Pat is not getting out of the penalty box\r\n" +
				"Sue is the current player\r\n" +
				"They have rolled a 1\r\n" +
				"Sue is getting out of the penalty box\r\n" +
				"Sue's new location is 2\r\n" +
				"The category is Sports\r\n" +
				"Sports Question 3\r\n" +
				"Answer was correct!!!!\r\n" +
				"Sue now has 3 Gold Coins.\r\n" +
				"Chet is the current player\r\n" +
				"They have rolled a 5\r\n" +
				"Chet's new location is 4\r\n" +
				"The category is Pop\r\n" +
				"Pop Question 0\r\n" +
				"Answer was correct!!!!\r\n" +
				"Chet now has 5 Gold Coins.\r\n" +
				"Pat is the current player\r\n" +
				"They have rolled a 4\r\n" +
				"Pat is not getting out of the penalty box\r\n" +
				"Sue is the current player\r\n" +
				"They have rolled a 3\r\n" +
				"Sue is getting out of the penalty box\r\n" +
				"Sue's new location is 5\r\n" +
				"The category is Science\r\n" +
				"Science Question 4\r\n" +
				"Answer was correct!!!!\r\n" +
				"Sue now has 4 Gold Coins.\r\n" +
				"Chet is the current player\r\n" +
				"They have rolled a 1\r\n" +
				"Chet's new location is 5\r\n" +
				"The category is Science\r\n" +
				"Science Question 5\r\n" +
				"Answer was correct!!!!\r\n" +
				"Chet now has 6 Gold Coins.\r\n";

		main(1L);

		assertThat(expectedOut).isEqualTo(outContent.toString());
		System.setOut(originalOut);
	}
}
