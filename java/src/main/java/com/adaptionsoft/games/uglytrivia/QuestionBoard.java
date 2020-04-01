package com.adaptionsoft.games.uglytrivia;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class QuestionBoard {
    private Map<Category, LinkedList<String>> questionsByCategory;

    public QuestionBoard() {

        this.questionsByCategory = new HashMap<>();
        initializeQuestionsCategory();
    }

    void askQuestion(Player player) {
        Category category = Category.currentCategory(player);
        System.out.println(this.questionsByCategory.get(category).remove());
    }

    void initializeQuestionsCategory() {

        for (Category category : Category.values()) {
            this.questionsByCategory.put(category, new LinkedList<>());
        }

        for (int i = 0; i < 50; i++) {
            String questionIndex = " Question " + i;
            Arrays.stream(Category.values())
                    .forEach(category -> this.questionsByCategory.get(category).add(category.getValue() + questionIndex));
        }
    }
}
