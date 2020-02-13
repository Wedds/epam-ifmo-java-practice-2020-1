package com.ifmo.epampractice.entity;

import java.sql.Date;
import java.util.Objects;

public class GroupsTests {

    private int id;
    private boolean isNeccessary;
    private int maxAttemps;
    private int timeLimit;
    private Date deadline;
    private int groupId;
    private int testId;

    public GroupsTests() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIsNeccessary() {
        return isNeccessary;
    }

    public void setIsNeccessary(boolean neccessary) {
        isNeccessary = neccessary;
    }

    public int getMaxAttemps() {
        return maxAttemps;
    }

    public void setMaxAttemps(int maxAttemps) {
        this.maxAttemps = maxAttemps;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public int getGroup_id() {
        return groupId;
    }

    public void setGroup_id(int groupId) {
        this.groupId = groupId;
    }

    public int getTest_id() {
        return testId;
    }

    public void setTest_id(int testId) {
        this.testId = testId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupsTests that = (GroupsTests) o;
        return id == that.id &&
                isNeccessary == that.isNeccessary &&
                maxAttemps == that.maxAttemps &&
                timeLimit == that.timeLimit &&
                Objects.equals(deadline, that.deadline) &&
                Objects.equals(groupId, that.groupId) &&
                Objects.equals(testId, that.testId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isNeccessary, maxAttemps, timeLimit, deadline, groupId, testId);
    }

    @Override
    public String toString() {
        return "GroupsTests{" +
                "id=" + id +
                ", isNeccessary=" + isNeccessary +
                ", maxAttemps=" + maxAttemps +
                ", timeLimit=" + timeLimit +
                ", deadline=" + deadline +
                ", group=" + groupId +
                ", test=" + testId +
                '}';
    }
}
