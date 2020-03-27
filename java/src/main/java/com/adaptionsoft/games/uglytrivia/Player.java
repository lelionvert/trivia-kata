package com.adaptionsoft.games.uglytrivia;

public class Player {

    static int calculateNewPlace(int roll, int currentPlace) {
        int playerNewPlace = currentPlace + roll;
        if (playerNewPlace > 11) {
            playerNewPlace = playerNewPlace - 12;
        }
        return playerNewPlace;
    }

    static String currentCategory(int currentPlayer, int[] places) {

        switch (places[currentPlayer] % 4) {
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

    static void movePlayer(Game game, int roll) {
        int playerNewPlace = calculateNewPlace(roll, game.places[game.currentPlayer]);

        game.places[game.currentPlayer] = playerNewPlace;
        System.out.println(game.players.get(game.currentPlayer)
                + "'s new location is "
                + playerNewPlace);
        System.out.println("The category is " + currentCategory(game.currentPlayer, game.places));
    }
}
