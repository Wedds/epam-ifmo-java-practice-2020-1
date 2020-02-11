package com.ifmo.epampractice.entity;

import java.sql.Date;
import java.util.Objects;

public class GroupsTests {

    private int id;
    private boolean isNeccessary;
    private int maxAttemps;
    private int timeLimit;
    private Date deadline;
    private Groups group;
    private Tests test;

    public GroupsTests() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isNeccessary() {
        return isNeccessary;
    }

    public void setNeccessary(boolean neccessary) {
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

    public Groups getGroup() {
        return group;
    }

    public void setGroup(Groups group) {
        this.group = group;
    }

    public Tests getTest() {
        return test;
    }

    public void setTest(Tests test) {
        this.test = test;
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
                Objects.equals(group, that.group) &&
                Objects.equals(test, that.test);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isNeccessary, maxAttemps, timeLimit, deadline, group, test);
    }

    @Override
    public String toString() {
        return "GroupsTests{" +
                "id=" + id +
                ", isNeccessary=" + isNeccessary +
                ", maxAttemps=" + maxAttemps +
                ", timeLimit=" + timeLimit +
                ", deadline=" + deadline +
                ", group=" + group +
                ", test=" + test +
                '}';
    }
}
