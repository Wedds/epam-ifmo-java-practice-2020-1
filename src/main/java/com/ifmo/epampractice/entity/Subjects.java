package com.ifmo.epampractice.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Subjects {
    private int id;
    private String name;
    private List<Tests> testsList;

    public Subjects() {
        this.testsList = new ArrayList<Tests>();
    }

    public List<Tests> getTestsList() {
        return testsList;
    }

    public void setTestsList(final List<Tests> testsList) {
        this.testsList = testsList;
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Subjects subjects = (Subjects) o;
        return id == subjects.id &&
                name.equals(subjects.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
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
