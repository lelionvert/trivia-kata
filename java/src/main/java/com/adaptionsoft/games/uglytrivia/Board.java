package com.adaptionsoft.games.uglytrivia;

import java.util.LinkedList;
import java.util.Queue;

public class Board {

    private Queue<Player> playerQueue;


    public Board() {
        this.playerQueue = new LinkedList<>();
    }

    public void add(String playerName) {
        playerQueue.add(new Player(playerName));
    }

    int countPlayers() {
        return playerQueue.size();
    }

    public void addInQueue(String player) {
        playerQueue.add(new Player(player));
    }

    public Player fetchNextPlayer() {
        Player currentPlayer = playerQueue.poll();
        playerQueue.add(currentPlayer);
        return currentPlayer;
    }

}
