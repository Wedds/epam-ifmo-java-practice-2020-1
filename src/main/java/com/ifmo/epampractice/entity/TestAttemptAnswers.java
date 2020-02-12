package com.ifmo.epampractice.entity;

import java.sql.Date;
import java.util.Objects;

public class TestAttemptAnswers {

    private int id;
    private int score;
    private String answer;
    private int question_id;
    private int test_attempt_id;

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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getQuestionId() {
        return question_id;
    }

    public void setQuestionId(int question_id) {
        this.question_id = question_id;
    }

    public int getTestAttemptId() {
        return test_attempt_id;
    }

    public void setTestAttemptId(int test_attempt_id) {
        this.test_attempt_id = test_attempt_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestAttemptAnswers that = (TestAttemptAnswers) o;
        return id == that.id &&
                score == that.score &&
                Objects.equals(answer, that.answer) &&
                Objects.equals(question_id, that.question_id) &&
                Objects.equals(test_attempt_id, that.test_attempt_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, score, answer, question_id, test_attempt_id);
    }

    @Override
    public String toString() {
        return "TestAttemptAnswers{" +
                "id=" + id +
                ", score=" + score +
                ", answer=" + answer +
                ", question_id=" + question_id +
                ", test_attempt_id=" + test_attempt_id +
                '}';
    }
}
