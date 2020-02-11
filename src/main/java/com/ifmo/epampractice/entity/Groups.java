package com.ifmo.epampractice.entity;

import java.util.List;
import java.util.Objects;

public class Groups {

    private int id;
    private String name;
    private boolean is_open;
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
        return is_open;
    }

    public void setIs_open(boolean is_open) {
        this.is_open = is_open;
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
        Groups groups = (Groups) o;
        return id == groups.id &&
                is_open == groups.is_open &&
                Objects.equals(name, groups.name) &&
                Objects.equals(usersList, groups.usersList) &&
                Objects.equals(groupsTests, groups.groupsTests);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, is_open, usersList, groupsTests);
    }

    @Override
    public String toString() {
        return "Groups{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", is_open=" + is_open +
                ", usersList=" + usersList +
                ", groupsTests=" + groupsTests +
                '}';
    }
}
