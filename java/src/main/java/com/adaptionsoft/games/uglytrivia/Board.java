package com.adaptionsoft.games.uglytrivia;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

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

    void print(Consumer<String> consumer, String format, Player playerName) {
        consumer.accept(String.format(format, playerName));
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
