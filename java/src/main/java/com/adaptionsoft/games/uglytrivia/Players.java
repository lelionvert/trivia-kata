package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public class Players {

    public ArrayList<String> names;

    public Players(ArrayList<String> names) {
        this.names = names;
    }

    static int earnCoin(int purs) {
        return purs + 1;
    }

    static boolean didPlayerWin(int currentPlayer, int[] purses) {
        return !(purses[currentPlayer] == 6);
    }

    boolean isDidPlayerWin(int currentPlayer, int[] purses, boolean isGettingOutOfPenaltyBox, boolean inPenaltyBox) {
        boolean didPlayerWin;
        if (inPenaltyBox) {
            didPlayerWin = true;

            if (isGettingOutOfPenaltyBox) {
                System.out.println("Answer was correct!!!!");
                purses[currentPlayer] = earnCoin(purses[currentPlayer]);
                System.out.println(names.get(currentPlayer)
                        + " now has "
                        + purses[currentPlayer]
                        + " Gold Coins.");

                didPlayerWin = didPlayerWin(currentPlayer, purses);
            }
        } else {
            System.out.println("Answer was corrent!!!!");
            purses[currentPlayer] = earnCoin(purses[currentPlayer]);
            System.out.println(names.get(currentPlayer)
                    + " now has "
                    + purses[currentPlayer]
                    + " Gold Coins.");

            didPlayerWin = didPlayerWin(currentPlayer, purses);
        }
        return didPlayerWin;
    }

    void addPlayerName(String playerName) {
        names.add(playerName);
    }
}
