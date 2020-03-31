package com.adaptionsoft.games.uglytrivia;

public class Player {

    private String playerName;

    @Override
    public String toString() {
        return playerName;
    }

    public Player(String playerName, int initPursePlayer, int initPlacePlayer, boolean[] inPenaltyBox) {

        this.playerName = playerName;
    }
}
