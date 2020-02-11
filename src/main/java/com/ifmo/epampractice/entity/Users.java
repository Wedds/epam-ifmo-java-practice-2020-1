package com.ifmo.epampractice.entity;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class Users {

    private int id;
    private String role_type;
    private String email;
    private String hash;
    private String salt;
    private String first_name;
    private String last_name;
    private String middle_name;
    private Date birth_date;
    private String work_title;
    private Date created_at;
    private List<Groups>groupsList;
    private List<TestAttempts>testAttemptsList;
    private List<Tests>createdTests;

    public Users() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole_type() {
        return role_type;
    }

    public void setRole_type(String role_type) {
        this.role_type = role_type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public String getWork_title() {
        return work_title;
    }

    public void setWork_title(String work_title) {
        this.work_title = work_title;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public List<Groups> getGroupsList() {
        return groupsList;
    }

    public void setGroupsList(List<Groups> groupsList) {
        this.groupsList = groupsList;
    }

    public List<TestAttempts> getTestAttemptsList() {
        return testAttemptsList;
    }

    public void setTestAttemptsList(List<TestAttempts> testAttemptsList) {
        this.testAttemptsList = testAttemptsList;
    }

    public List<Tests> getCreatedTests() {
        return createdTests;
    }

    public void setCreatedTests(List<Tests> createdTests) {
        this.createdTests = createdTests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return id == users.id &&
                Objects.equals(role_type, users.role_type) &&
                Objects.equals(email, users.email) &&
                Objects.equals(hash, users.hash) &&
                Objects.equals(salt, users.salt) &&
                Objects.equals(first_name, users.first_name) &&
                Objects.equals(last_name, users.last_name) &&
                Objects.equals(middle_name, users.middle_name) &&
                Objects.equals(birth_date, users.birth_date) &&
                Objects.equals(work_title, users.work_title) &&
                Objects.equals(created_at, users.created_at) &&
                Objects.equals(groupsList, users.groupsList) &&
                Objects.equals(testAttemptsList, users.testAttemptsList) &&
                Objects.equals(createdTests, users.createdTests);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role_type, email, hash, salt, first_name, last_name, middle_name, birth_date, work_title, created_at, groupsList, testAttemptsList, createdTests);
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", role_type='" + role_type + '\'' +
                ", email='" + email + '\'' +
                ", hash='" + hash + '\'' +
                ", salt='" + salt + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", middle_name='" + middle_name + '\'' +
                ", birth_date=" + birth_date +
                ", work_title='" + work_title + '\'' +
                ", created_at=" + created_at +
                ", groupsList=" + groupsList +
                ", testAttemptsList=" + testAttemptsList +
                ", createdTests=" + createdTests +
                '}';
    }
}
