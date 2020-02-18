package com.ifmo.epampractice.entity;

import java.util.List;
import java.util.Objects;

public class Questions {
    private int id;
    private Object questionType;
    private String title;
    private String image;
    private String questionText;
    private int testId;

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    private List<Answers> answersList;

    public List<Answers> getAnswersList() {
        return answersList;
    }

    public void setAnswersList(List<Answers> answersList) {
        this.answersList = answersList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Object questionType) {
        this.questionType = questionType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Questions questions = (Questions) o;
        return id == questions.id &&
                testId == questions.testId &&
                questionType.equals(questions.questionType) &&
                title.equals(questions.title) &&
                image.equals(questions.image) &&
                questionText.equals(questions.questionText) &&
                answersList.equals(questions.answersList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, questionType, title, image, questionText, testId, answersList);
    }

    @Override
    public String toString() {
        return "Questions{" +
                "id=" + id +
                ", questionType=" + questionType +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", questionText='" + questionText + '\'' +
                ", testId=" + testId +
                ", answersList=" + answersList +
                '}';
    }
}
