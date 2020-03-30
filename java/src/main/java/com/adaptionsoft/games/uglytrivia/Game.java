package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
    final Players players;
    ArrayList<String> playerNames = new ArrayList<>();

    LinkedList<String> popQuestions = new LinkedList<>();
    LinkedList<String> scienceQuestions = new LinkedList<>();
    LinkedList<String> sportsQuestions = new LinkedList<>();
    LinkedList<String> rockQuestions = new LinkedList<>();

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    public Game() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast(("Science Question " + i));
            sportsQuestions.addLast(("Sports Question " + i));
            rockQuestions.addLast(createRockQuestion(i));
        }
        players = new Players(playerNames);
    }

    String currentCategory(int currentPlayer) {

        switch (players.getIndexCategory(currentPlayer, 4)) {
            case 0:
                return "Pop";
            case 1:
                return "Science";
            case 2:
                return "Sports";
            default:
                return "Rock";
        }
    }

    public String createRockQuestion(int index) {
        return "Rock Question " + index;
    }

    public boolean isPlayable() {
        return (playerNames.size() >= 2);
    }

    public boolean add(String playerName) {

        players.add(playerName);

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + playerNames.size());
        return true;
    }

    public void roll(int roll) {
        System.out.println(playerNames.get(currentPlayer) + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (players.isInPenaltyBox(this.currentPlayer)) {
            if (roll % 2 != 0) {

                isGettingOutOfPenaltyBox = true;
                System.out.println(playerNames.get(currentPlayer) + " is getting out of the penalty box");

                int playerNewPlace = players.getPlayerNewPlace(roll, this.currentPlayer);

                System.out.println(playerNames.get(currentPlayer)
                        + "'s new location is "
                        + playerNewPlace);
                System.out.println("The category is " + currentCategory(currentPlayer));
                askQuestion();
            } else {
                System.out.println(playerNames.get(currentPlayer) + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {

            int playerNewPlace = players.getPlayerNewPlace(roll, this.currentPlayer);

            System.out.println(playerNames.get(currentPlayer)
                    + "'s new location is "
                    + playerNewPlace);
            System.out.println("The category is " + currentCategory(currentPlayer));
            askQuestion();
        }

    }

    private void askQuestion() {
        if (currentCategory(currentPlayer).equals("Pop"))
            System.out.println(popQuestions.removeFirst());
        if (currentCategory(currentPlayer).equals("Science"))
            System.out.println(scienceQuestions.removeFirst());
        if (currentCategory(currentPlayer).equals("Sports"))
            System.out.println(sportsQuestions.removeFirst());
        if (currentCategory(currentPlayer).equals("Rock"))
            System.out.println(rockQuestions.removeFirst());
    }


    public boolean wasCorrectlyAnswered() {
        boolean didPlayerWin;
        didPlayerWin = players.isDidPlayerWin(currentPlayer, isGettingOutOfPenaltyBox, players.isInPenaltyBox(this.currentPlayer));
        getNextPlayer();
        return didPlayerWin;
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");

        System.out.println(playerNames.get(currentPlayer) + " was sent to the penalty box");
        players.putPlayerInPenaltyBox(this.currentPlayer);

        getNextPlayer();

        return true;
    }

    private void getNextPlayer() {
        currentPlayer++;
        if (currentPlayer == playerNames.size()) currentPlayer = 0;
    }

}
