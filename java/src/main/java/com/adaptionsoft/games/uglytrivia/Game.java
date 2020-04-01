package com.adaptionsoft.games.uglytrivia;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Game {
    private final Board board;

    private Player player = null;
    private Map<Category, LinkedList<String>> questionsByCategory = new HashMap<>();

    public Game() {

        initializeQuestionCategories();

        fillQuestionCategories();

        board = new Board();

    }

    private void fillQuestionCategories() {
        for (int i = 0; i < 50; i++) {
            String questionIndex = " Question " + i;
            Arrays.stream(Category.values())
                    .forEach(category -> questionsByCategory
                            .get(category)
                            .add(category.getValue() + questionIndex));
        }
    }

    private void initializeQuestionCategories() {
        for (Category category : Category.values()) {
            questionsByCategory.put(category, new LinkedList<>());
        }
    }

    public void add(String playerName) {

        board.add(playerName);

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + board.countPlayers());
    }

    public void roll(int roll) {

        if (player == null) nextPlayer();

        player.printCommand("%s is the current player");
        System.out.println("They have rolled a " + roll);

        if (player.inPenaltyBox()) {
            if (roll % 2 != 0) {

                player.gettingOutOfPenaltyBox(true);
                player.printCommand("%s is getting out of the penalty box");

                move(roll);
            } else {
                player.printCommand("%s is not getting out of the penalty box");
                player.gettingOutOfPenaltyBox(false);
            }

        } else {

            move(roll);
        }

    }

    private void move(int roll) {
        int playerNewPlace = player.updatePlace(roll);

        player.printCommand("%s's new location is " + playerNewPlace);
        System.out.println("The category is " + Category.currentCategory(player).getValue());
        askQuestion();
    }

    private void askQuestion() {
        Category category = Category.currentCategory(player);
        System.out.println(questionsByCategory.get(category).remove());
    }


    public boolean wasCorrectlyAnswered() {
        boolean didPlayerWin;

        didPlayerWin = player.isDidPlayerWin();
        nextPlayer();
        return didPlayerWin;
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");

        player.printCommand("%s was sent to the penalty box");
        player.setPenaltyBox(true);

        nextPlayer();

        return true;
    }

    private void nextPlayer() {
        player = board.fetchNextPlayer();
    }

}
