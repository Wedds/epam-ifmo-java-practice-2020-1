package com.ifmo.epampractice.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Subjects {
<<<<<<< HEAD
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Subjects {
=======
>>>>>>> Change DAO & entity Subjects. Again.
    private int id;
    private String name;
    private List<Tests> testsList;

    public Subjects() {
        this.testsList = new ArrayList<Tests>();
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

    @Override
    public boolean equals(Object o) {
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
<<<<<<< HEAD
}
=======
}
>>>>>>> Change DAO & entity Subjects. Again.
