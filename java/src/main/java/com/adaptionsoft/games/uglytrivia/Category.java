package com.adaptionsoft.games.uglytrivia;

public enum Category {
    POP("Pop"),
    SCIENCE("Science"),
    SPORTS("Sports"),
    ROCK("Rock");

    private String value;

    static Category currentCategory(Player player) {
        int indexCategory = player.place() % 4;
        return values()[indexCategory];
    }

    public String getValue() {
        return value;
    }

    Category(String value) {
        this.value = value;
    }
}