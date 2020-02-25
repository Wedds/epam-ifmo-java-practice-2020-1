package com.ifmo.epampractice.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class Attempts {
    private int id;
    private int testId;
    private int userId;
    private int score;
    private Timestamp passingDate;

    public int getTestId() {
        return testId;
    }

    public void setTestId(final int testId) {
        this.testId = testId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(final int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(final Integer score) {
        this.score = score;
    }

    public Timestamp getPassingDate() {
        return passingDate;
    }

    public void setPassingDate(final Timestamp passingDate) {
        this.passingDate = passingDate;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Attempts attempts = (Attempts) o;
        return id == attempts.id
                && testId == attempts.testId
                && userId == attempts.userId
                && score == attempts.score
                && passingDate.toLocalDateTime().equals(attempts.passingDate.toLocalDateTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, testId, userId, score, passingDate);
    }

    @Override
    public String toString() {
        return "Attempts{"
                + "id=" + id
                + ", testId=" + testId
                + ", userId=" + userId
                + ", score=" + score
                + ", passingDate=" + passingDate
                + '}';
    }
}
