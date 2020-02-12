package com.ifmo.epampractice.entity;

import java.util.List;
import java.util.Objects;

public class Tests {

    private int id;
    private String title;
    private boolean isRandom;
    private int creator_id;
    private int subject_id;
    private List<TestAttempts> testAttemptsList;

    private List<Questions> questionsList;
    private List<GroupsTests> groupsTestsList;

    public Tests() {
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

    public boolean isRandom() {
        return isRandom;
    }

    public void setRandom(boolean random) {
        isRandom = random;
    }

    public int getCreatorId() {
        return creator_id;
    }

    public void setCreatorId(int creator_id) {
        this.creator_id = creator_id;
    }

    public List<TestAttempts> getTestAttemptsList() {
        return testAttemptsList;
    }

    public void setTestAttemptsList(List<TestAttempts> testAttemptsList) {
        this.testAttemptsList = testAttemptsList;
    }

    public int getSubjectId() {
        return subject_id;
    }

    public void setSubjectId(int subject_id) {
        this.subject_id = subject_id;
    }

    public List<Questions> getQuestionsList() {
        return questionsList;
    }

    public void setQuestionsList(List<Questions> questionsList) {
        this.questionsList = questionsList;
    }

    public List<GroupsTests> getGroupsTestsList() {
        return groupsTestsList;
    }

    public void setGroupsTestsList(List<GroupsTests> groupsTestsList) {
        this.groupsTestsList = groupsTestsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tests tests = (Tests) o;
        return id == tests.id &&
                isRandom == tests.isRandom &&
                Objects.equals(title, tests.title) &&
                Objects.equals(creator_id, tests.creator_id) &&
                Objects.equals(testAttemptsList, tests.testAttemptsList) &&
                Objects.equals(subject_id, tests.subject_id) &&
                Objects.equals(questionsList, tests.questionsList) &&
                Objects.equals(groupsTestsList, tests.groupsTestsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, isRandom, creator_id, testAttemptsList, subject_id, questionsList, groupsTestsList);
    }

    @Override
    public String toString() {
        return "Tests{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isRandom=" + isRandom +
                ", creator_id=" + creator_id +
                //", testAttempt=" + testAttemptsList +
                ", subject_id=" + subject_id +
                //", questionsList=" + questionsList +
                //", groupsTestsList=" + groupsTestsList +
                '}';
    }
}
