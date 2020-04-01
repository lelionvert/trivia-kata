package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.uglytrivia.Player;
import com.adaptionsoft.games.uglytrivia.Board;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerQueueTest {

    @Test
    public void returnsNextPlayerInListPlayersWhenCallingFetchNexPlayerMethod() {

        Board board = new Board();
        board.addInQueue("Player1");
        board.addInQueue("Player2");
        board.addInQueue("Player3");

        Player currentPlayer = board.fetchNextPlayer();
        Player expectedPlayer = new Player("Player1");
        assertThat(currentPlayer).isEqualTo(expectedPlayer);


        currentPlayer = board.fetchNextPlayer();
        expectedPlayer = new Player("Player2");
        assertThat(currentPlayer).isEqualTo(expectedPlayer);

    }
}
