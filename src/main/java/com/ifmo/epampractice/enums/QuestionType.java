package com.ifmo.epampractice.enums;

public enum QuestionType {
    CHECKBOX,
    RADIOBUTTON;

    public static QuestionType getQuestionTypeFromString(final String questionTypeString) {
        if (questionTypeString.equals("checkbox")) {
            return QuestionType.CHECKBOX;
        } else {
            return QuestionType.RADIOBUTTON;
        }
    }
}




