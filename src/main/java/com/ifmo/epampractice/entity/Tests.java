package com.ifmo.epampractice.entity;

import java.sql.Date;
import java.util.ArrayList;
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
    private int groupId;
    private Boolean isNecessary;
    private int maxAttempts;
    private Date deadline;
    private int timeLimit;

    public Tests() {
        this.attemptsList = new ArrayList<Attempts>();
        this.questionsList = new ArrayList<Questions>();
        this.groupsList = new ArrayList<Groups>();
    }

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

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public Boolean getIsNecessary() {
        return isNecessary;
    }

    public void setIsNecessary(Boolean necessary) {
        isNecessary = necessary;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public void setMaxAttempts(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
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
                maxAttempts == tests.maxAttempts &&
                timeLimit == tests.timeLimit &&
                Objects.equals(title, tests.title) &&
                Objects.equals(description, tests.description) &&
                Objects.equals(isRandom, tests.isRandom) &&
                Objects.equals(createdAt, tests.createdAt) &&
                Objects.equals(maxPoints, tests.maxPoints) &&
                Objects.equals(creatorId, tests.creatorId) &&
                Objects.equals(attemptsList, tests.attemptsList) &&
                Objects.equals(questionsList, tests.questionsList) &&
                Objects.equals(groupsList, tests.groupsList) &&
                Objects.equals(groupId, tests.groupId) &&
                Objects.equals(isNecessary, tests.isNecessary) &&
                Objects.equals(deadline, tests.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subjectId, title, description, isRandom, createdAt,
                maxPoints, creatorId, attemptsList, questionsList, groupsList, groupId,
                isNecessary, maxAttempts, deadline, timeLimit);
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
                ", groupId=" + groupId +
                ", isNecessary=" + isNecessary +
                ", maxAttempts=" + maxAttempts +
                ", deadline=" + deadline +
                ", timeLimit=" + timeLimit +
                '}';
    }
}
