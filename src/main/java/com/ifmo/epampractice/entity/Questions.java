package com.ifmo.epampractice.entity;

import com.ifmo.epampractice.enums.QuestionType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Questions {
    private int id;
    private QuestionType questionType;
    private String title;
    private String image;
    private String questionText;
    private int testId;
    private List<Answers> answersList;

    public Questions() {
        this.answersList = new ArrayList<>();
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(final int testId) {
        this.testId = testId;
    }

    public List<Answers> getAnswersList() {
        return answersList;
    }

    public void setAnswersList(final List<Answers> answersList) {
        this.answersList = answersList;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(final QuestionType questionType) {
        this.questionType = questionType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(final String image) {
        this.image = image;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(final String questionText) {
        this.questionText = questionText;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Questions questions = (Questions) o;
        return id == questions.id
                && testId == questions.testId
                && questionType == questions.questionType
                && Objects.equals(title, questions.title)
                && Objects.equals(image, questions.image)
                && Objects.equals(questionText, questions.questionText)
                && Objects.equals(answersList, questions.answersList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, questionType, title, image, questionText, testId, answersList);
    }

    @Override
    public String toString() {
        return "Questions{"
                + "id=" + id
                + ", questionType=" + questionType
                + ", title='" + title + '\''
                + ", image='" + image + '\''
                + ", questionText='" + questionText + '\''
                + ", testId=" + testId
                + ", answersList=" + answersList
                + '}';
    }
}
