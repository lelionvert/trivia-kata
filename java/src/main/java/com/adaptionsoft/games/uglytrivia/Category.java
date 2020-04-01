package com.adaptionsoft.games.uglytrivia;

public enum Category {
    POP("Pop"),
    SCIENCE("Science"),
    SPORTS("Sports"),
    ROCK("Rock");

    private static final int CATEGORY_COUNT = Category.values().length;
    private String value;

    static Category currentCategory(Player player) {
        int indexCategory = player.place() % CATEGORY_COUNT;
        return values()[indexCategory];
    }

    public String getValue() {
        return value;
    }

    Category(String value) {
        this.value = value;
    }
}