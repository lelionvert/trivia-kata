package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.function.Consumer;

public class Players {

    private ArrayList<String> names;
    private int[] places;
    private int[] purses;
    private boolean[] inPenaltyBox;


    public Players() {
        this.names = new ArrayList<>();
        this.places = new int[6];
        this.inPenaltyBox = new boolean[6];
        this.purses = new int[6];
    }

    boolean didPlayerWin(int currentPlayer) {

        return !(this.purses[currentPlayer] == 6);
    }

    boolean isDidPlayerWin(int currentPlayer, boolean isGettingOutOfPenaltyBox, boolean inPenaltyBox) {
        boolean didPlayerWin;
        if (inPenaltyBox) {
            didPlayerWin = true;

            if (isGettingOutOfPenaltyBox) {
                System.out.println("Answer was correct!!!!");
                earnCoin(currentPlayer);
                System.out.println(names.get(currentPlayer)
                        + " now has "
                        + this.purses[currentPlayer]
                        + " Gold Coins.");

                didPlayerWin = this.didPlayerWin(currentPlayer);
            }
        } else {
            System.out.println("Answer was corrent!!!!");
            earnCoin(currentPlayer);
            System.out.println(names.get(currentPlayer)
                    + " now has "
                    + this.purses[currentPlayer]
                    + " Gold Coins.");

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
        names.add(playerName);
        purses[names.size()] = 0;
        places[names.size()] = 0;
        inPenaltyBox[names.size()] = false;
    }

    String currentPlayerName(int currentPlayer) {
        return names.get(currentPlayer);
    }

    int countPlayers() {
        return names.size();
    }

    void print(Consumer<String> consumer, String format, int currentPlayer) {
        String currentString = currentPlayerName(currentPlayer);
        String x = String.format(format, currentString);
        consumer.accept(x);
    }
}
