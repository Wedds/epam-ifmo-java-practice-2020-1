package com.ifmo.epampractice.enums;

public enum QuestionType {
    CHECKBOX("checkbox"),
    RADIOBUTTON("radiobutton");

    private String value;

    QuestionType(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}


