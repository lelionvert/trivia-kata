package com.adaptionsoft.games.uglytrivia;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Game {
    final Board board;

    LinkedList<String> popQuestions = new LinkedList<>();
    LinkedList<String> scienceQuestions = new LinkedList<>();
    LinkedList<String> sportsQuestions = new LinkedList<>();
    LinkedList<String> rockQuestions = new LinkedList<>();

    private Player player = null;
    private Map<Category, LinkedList<String>> questionsByCategory = new HashMap<>();

    public Game() {

        initializeQuestionCategories();

        for (int i = 0; i < 50; i++) {
            String questionIndex = " Question " + i;
            popQuestions.addLast(Category.POP.getValue() + questionIndex);
            scienceQuestions.addLast(Category.SCIENCE.getValue() + " Question " + i);
            sportsQuestions.addLast(Category.SPORTS.getValue() + " Question " + i);
            rockQuestions.addLast(Category.ROCK.getValue() + " Question " + i);

            Arrays.stream(Category.values())
                    .forEach(category -> questionsByCategory
                            .get(category)
                            .add(category.getValue() + questionIndex));

        }
        board = new Board();

    }

    private void initializeQuestionCategories() {
        for (Category category : Category.values()) {
            questionsByCategory.put(category, new LinkedList<>());
        }
    }

    Category currentCategory(Player player) {

        switch (player.place() % 4) {
            case 0:
                return Category.POP;
            case 1:
                return Category.SCIENCE;
            case 2:
                return Category.SPORTS;
            default:
                return Category.ROCK;
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
                System.out.println("The category is " + currentCategory(player).getValue());
                askQuestion();
            } else {
                printCommand("%s is not getting out of the penalty box");
                player.gettingOutOfPenaltyBox(false);
            }

        } else {

            int playerNewPlace = player.updatePlace(roll);

            printCommand("%s's new location is " + playerNewPlace);
            System.out.println("The category is " + currentCategory(player).getValue());
            askQuestion();
        }

    }

    private void printCommand(String format) {
        board.print(System.out::println, format, player);
    }

    private void askQuestion() {

        Category category = currentCategory(player);

        String question = questionsByCategory.get(category).remove();
        System.out.println(question);

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
