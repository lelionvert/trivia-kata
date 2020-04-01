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

        player.printCommand("%s is the current player");
        System.out.println("They have rolled a " + roll);

        launchRoll(roll);

    }

    private void launchRoll(int roll) {
        QuestionBoard questionBoard = this.questionBoard;
        if (player.inPenaltyBox()) {
            if (roll % 2 != 0) {

                player.gettingOutOfPenaltyBox(true);
                player.printCommand("%s is getting out of the penalty box");

                player.move(roll);

                System.out.println("The category is " + Category.currentCategory(player).getValue());
                questionBoard.askQuestion(player);
            } else {
                player.printCommand("%s is not getting out of the penalty box");
                player.gettingOutOfPenaltyBox(false);
            }

        } else {

            player.move(roll);
            System.out.println("The category is " + Category.currentCategory(player).getValue());
            questionBoard.askQuestion(player);
        }
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
