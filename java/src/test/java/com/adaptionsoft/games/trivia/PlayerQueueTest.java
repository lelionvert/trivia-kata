package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.uglytrivia.Player;
import com.adaptionsoft.games.uglytrivia.Players;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerQueueTest {

    @Test
    public void returnsNextPlayerInListPlayersWhenCallingFetchNexPlayerMethod() {

        Players players = new Players();
        players.addInQueue("Player1");
        players.addInQueue("Player2");
        players.addInQueue("Player3");

        Player currentPlayer = players.fetchNextPlayer();
        Player expectedPlayer = new Player("Player1");
        assertThat(currentPlayer).isEqualTo(expectedPlayer);


        currentPlayer = players.fetchNextPlayer();
        expectedPlayer = new Player("Player2");
        assertThat(currentPlayer).isEqualTo(expectedPlayer);

    }
}
