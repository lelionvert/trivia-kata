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

    String currentCategory(Player player) {

        switch (players.getIndexCategory(4, player)) {
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

        Player player = players.playerList.get(this.currentPlayer);
        if (player.inPenaltyBox()) {
            if (roll % 2 != 0) {

                players.gettingOutOfPenaltyBox(true, player);
                printCommand("%s is getting out of the penalty box");

                int playerNewPlace = players.getPlayerNewPlace(roll, player);

                printCommand("%s's new location is " + playerNewPlace);
                System.out.println("The category is " + currentCategory(players.playerList.get(currentPlayer)));
                askQuestion();
            } else {
                printCommand("%s is not getting out of the penalty box");
                players.gettingOutOfPenaltyBox(false, player);
            }

        } else {

            int playerNewPlace = players.getPlayerNewPlace(roll, player);

            printCommand("%s's new location is " + playerNewPlace);
            System.out.println("The category is " + currentCategory(players.playerList.get(currentPlayer)));
            askQuestion();
        }

    }

    private void printCommand(String format) {
        Player playerName = players.playerList.get(this.currentPlayer);
        players.print(System.out::println, format, playerName);
    }

    private void askQuestion() {
        Player player = players.playerList.get(currentPlayer);

        if (currentCategory(player).equals("Pop"))
            System.out.println(popQuestions.removeFirst());
        if (currentCategory(player).equals("Science"))
            System.out.println(scienceQuestions.removeFirst());
        if (currentCategory(player).equals("Sports"))
            System.out.println(sportsQuestions.removeFirst());
        if (currentCategory(player).equals("Rock"))
            System.out.println(rockQuestions.removeFirst());
    }


    public boolean wasCorrectlyAnswered() {
        boolean didPlayerWin;
        Player player = players.playerList.get(currentPlayer);

        didPlayerWin = players.isDidPlayerWin(player);
        getNextPlayer();
        return didPlayerWin;
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");

        printCommand("%s was sent to the penalty box");
        Player player = players.playerList.get(this.currentPlayer);
        players.putPlayerInPenaltyBox(player);

        getNextPlayer();

        return true;
    }

    private void getNextPlayer() {
        currentPlayer++;
        if (currentPlayer == players.countPlayers()) currentPlayer = 0;
        presentPlayer = players.fetchNextPlayer();
    }

}
