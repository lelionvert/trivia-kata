package com.adaptionsoft.games.uglytrivia;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Game {
    private final Board board;

    private Player player = null;
    public Map<Category, LinkedList<String>> questionsByCategory = new HashMap<>();
    private QuestionBoard questionBoard;

    public Game() {

        initQuestionBoard();

        board = new Board();

    }

    private void initQuestionBoard() {
        questionBoard = new QuestionBoard(questionsByCategory);
        QuestionBoard.initializeQuestionCategories(questionsByCategory);

        QuestionBoard.fillQuestionCategories(questionsByCategory);

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
        questionBoard.askQuestion(player);
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
