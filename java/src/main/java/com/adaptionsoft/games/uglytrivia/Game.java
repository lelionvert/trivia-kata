package com.adaptionsoft.games.uglytrivia;

public class Game {
    private final Board board;

    private Player player = null;
    private QuestionBoard questionBoard;

    public Game() {
        questionBoard = new QuestionBoard();
        board = new Board();
    }

    public void add(String playerName) {

        board.add(playerName);

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + board.countPlayers());
    }

    public void roll(int roll) {

        if (player == null) nextPlayer();

        player.launchRoll(roll, this.questionBoard);

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
