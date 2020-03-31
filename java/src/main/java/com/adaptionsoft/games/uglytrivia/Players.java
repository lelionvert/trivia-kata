package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class Players {

    public ArrayList<Player> playerList;
    private Queue<Player> playerQueue;


    public Players() {
        this.playerList = new ArrayList<>();
        this.playerQueue = new LinkedList<>();
    }

    boolean didPlayerWin(Player player) {

        return !(player.purses() == 6);
    }

    boolean isDidPlayerWin(Player player) {
        if (player.inPenaltyBox()) {

            if (player.isGettingOutOfPenaltyBox()) {
                System.out.println("Answer was correct!!!!");
                player.earnCoin();
                System.out.println(player + " now has " + player.purses() + " Gold Coins.");

                return this.didPlayerWin(player);
            } else {
                return true;
            }
        } else {
            System.out.println("Answer was correct!!!!");
            player.earnCoin();
            System.out.println(player + " now has " + player.purses() + " Gold Coins.");

            return this.didPlayerWin(player);
        }
    }

    void putPlayerInPenaltyBox(Player player) {
        player.setPenaltyBox(true);
    }

    int getPlayerNewPlace(int roll, Player player) {
        return player.updatePlace(roll);
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

    void gettingOutOfPenaltyBox(boolean statePlayerInPenaltyBox, Player player) {
        player.gettingOutOfPenaltyBox(statePlayerInPenaltyBox);
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
