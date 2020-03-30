package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public class Players {

    public ArrayList<String> names;
    public int[] purses;

    public Players(ArrayList<String> names, int[] purses) {
        this.names = names;
        this.purses = purses;
    }

    static int earnCoin(int purs) {
        return purs + 1;
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
                this.purses[currentPlayer] = earnCoin(this.purses[currentPlayer]);
                System.out.println(names.get(currentPlayer)
                        + " now has "
                        + this.purses[currentPlayer]
                        + " Gold Coins.");

                didPlayerWin = this.didPlayerWin(currentPlayer);
            }
        } else {
            System.out.println("Answer was corrent!!!!");
            this.purses[currentPlayer] = earnCoin(this.purses[currentPlayer]);
            System.out.println(names.get(currentPlayer)
                    + " now has "
                    + this.purses[currentPlayer]
                    + " Gold Coins.");

            didPlayerWin = this.didPlayerWin(currentPlayer);
        }
        return didPlayerWin;
    }

    void addPlayerName(String playerName) {
        names.add(playerName);
    }

    void addPurses() {
        purses[names.size()] = 0;
    }
}
