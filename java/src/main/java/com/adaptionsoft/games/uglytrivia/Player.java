package com.adaptionsoft.games.uglytrivia;

public class Player {

    private String playerName;
    private boolean isGettingOutOfPenaltyBox;
    private int purses;
    private int place;
    private boolean inPenaltyBox;

    static int calculateNewPlace(int roll, int currentPlace) {
        int playerNewPlace = currentPlace + roll;
        if (playerNewPlace > 11) {
            playerNewPlace = playerNewPlace - 12;
        }
        return playerNewPlace;
    }

    @Override
    public String toString() {
        return playerName;
    }

    public Player(String playerName) {

        this.playerName = playerName;
        this.isGettingOutOfPenaltyBox = false;
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

    public int place() {
        return place;
    }

    public int updatePlace(int roll) {
        place = calculateNewPlace(roll, place);
        return place;
    }

    public void gettingOutOfPenaltyBox(boolean statePlayerInPenaltyBox) {
        isGettingOutOfPenaltyBox = statePlayerInPenaltyBox;
    }

    public boolean isGettingOutOfPenaltyBox() {
        return isGettingOutOfPenaltyBox;
    }

    public boolean inPenaltyBox() {
        return inPenaltyBox;
    }

    public void setPenaltyBox(boolean penaltyState) {
        inPenaltyBox = penaltyState;
    }
}
