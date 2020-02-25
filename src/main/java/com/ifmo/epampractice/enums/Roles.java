package com.ifmo.epampractice.enums;

public enum Roles {
    ADMIN,
    MODERATOR,
    TEACHER,
    STUDENT;

    public static Roles getRoleFromString(final String roleString) {
        switch (roleString) {
            case "admin": {
                return (Roles.ADMIN);
            }
            case "moderator": {
                return (Roles.MODERATOR);
            }
            case "teacher": {
                return (Roles.TEACHER);
            }
            case "student": {
                return (Roles.STUDENT);
            }
            default:
                return (Roles.STUDENT);
        }
    }
}
