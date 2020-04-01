package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class Players {

    private ArrayList<Player> playerList;
    private Queue<Player> playerQueue;


    public Players() {
        this.playerList = new ArrayList<>();
        this.playerQueue = new LinkedList<>();
    }

    int getIndexCategory(int categoriesCount, Player player) {

        return player.place() % categoriesCount;
    }

    public void add(String playerName) {
        playerList.add(new Player(playerName));
        playerQueue.add(new Player(playerName));

    }

    int countPlayers() {
        return playerList.size();
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
