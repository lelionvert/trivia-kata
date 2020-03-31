package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.function.Consumer;

public class Players {

    private ArrayList<Player> playerList;
    private int[] places;
    private boolean[] inPenaltyBox;


    public Players() {
        this.places = new int[6];
        this.inPenaltyBox = new boolean[6];
        this.playerList = new ArrayList<>();
    }

    boolean didPlayerWin(int currentPlayer) {

        int playerPurses = playerList.get(currentPlayer).purses();
        return !(playerPurses == 6);
    }

    boolean isDidPlayerWin(int currentPlayer, boolean isGettingOutOfPenaltyBox, boolean inPenaltyBox) {
        boolean didPlayerWin;
        Player player = playerList.get(currentPlayer);
        if (inPenaltyBox) {
            didPlayerWin = true;

            if (isGettingOutOfPenaltyBox) {
                System.out.println("Answer was correct!!!!");
                playerList.get(currentPlayer).earnCoin();
                System.out.println(player + " now has " + player.purses() + " Gold Coins.");

                didPlayerWin = this.didPlayerWin(currentPlayer);
            }
        } else {
            System.out.println("Answer was corrent!!!!");
            playerList.get(currentPlayer).earnCoin();
            System.out.println(player + " now has " + player.purses() + " Gold Coins.");

            didPlayerWin = this.didPlayerWin(currentPlayer);
        }
        return didPlayerWin;
    }

    void putPlayerInPenaltyBox(int currentPlayer) {
        inPenaltyBox[currentPlayer] = true;
    }

    boolean isInPenaltyBox(int currentPlayer) {
        return inPenaltyBox[currentPlayer];
    }

    int getPlayerNewPlace(int roll, int currentPlayer) {
        int oldPlace = getPlace(currentPlayer);
        int playerNewPlace = calculateNewPlace(roll, oldPlace);

        updatePlace(currentPlayer, playerNewPlace);

        return playerNewPlace;
    }

    private int getPlace(int currentPlayer) {
        return playerList.get(currentPlayer).place();
    }

    private void updatePlace(int currentPlayer, int playerNewPlace) {
        playerList.get(currentPlayer).updatePlace(playerNewPlace);
    }

    int calculateNewPlace(int roll, int currentPlace) {
        int playerNewPlace = currentPlace + roll;
        if (playerNewPlace > 11) {
            playerNewPlace = playerNewPlace - 12;
        }
        return playerNewPlace;
    }

    int getIndexCategory(int currentPlayer, int categoriesCount) {
        int place = getPlace(currentPlayer);

        return place % categoriesCount;
    }

    void add(String playerName) {
        boolean initPenaltyPlayer = false;
        playerList.add(new Player(playerName));
        places[countPlayers()] = 0;
        inPenaltyBox[countPlayers()] = initPenaltyPlayer;

    }

    Player currentPlayerName(int currentPlayer) {
        return playerList.get(currentPlayer);
    }

    int countPlayers() {
        return playerList.size();
    }

    void print(Consumer<String> consumer, String format, int currentPlayer) {
        Player currentString = currentPlayerName(currentPlayer);
        consumer.accept(String.format(format, currentString));
    }
}
