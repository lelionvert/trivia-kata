package com.adaptionsoft.games.uglytrivia;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;

public class QuestionBoard {
    private Map<Category, LinkedList<String>> questionsByCategory;

    public QuestionBoard(Map<Category, LinkedList<String>> questionsByCategory) {

        this.questionsByCategory = questionsByCategory;
    }

    public void fillQuestionCategories() {
        for (int i = 0; i < 50; i++) {
            String questionIndex = " Question " + i;
            Arrays.stream(Category.values())
                    .forEach(category -> this.questionsByCategory
                            .get(category)
                            .add(category.getValue() + questionIndex));
        }
    }

    public void initializeQuestionCategories() {
        for (Category category : Category.values()) {
            this.questionsByCategory.put(category, new LinkedList<>());
        }
    }

    void askQuestion(Player player) {
        Category category = Category.currentCategory(player);
        System.out.println(this.questionsByCategory.get(category).remove());
    }
}
