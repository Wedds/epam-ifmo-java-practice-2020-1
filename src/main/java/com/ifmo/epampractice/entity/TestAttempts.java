package com.ifmo.epampractice.entity;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class TestAttempts {

    private int id;
    private Integer score;
    private Date passingDate;
    private int user_id;
    private int test_id;
    private List<TestAttemptAnswers> testAttemptAnswers;

    public TestAttempts() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getPassingDate() {
        return passingDate;
    }

    public void setPassingDate(Date passingDate) {
        this.passingDate = passingDate;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user) {
        this.user_id = user_id;
    }

    public List<TestAttemptAnswers> getTestAttemptAnswers() {
        return testAttemptAnswers;
    }

    public void setTestAttemptAnswers(List<TestAttemptAnswers> testAttemptAnswers) {
        this.testAttemptAnswers = testAttemptAnswers;
    }

    public int getTestId() {
        return test_id;
    }

    public void setTestId(int test_id) {
        this.test_id = test_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestAttempts that = (TestAttempts) o;
        return id == that.id &&
                Objects.equals(score, that.score) &&
                Objects.equals(passingDate, that.passingDate) &&
                Objects.equals(user_id, that.user_id) &&
                Objects.equals(testAttemptAnswers, that.testAttemptAnswers) &&
                Objects.equals(test_id, that.test_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, score, passingDate, user_id, testAttemptAnswers, test_id);
    }


    @Override
    public String toString() {
        return "TestAttempts{" +
                "id=" + id +
                ", score=" + score +
                ", passingDate=" + passingDate +
                ", user_id=" + user_id +
                ", testAttemptAnswers=" + testAttemptAnswers +
                ", test_id=" + test_id +
                '}';
    }
}
