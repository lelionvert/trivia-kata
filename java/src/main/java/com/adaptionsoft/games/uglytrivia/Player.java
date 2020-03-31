package com.adaptionsoft.games.uglytrivia;

public class Player {

    private String playerName;
    private int purses;
    private int place;
    private boolean inPenaltyBox;

    @Override
    public String toString() {
        return playerName;
    }

    public Player(String playerName) {

        this.playerName = playerName;
        this.purses = 0;
        this.place = 0;
        this.inPenaltyBox = false;
    }

    public int purses() {
        return purses;
    }

    public void earnCoin() {
        purses++;
    }
}
