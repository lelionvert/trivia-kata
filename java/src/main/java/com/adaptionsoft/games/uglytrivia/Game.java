package com.adaptionsoft.games.uglytrivia;

import java.util.LinkedList;

public class Game {
    final Board board;

    LinkedList<String> popQuestions = new LinkedList<>();
    LinkedList<String> scienceQuestions = new LinkedList<>();
    LinkedList<String> sportsQuestions = new LinkedList<>();
    LinkedList<String> rockQuestions = new LinkedList<>();

    private Player player = null;

    public Game() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast("Science Question " + i);
            sportsQuestions.addLast("Sports Question " + i);
            rockQuestions.addLast("Rock Question " + i);
        }
        board = new Board();

    }

    String currentCategory(Player player) {

        switch (player.place() % 4) {
            case 0:
                return Category.POP.getValue();
            case 1:
                return Category.SCIENCE.getValue();
            case 2:
                return Category.SPORTS.getValue();
            default:
                return Category.ROCK.getValue();
        }
    }

    public boolean isPlayable() {
        return (board.countPlayers() >= 2);
    }

    public boolean add(String playerName) {

        board.add(playerName);

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + board.countPlayers());
        return true;
    }

    public void roll(int roll) {

        if (player == null) getNextPlayer();

        printCommand("%s is the current player");
        System.out.println("They have rolled a " + roll);

        if (player.inPenaltyBox()) {
            if (roll % 2 != 0) {

                player.gettingOutOfPenaltyBox(true);
                printCommand("%s is getting out of the penalty box");

                int playerNewPlace = player.updatePlace(roll);

                printCommand("%s's new location is " + playerNewPlace);
                System.out.println("The category is " + currentCategory(player));
                askQuestion();
            } else {
                printCommand("%s is not getting out of the penalty box");
                player.gettingOutOfPenaltyBox(false);
            }

        } else {

            int playerNewPlace = player.updatePlace(roll);

            printCommand("%s's new location is " + playerNewPlace);
            System.out.println("The category is " + currentCategory(player));
            askQuestion();
        }

    }

    private void printCommand(String format) {
        board.print(System.out::println, format, player);
    }

    private void askQuestion() {

        if (currentCategory(player).equals(Category.POP.getValue()))
            System.out.println(popQuestions.removeFirst());
        if (currentCategory(player).equals(Category.SCIENCE.getValue()))
            System.out.println(scienceQuestions.removeFirst());
        if (currentCategory(player).equals(Category.SPORTS.getValue()))
            System.out.println(sportsQuestions.removeFirst());
        if (currentCategory(player).equals(Category.ROCK.getValue()))
            System.out.println(rockQuestions.removeFirst());
    }


    public boolean wasCorrectlyAnswered() {
        boolean didPlayerWin;

        didPlayerWin = player.isDidPlayerWin();
        getNextPlayer();
        return didPlayerWin;
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");

        printCommand("%s was sent to the penalty box");
        player.setPenaltyBox(true);

        getNextPlayer();

        return true;
    }

    private void getNextPlayer() {
        player = board.fetchNextPlayer();
    }

}
