package com.ifmo.epampractice.entity;

import java.util.Objects;

public class Answers {
    private int id;
    private int questionId;
    private String image;
    private String answerText;
    private Boolean isCorrect;
    private int points;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(final int questionId) {
        this.questionId = questionId;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(final String image) {
        this.image = image;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(final String answerText) {
        this.answerText = answerText;
    }

    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(final Boolean correct) {
        isCorrect = correct;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(final Integer points) {
        this.points = points;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Answers answers = (Answers) o;
        return id == answers.id
                && questionId == answers.questionId
                && Objects.equals(image, answers.image)
                && Objects.equals(answerText, answers.answerText)
                && Objects.equals(isCorrect, answers.isCorrect)
                && Objects.equals(points, answers.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, questionId, image, answerText, isCorrect, points);
    }

    @Override
    public String toString() {
        return "Answers{"
                + "id=" + id
                + ", questionId=" + questionId
                + ", image='" + image + '\''
                + ", answerText='" + answerText + '\''
                + ", isCorrect=" + isCorrect
                + ", points=" + points
                + '}';
    }
}
