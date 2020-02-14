package com.ifmo.epampractice.entity;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class Groups {
    private int id;
    private String name;
    private Date createdAt;
    private List<Users> usersList;
    private List<Tests> testsList;

    public List<Users> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }

    public List<Tests> getTestsList() {
        return testsList;
    }

    public void setTestsList(List<Tests> testsList) {
        this.testsList = testsList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Groups groups = (Groups) o;
        return id == groups.id &&
                name.equals(groups.name) &&
                createdAt.equals(groups.createdAt) &&
                Objects.equals(usersList, groups.usersList) &&
                Objects.equals(testsList, groups.testsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, createdAt, usersList, testsList);
    }

    @Override
    public String toString() {
        return "Groups{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", usersList=" + usersList +
                ", testsList=" + testsList +
                '}';
    }


}
