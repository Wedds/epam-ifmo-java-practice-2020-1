package com.ifmo.epampractice.enums;

public enum Roles {
    ADMIN("admin"),
    MODERATOR("moderator"),
    TEACHER("teacher"),
    STUDENT("student");

    private String value;

    Roles(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
