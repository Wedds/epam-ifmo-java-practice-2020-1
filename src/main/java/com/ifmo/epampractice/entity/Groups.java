package com.ifmo.epampractice.entity;

import java.util.List;
import java.util.Objects;


public class Groups {

    private int id;
    private String name;
    private boolean isOpen;
    private List<Users>usersList;
    private List<GroupsTests>groupsTests;

    public Groups() {
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

    public boolean isIs_open() {
        return isOpen;
    }

    public void setIs_open(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public List<Users> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }

    public List<GroupsTests> getGroupsTests() {
        return groupsTests;
    }

    public void setGroupsTests(List<GroupsTests> groupsTests) {
        this.groupsTests = groupsTests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Groups that = (Groups) o;
        return id == that.id &&
                isOpen == that.isOpen &&
                Objects.equals(name, that.name) &&
                Objects.equals(usersList, that.usersList) &&
                Objects.equals(groupsTests, that.groupsTests);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isOpen, usersList, groupsTests);
    }

    @Override
    public String toString() {
        return "Groups{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", is_open=" + isOpen +
                ", usersList=" + usersList +
                ", groupsTests=" + groupsTests +
                '}';
    }
}
