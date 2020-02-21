package com.ifmo.epampractice.entity;

import java.util.Objects;

public class Answers {
    private int id;
    private int questionId;
    private String image;
    private String answerText;
    private Boolean isCorrect;
    private Integer points;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Boolean correct) {
        isCorrect = correct;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Answers answers = (Answers) o;
        return id == answers.id &&
                questionId == answers.questionId &&
                Objects.equals(image, answers.image) &&
                Objects.equals(answerText, answers.answerText) &&
                Objects.equals(isCorrect, answers.isCorrect) &&
                Objects.equals(points, answers.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, questionId, image, answerText, isCorrect, points);
    }

    @Override
    public String toString() {
        return "Answers{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", image='" + image + '\'' +
                ", answerText='" + answerText + '\'' +
                ", isCorrect=" + isCorrect +
                ", points=" + points +
                '}';
    }
}
