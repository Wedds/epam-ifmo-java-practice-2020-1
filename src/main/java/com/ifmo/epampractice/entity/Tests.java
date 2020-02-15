package com.ifmo.epampractice.entity;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class Tests {
    private int id;
    private int subjectId;
    private String title;
    private String description;
    private Boolean isRandom;
    private Date createdAt;
    private Integer maxPoints;
    private Integer creatorId;
    private List<Attempts> attemptsList;
    private List<Questions> questionsList;
    private List<Groups> groupsList;

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public List<Questions> getQuestionsList() {
        return questionsList;
    }

    public void setQuestionsList(List<Questions> questionsList) {
        this.questionsList = questionsList;
    }

    public List<Groups> getGroupsList() {
        return groupsList;
    }

    public void setGroupsList(List<Groups> groupsList) {
        this.groupsList = groupsList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsRandom() {
        return isRandom;
    }

    public void setIsRandom(Boolean random) {
        isRandom = random;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(Integer maxPoints) {
        this.maxPoints = maxPoints;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public List<Attempts> getAttemptsList() {
        return attemptsList;
    }

    public void setAttemptsList(List<Attempts> attemptsList) {
        this.attemptsList = attemptsList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tests tests = (Tests) o;
        return id == tests.id &&
                subjectId == tests.subjectId &&
                title.equals(tests.title) &&
                description.equals(tests.description) &&
                isRandom.equals(tests.isRandom) &&
                createdAt.equals(tests.createdAt) &&
                maxPoints.equals(tests.maxPoints) &&
                creatorId.equals(tests.creatorId) &&
                attemptsList.equals(tests.attemptsList) &&
                questionsList.equals(tests.questionsList) &&
                groupsList.equals(tests.groupsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subjectId, title, description, isRandom, createdAt, maxPoints, creatorId, attemptsList, questionsList, groupsList);
    }

    @Override
    public String toString() {
        return "Tests{" +
                "id=" + id +
                ", subjectId=" + subjectId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", isRandom=" + isRandom +
                ", createdAt=" + createdAt +
                ", maxPoints=" + maxPoints +
                ", creatorId=" + creatorId +
                ", attemptsList=" + attemptsList +
                ", questionsList=" + questionsList +
                ", groupsList=" + groupsList +
                '}';
    }
}
