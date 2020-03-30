package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
    ArrayList<String> players = new ArrayList<>();
    int[] places = new int[6];
    int[] purses = new int[6];
    boolean[] inPenaltyBox = new boolean[6];

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
    }

    int calculateNewPlace(int roll, int currentPlace) {
        int playerNewPlace = currentPlace + roll;
        if (playerNewPlace > 11) {
            playerNewPlace = playerNewPlace - 12;
        }
        return playerNewPlace;
    }

    String currentCategory(int currentPlayer, int[] places) {

        switch (places[currentPlayer] % 4) {
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

    void movePlayer(int roll) {
        int playerNewPlace = calculateNewPlace(roll, places[currentPlayer]);

        places[currentPlayer] = playerNewPlace;
        System.out.println(players.get(currentPlayer)
                + "'s new location is "
                + playerNewPlace);
        System.out.println("The category is " + currentCategory(currentPlayer, places));
    }

    public String createRockQuestion(int index) {
        return "Rock Question " + index;
    }

    public boolean isPlayable() {
        return (numberOfPlayers() >= 2);
    }

    public boolean add(String playerName) {

        players.add(playerName);
        places[numberOfPlayers()] = 0;
        purses[numberOfPlayers()] = 0;
        inPenaltyBox[numberOfPlayers()] = false;

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
        return true;
    }

    public int numberOfPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        System.out.println(players.get(currentPlayer) + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (inPenaltyBox[currentPlayer]) {
            if (roll % 2 != 0) {

                isGettingOutOfPenaltyBox = true;
                System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");

                this.movePlayer(roll);
                askQuestion();
            } else {
                System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {

            this.movePlayer(roll);
            askQuestion();
        }

    }

    private void askQuestion() {
        if (currentCategory(currentPlayer, places).equals("Pop"))
            System.out.println(popQuestions.removeFirst());
        if (currentCategory(currentPlayer, places).equals("Science"))
            System.out.println(scienceQuestions.removeFirst());
        if (currentCategory(currentPlayer, places).equals("Sports"))
            System.out.println(sportsQuestions.removeFirst());
        if (currentCategory(currentPlayer, places).equals("Rock"))
            System.out.println(rockQuestions.removeFirst());
    }


    public boolean wasCorrectlyAnswered() {
        boolean didPlayerWin;
        didPlayerWin = Players.isDidPlayerWin(currentPlayer, players, purses, isGettingOutOfPenaltyBox, inPenaltyBox[currentPlayer]);
        getNextPlayer();
        return didPlayerWin;
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");

        putPlayerInPenaltyBox();

        getNextPlayer();

        return true;
    }

    private void putPlayerInPenaltyBox() {
        System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;
    }

    private void getNextPlayer() {
        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
    }

}
