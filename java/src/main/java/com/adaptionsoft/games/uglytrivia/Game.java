package com.adaptionsoft.games.uglytrivia;

import java.util.LinkedList;

public class Game {
    final Players players;

    LinkedList<String> popQuestions = new LinkedList<>();
    LinkedList<String> scienceQuestions = new LinkedList<>();
    LinkedList<String> sportsQuestions = new LinkedList<>();
    LinkedList<String> rockQuestions = new LinkedList<>();

    int currentPlayer = 0;
    private Player presentPlayer;

    public Game() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast(("Science Question " + i));
            sportsQuestions.addLast(("Sports Question " + i));
            rockQuestions.addLast(createRockQuestion(i));
        }
        players = new Players();

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
        return (players.countPlayers() >= 2);
    }

    public boolean add(String playerName) {

        players.add(playerName);

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.countPlayers());
        return true;
    }

    public void roll(int roll) {

        printCommand("%s is the current player");
        System.out.println("They have rolled a " + roll);

        if (players.isInPenaltyBox(this.currentPlayer)) {
            if (roll % 2 != 0) {

                players.gettingOutOfPenaltyBox(currentPlayer,true);
                printCommand("%s is getting out of the penalty box");

                int playerNewPlace = players.getPlayerNewPlace(roll, this.currentPlayer);

                printCommand("%s's new location is " + playerNewPlace);
                System.out.println("The category is " + currentCategory(currentPlayer));
                askQuestion();
            } else {
                printCommand("%s is not getting out of the penalty box");
                players.gettingOutOfPenaltyBox(currentPlayer, false);
            }

        } else {

            int playerNewPlace = players.getPlayerNewPlace(roll, this.currentPlayer);

            printCommand("%s's new location is " + playerNewPlace);
            System.out.println("The category is " + currentCategory(currentPlayer));
            askQuestion();
        }

    }

    private void printCommand(String format) {
        players.print(System.out::println, format, this.currentPlayer);
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
        didPlayerWin = players.isDidPlayerWin(currentPlayer);
        getNextPlayer();
        return didPlayerWin;
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");

        printCommand("%s was sent to the penalty box");
        players.putPlayerInPenaltyBox(this.currentPlayer);

        getNextPlayer();

        return true;
    }

    private void getNextPlayer() {
        currentPlayer++;
        if (currentPlayer == players.countPlayers()) currentPlayer = 0;
        presentPlayer = players.fetchNextPlayer();
    }

}
