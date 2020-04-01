package com.adaptionsoft.games.uglytrivia;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;

public class QuestionBoard {
    private static Map<Category, LinkedList<String>> questionsByCategory;

    public QuestionBoard(Map<Category, LinkedList<String>> questionsByCategory) {

        this.questionsByCategory = questionsByCategory;
        initializeQuestionCategories(questionsByCategory);
        fillQuestionCategories(questionsByCategory);
    }

    public static void fillQuestionCategories(Map<Category, LinkedList<String>> questionsByCategory) {
        QuestionBoard.questionsByCategory = questionsByCategory;
        for (int i = 0; i < 50; i++) {
            String questionIndex = " Question " + i;
            Arrays.stream(Category.values())
                    .forEach(category -> questionsByCategory
                            .get(category)
                            .add(category.getValue() + questionIndex));
        }
    }

    public static void initializeQuestionCategories(Map<Category, LinkedList<String>> questionsByCategory) {
        for (Category category : Category.values()) {
            questionsByCategory.put(category, new LinkedList<>());
        }
    }
}
