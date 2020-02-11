package com.ifmo.epampractice.entity;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class TestAttempts {

    private int id;
    private Integer score;
    private Date passingDate;
    private Users user;
    private List<TestAttemptAnswers> testAttemptAnswers;
    private Tests test;

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

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<TestAttemptAnswers> getTestAttemptAnswers() {
        return testAttemptAnswers;
    }

    public void setTestAttemptAnswers(List<TestAttemptAnswers> testAttemptAnswers) {
        this.testAttemptAnswers = testAttemptAnswers;
    }

    public Tests getTest() {
        return test;
    }

    public void setTest(Tests test) {
        this.test = test;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestAttempts that = (TestAttempts) o;
        return id == that.id &&
                Objects.equals(score, that.score) &&
                Objects.equals(passingDate, that.passingDate) &&
                Objects.equals(user, that.user) &&
                Objects.equals(testAttemptAnswers, that.testAttemptAnswers) &&
                Objects.equals(test, that.test);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, score, passingDate, user, testAttemptAnswers, test);
    }


    @Override
    public String toString() {
        return "TestAttempts{" +
                "id=" + id +
                ", score=" + score +
                ", passingDate=" + passingDate +
                ", user=" + user +
                ", testAttemptAnswers=" + testAttemptAnswers +
                ", test=" + test +
                '}';
    }
}
