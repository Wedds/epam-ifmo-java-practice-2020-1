package com.ifmo.epampractice.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;


public class Groups {
    private int id;
    private String name;
    private LocalDateTime createdAt;
    private List<Users> usersList;
    private List<Tests> testsList;

    public Groups() {
        this.usersList = new ArrayList<Users>();
        this.testsList = new ArrayList<Tests>();
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<Users> getUsersList() {
        return usersList;
    }

    public void setUsersList(final List<Users> usersList) {
        this.usersList = usersList;
    }

    public List<Tests> getTestsList() {
        return testsList;
    }

    public void setTestsList(final List<Tests> testsList) {
        this.testsList = testsList;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Groups groups = (Groups) o;
        return id == groups.id
                && name.equals(groups.name)
                && createdAt.equals(groups.createdAt)
                && Objects.equals(usersList, groups.usersList)
                && Objects.equals(testsList, groups.testsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, createdAt, usersList, testsList);
    }

    @Override
    public String toString() {
        return "Groups{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", createdAt=" + createdAt
                + ", usersList=" + usersList
                + ", testsList=" + testsList
                + '}';
    }
}
