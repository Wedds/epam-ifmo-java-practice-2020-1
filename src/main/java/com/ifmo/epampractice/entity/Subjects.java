package com.ifmo.epampractice.entity;

import java.util.List;
import java.util.Objects;

public class Subjects {

    private int id;
    private String name;
    private List<Tests>testsList;

    public Subjects() {
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

    public List<Tests> getTestsList() {
        return testsList;
    }

    public void setTestsList(List<Tests> testsList) {
        this.testsList = testsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subjects subjects = (Subjects) o;
        return id == subjects.id &&
                Objects.equals(name, subjects.name) &&
                Objects.equals(testsList, subjects.testsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, testsList);
    }

    @Override
    public String toString() {
        return "Subjects{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", testsList=" + testsList +
                '}';
    }
}