package com.ifmo.epampractice.entity;
import java.util.List;
import java.util.Objects;

public class Tests {

    private int id;
    private String title;
    private boolean isRandom;
    private Users creator;
    private TestAttempts testAttempt;
    private Subjects subject;
    private List<Questions> questionsList;
    private List<GroupsTests>groupsTestsList;

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

    public Users getCreator() {
        return creator;
    }

    public void setCreator(Users creator) {
        this.creator = creator;
    }

    public TestAttempts getTestAttempt() {
        return testAttempt;
    }

    public void setTestAttempt(TestAttempts testAttempt) {
        this.testAttempt = testAttempt;
    }

    public Subjects getSubject() {
        return subject;
    }

    public void setSubject(Subjects subject) {
        this.subject = subject;
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
                Objects.equals(creator, tests.creator) &&
                Objects.equals(testAttempt, tests.testAttempt) &&
                Objects.equals(subject, tests.subject) &&
                Objects.equals(questionsList, tests.questionsList) &&
                Objects.equals(groupsTestsList, tests.groupsTestsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, isRandom, creator, testAttempt, subject, questionsList, groupsTestsList);
    }

    @Override
    public String toString() {
        return "Tests{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isRandom=" + isRandom +
                ", creator=" + creator +
                ", testAttempt=" + testAttempt +
                ", subject=" + subject +
                ", questionsList=" + questionsList +
                ", groupsTestsList=" + groupsTestsList +
                '}';
    }
}
