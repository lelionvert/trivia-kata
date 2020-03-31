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

    boolean didPlayerWin(int currentPlayer) {

        int playerPurses = playerList.get(currentPlayer).purses();
        return !(playerPurses == 6);
    }

    boolean isDidPlayerWin(int currentPlayer) {
        Player player = playerList.get(currentPlayer);
        if (playerList.get(currentPlayer).inPenaltyBox()) {

            if (playerList.get(currentPlayer).isGettingOutOfPenaltyBox()) {
                System.out.println("Answer was correct!!!!");
                playerList.get(currentPlayer).earnCoin();
                System.out.println(player + " now has " + player.purses() + " Gold Coins.");

                return this.didPlayerWin(currentPlayer);
            } else {
                return true;
            }
        } else {
            System.out.println("Answer was correct!!!!");
            playerList.get(currentPlayer).earnCoin();
            System.out.println(player + " now has " + player.purses() + " Gold Coins.");

            return this.didPlayerWin(currentPlayer);
        }
    }

    void putPlayerInPenaltyBox(int currentPlayer) {
        playerList.get(currentPlayer).setPenaltyBox(true);
    }

    boolean isInPenaltyBox(int currentPlayer) {
        return playerList.get(currentPlayer).inPenaltyBox();
    }

    int getPlayerNewPlace(int roll, int currentPlayer) {
        return updatePlace(currentPlayer, roll);
    }

    private int updatePlace(int currentPlayer, int roll) {

        return playerList.get(currentPlayer).updatePlace(roll);
    }

    int getIndexCategory(int currentPlayer, int categoriesCount) {
        int place = playerList.get(currentPlayer).place();

        return place % categoriesCount;
    }

    public void add(String playerName) {
        playerList.add(new Player(playerName));
        playerQueue.add(new Player(playerName));

    }

    Player currentPlayerName(int currentPlayer) {
        return playerList.get(currentPlayer);
    }

    int countPlayers() {
        return playerList.size();
    }

    void print(Consumer<String> consumer, String format, int currentPlayer) {
        Player currentString = currentPlayerName(currentPlayer);
        consumer.accept(String.format(format, currentString));
    }

    void gettingOutOfPenaltyBox(int currentPlayer, boolean statePlayerInPenaltyBox) {
        playerList.get(currentPlayer).gettingOutOfPenaltyBox(statePlayerInPenaltyBox);
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
