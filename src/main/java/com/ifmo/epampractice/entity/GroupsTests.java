package com.ifmo.epampractice.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

public class GroupsTests {
    private int testId;
    private int groupId;
    private Boolean isNecessary;
    private Integer maxAttempts;
    private Date deadline;
    private Integer timeLimit;


    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public Boolean getNecessary() {
        return isNecessary;
    }

    public void setNecessary(Boolean necessary) {
        isNecessary = necessary;
    }

    public Integer getMaxAttempts() {
        return maxAttempts;
    }

    public void setMaxAttempts(Integer maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    @Basic
    @Column(name = "deadline")
    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    @Basic
    @Column(name = "time_limit")
    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupsTests that = (GroupsTests) o;
        return testId == that.testId &&
                groupId == that.groupId &&
                isNecessary.equals(that.isNecessary) &&
                maxAttempts.equals(that.maxAttempts) &&
                deadline.equals(that.deadline) &&
                timeLimit.equals(that.timeLimit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(testId, groupId, isNecessary, maxAttempts, deadline, timeLimit);
    }

    @Override
    public String toString() {
        return "GroupsTests{" +
                "testId=" + testId +
                ", groupId=" + groupId +
                ", isNecessary=" + isNecessary +
                ", maxAttempts=" + maxAttempts +
                ", deadline=" + deadline +
                ", timeLimit=" + timeLimit +
                '}';
    }
}
