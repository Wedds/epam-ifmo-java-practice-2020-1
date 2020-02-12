package com.ifmo.epampractice.entity;

import java.util.List;
import java.util.Objects;

public class Questions {

    private int id;
    private String question_type;
    private String phrase;
    private String rightAnswer;
    private String structure;
    private int test_id;
    private List<TestAttemptAnswers> testAttemptAnswersList;

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

    public int getTestId() {
        return test_id;
    }

    public void setTestId(int test_id) {
        this.test_id = test_id;
    }

    public List<TestAttemptAnswers> getTestAttemptAnswersList() {
        return testAttemptAnswersList;
    }

    public void setTestAttemptAnswersList(List<TestAttemptAnswers> testAttemptAnswersList) {
        this.testAttemptAnswersList = testAttemptAnswersList;
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
                Objects.equals(test_id, questions.test_id) &&
                Objects.equals(testAttemptAnswersList, questions.testAttemptAnswersList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question_type, phrase, rightAnswer, structure, test_id, testAttemptAnswersList);
    }

    @Override
    public String toString() {
        return "Questions{" +
                "id=" + id +
                ", question_type='" + question_type + '\'' +
                ", phrase='" + phrase + '\'' +
                ", rightAnswer='" + rightAnswer + '\'' +
                ", structure='" + structure + '\'' +
                ", test_id=" + test_id +
                //", testAttemptAnswersList=" + testAttemptAnswersList +
                '}';
    }
}
