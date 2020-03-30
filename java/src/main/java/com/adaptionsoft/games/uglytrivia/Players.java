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

    static boolean didPlayerWin(int currentPlayer, int[] purses) {
        return !(purses[currentPlayer] == 6);
    }

    boolean isDidPlayerWin(int[] purses, int currentPlayer, boolean isGettingOutOfPenaltyBox, boolean inPenaltyBox) {
        boolean didPlayerWin;
        this.purses = purses;
        if (inPenaltyBox) {
            didPlayerWin = true;

            if (isGettingOutOfPenaltyBox) {
                System.out.println("Answer was correct!!!!");
                this.purses[currentPlayer] = earnCoin(this.purses[currentPlayer]);
                System.out.println(names.get(currentPlayer)
                        + " now has "
                        + this.purses[currentPlayer]
                        + " Gold Coins.");

                didPlayerWin = didPlayerWin(currentPlayer, this.purses);
            }
        } else {
            System.out.println("Answer was corrent!!!!");
            this.purses[currentPlayer] = earnCoin(this.purses[currentPlayer]);
            System.out.println(names.get(currentPlayer)
                    + " now has "
                    + this.purses[currentPlayer]
                    + " Gold Coins.");

            didPlayerWin = didPlayerWin(currentPlayer, this.purses);
        }
        return didPlayerWin;
    }

    void addPlayerName(String playerName) {
        names.add(playerName);
    }

    void addPurses(Game game) {
        purses[game.numberOfPlayers()] = 0;
    }
}
