package com.ifmo.epampractice.entity;

import java.util.Objects;

public class Questions {

    private int id;
    private String question_type;
    private String phrase;
    private String rightAnswer;
    private String structure;
    private Tests test;
    private TestAttemptAnswers testAttemptAnswer;

    public Questions() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return question_type;
    }

    public void setType(String type) {
        this.question_type = type;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public Tests getTest() {
        return test;
    }

    public void setTest(Tests test) {
        this.test = test;
    }

    public TestAttemptAnswers getTestAttemptAnswer() {
        return testAttemptAnswer;
    }

    public void setTestAttemptAnswer(TestAttemptAnswers testAttemptAnswer) {
        this.testAttemptAnswer = testAttemptAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Questions questions = (Questions) o;
        return id == questions.id &&
                Objects.equals(question_type, questions.question_type) &&
                Objects.equals(phrase, questions.phrase) &&
                Objects.equals(rightAnswer, questions.rightAnswer) &&
                Objects.equals(structure, questions.structure) &&
                Objects.equals(test, questions.test) &&
                Objects.equals(testAttemptAnswer, questions.testAttemptAnswer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question_type, phrase, rightAnswer, structure, test, testAttemptAnswer);
    }

    @Override
    public String toString() {
        return "Questions{" +
                "id=" + id +
                ", type='" + question_type + '\'' +
                ", phrase='" + phrase + '\'' +
                ", rightAnswer='" + rightAnswer + '\'' +
                ", structure='" + structure + '\'' +
                ", test=" + test +
                ", testAttemptAnswer=" + testAttemptAnswer +
                '}';
    }
}
