package com.adaptionsoft.games.uglytrivia;

import java.util.Objects;

public class Player {

    private String playerName;
    private boolean isGettingOutOfPenaltyBox;
    private int purses;
    private int place;
    private boolean inPenaltyBox;

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

    private int calculateNewPlace(int roll, int currentPlace) {
        int playerNewPlace = currentPlace + roll;
        if (playerNewPlace > 11) {
            playerNewPlace = playerNewPlace - 12;
        }
        return playerNewPlace;
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

    boolean isDidPlayerWin() {
        if (inPenaltyBox()) {

            if (isGettingOutOfPenaltyBox()) {
                System.out.println("Answer was correct!!!!");
                earnCoin();
                System.out.println(this + " now has " + purses() + " Gold Coins.");

                return didPlayerWin();
            } else {
                return true;
            }
        } else {
            System.out.println("Answer was correct!!!!");
            earnCoin();
            System.out.println(this + " now has " + purses() + " Gold Coins.");

            return didPlayerWin();
        }
    }

    boolean didPlayerWin() {

        return !(purses() == 6);
    }

    @Override
    public String toString() {
        return playerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(playerName, player.playerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerName);
    }

}
