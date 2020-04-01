package com.adaptionsoft.games.uglytrivia;

import java.util.Objects;
import java.util.function.Consumer;

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
                return doesWinWhenCorrectlyAnswered();
            } else {
                return true;
            }
        } else {
            return doesWinWhenCorrectlyAnswered();
        }
    }

    private boolean doesWinWhenCorrectlyAnswered() {
        System.out.println("Answer was correct!!!!");
        earnCoin();
        System.out.println(this + " now has " + purses() + " Gold Coins.");

        return didPlayerWin();
    }

    boolean didPlayerWin() {
        return purses() != 6;
    }

    void print(Consumer<String> consumer, String format) {
        consumer.accept(String.format(format, this));
    }

    void printCommand(String format) {
        print(System.out::println, format);
    }

    void launchRoll(int roll, QuestionBoard questionBoard) {

        printCommand("%s is the current player");
        System.out.println("They have rolled a " + roll);

        if (inPenaltyBox()) {
            if (roll % 2 != 0) {

                gettingOutOfPenaltyBox(true);
                printCommand("%s is getting out of the penalty box");

                move(roll);

                System.out.println("The category is " + Category.currentCategory(this).getValue());
                questionBoard.askQuestion(this);
            } else {
                printCommand("%s is not getting out of the penalty box");
                gettingOutOfPenaltyBox(false);
            }

        } else {

            move(roll);
            System.out.println("The category is " + Category.currentCategory(this).getValue());
            questionBoard.askQuestion(this);
        }
    }

    void move(int roll) {
        int playerNewPlace = updatePlace(roll);

        printCommand("%s's new location is " + playerNewPlace);
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
