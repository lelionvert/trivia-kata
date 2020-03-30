package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public class Players {

    private ArrayList<String> names;
    private int[] purses;
    private boolean[] inPenaltyBox;

    public Players(ArrayList<String> names, boolean[] inPenaltyBox) {
        this.names = names;
        this.inPenaltyBox = inPenaltyBox;
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

    void add(String playerName) {
        names.add(playerName);
        purses[names.size()] = 0;
    }

    void putPlayerInPenaltyBox(Game game) {
        game.inPenaltyBox[game.currentPlayer] = true;
    }

    boolean isInPenaltyBox(Game game) {
        return game.inPenaltyBox[game.currentPlayer];
    }

    void initPenaltyBox(Game game) {
        inPenaltyBox[game.playerNames.size()] = false;
    }
}
