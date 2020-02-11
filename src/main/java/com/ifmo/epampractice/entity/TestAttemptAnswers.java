package com.ifmo.epampractice.entity;

import java.sql.Date;
import java.util.Objects;

public class TestAttemptAnswers {

    private int id;
    private int score;
    private Date passingDate;
    private Questions question;
    private Tests test;

    public TestAttemptAnswers() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getPassingDate() {
        return passingDate;
    }

    public void setPassingDate(Date passingDate) {
        this.passingDate = passingDate;
    }

    public Questions getQuestion() {
        return question;
    }

    public void setQuestion(Questions question) {
        this.question = question;
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
        TestAttemptAnswers that = (TestAttemptAnswers) o;
        return id == that.id &&
                score == that.score &&
                Objects.equals(passingDate, that.passingDate) &&
                Objects.equals(question, that.question) &&
                Objects.equals(test, that.test);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, score, passingDate, question, test);
    }

    @Override
    public String toString() {
        return "TestAttemptAnswers{" +
                "id=" + id +
                ", score=" + score +
                ", passingDate=" + passingDate +
                ", question=" + question +
                ", test=" + test +
                '}';
    }
}
