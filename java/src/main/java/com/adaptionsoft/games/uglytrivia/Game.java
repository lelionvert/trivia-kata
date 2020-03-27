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

        if (isPlayerInPenaltyBox()) {
            if (roll % 2 != 0) {

                isGettingOutOfPenaltyBox = true;
                System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");

                Player.movePlayer(this, roll);
                askQuestion();
            } else {
                System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {

            Player.movePlayer(this, roll);
            askQuestion();
        }

    }

    private void askQuestion() {
        if (Player.currentCategory(currentPlayer, places).equals("Pop"))
            System.out.println(popQuestions.removeFirst());
        if (Player.currentCategory(currentPlayer, places).equals("Science"))
            System.out.println(scienceQuestions.removeFirst());
        if (Player.currentCategory(currentPlayer, places).equals("Sports"))
            System.out.println(sportsQuestions.removeFirst());
        if (Player.currentCategory(currentPlayer, places).equals("Rock"))
            System.out.println(rockQuestions.removeFirst());
    }


    public boolean wasCorrectlyAnswered() {
        boolean didPlayerWin;
        if (isPlayerInPenaltyBox()) {

            didPlayerWin = true;

            if (isGettingOutOfPenaltyBox) {
                System.out.println("Answer was correct!!!!");
                earnCoin();

                didPlayerWin = didPlayerWin();
            }
        } else {
            System.out.println("Answer was corrent!!!!");
            earnCoin();

            didPlayerWin = didPlayerWin();
        }
        getNextPlayer();
        return didPlayerWin;
    }

	private boolean isPlayerInPenaltyBox() {
		return inPenaltyBox[currentPlayer];
	}

	private void earnCoin() {
        purses[currentPlayer]++;
        System.out.println(players.get(currentPlayer)
                + " now has "
                + purses[currentPlayer]
                + " Gold Coins.");
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

    private boolean didPlayerWin() {
        return !(purses[currentPlayer] == 6);
    }
}
