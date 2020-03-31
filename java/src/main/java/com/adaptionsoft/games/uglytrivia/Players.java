package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.function.Consumer;

public class Players {

    private ArrayList<Player> playerList;
    private int[] places;
    private int[] purses;
    private boolean[] inPenaltyBox;


    public Players() {
        this.places = new int[6];
        this.inPenaltyBox = new boolean[6];
        this.purses = new int[6];
        this.playerList = new ArrayList<>();
    }

    boolean didPlayerWin(int currentPlayer) {

        return !(this.purses[currentPlayer] == 6);
    }

    boolean isDidPlayerWin(int currentPlayer, boolean isGettingOutOfPenaltyBox, boolean inPenaltyBox) {
        boolean didPlayerWin;
        Player playerName = playerList.get(currentPlayer);
        if (inPenaltyBox) {
            didPlayerWin = true;

            if (isGettingOutOfPenaltyBox) {
                System.out.println("Answer was correct!!!!");
                earnCoin(currentPlayer);
                int playerPurse = this.purses[currentPlayer];
                System.out.println(playerName + " now has " + playerPurse + " Gold Coins.");

                didPlayerWin = this.didPlayerWin(currentPlayer);
            }
        } else {
            System.out.println("Answer was corrent!!!!");
            earnCoin(currentPlayer);
            int playerPurse = this.purses[currentPlayer];
            System.out.println(playerName + " now has " + playerPurse + " Gold Coins.");

            didPlayerWin = this.didPlayerWin(currentPlayer);
        }
        return didPlayerWin;
    }

    private void earnCoin(int currentPlayer) {
        this.purses[currentPlayer]++;
    }

    void putPlayerInPenaltyBox(int currentPlayer) {
        inPenaltyBox[currentPlayer] = true;
    }

    boolean isInPenaltyBox(int currentPlayer) {
        return inPenaltyBox[currentPlayer];
    }

    int getPlayerNewPlace(int roll, int currentPlayer) {
        int playerNewPlace = calculateNewPlace(roll, places[currentPlayer]);
        places[currentPlayer] = playerNewPlace;
        return playerNewPlace;
    }

    int calculateNewPlace(int roll, int currentPlace) {
        int playerNewPlace = currentPlace + roll;
        if (playerNewPlace > 11) {
            playerNewPlace = playerNewPlace - 12;
        }
        return playerNewPlace;
    }

    int getIndexCategory(int currentPlayer, int categoriesCount) {
        return this.places[currentPlayer] % categoriesCount;
    }

    void add(String playerName) {
        int initPursePlayer = 0;
        int initPlacePlayer = 0;
        boolean initPenaltyPlayer = false;
        playerList.add(new Player(playerName,initPursePlayer,initPlacePlayer,inPenaltyBox));
        purses[countPlayers()] = initPursePlayer;
        places[countPlayers()] = initPlacePlayer;
        inPenaltyBox[countPlayers()] = initPenaltyPlayer;

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
}
