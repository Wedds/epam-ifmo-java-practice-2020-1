package com.ifmo.epampractice.entity;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class Users {
    private int id;
    private Enum<Roles> roleType;
    private String email;
    private String hash;
    private String salt;
    private String firstName;
    private String lastName;
    private String middleName;
    private Date birthDate;
    private String workTitle;
    private Date createdAt;
    private String avatar;
    private int groupId;
    private List<Tests> testsList;
    private List<Attempts> attemptsList;

    public List<Tests> getTestsList() {
        return testsList;
    }

    public void setTestsList(List<Tests> testsList) {
        this.testsList = testsList;
    }

    public List<Attempts> getAttemptsList() {
        return attemptsList;
    }

    public void setAttemptsList(List<Attempts> attemptsList) {
        this.attemptsList = attemptsList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Enum<Roles> getRoleType() {
        return roleType;
    }

    public void setRoleType(Enum<Roles> roleType) {
        this.roleType = roleType;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getWorkTitle() {
        return workTitle;
    }

    public void setWorkTitle(String workTitle) {
        this.workTitle = workTitle;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Users users = (Users) o;
        return id == users.id &&
                groupId == users.groupId &&
                roleType.equals(users.roleType) &&
                email.equals(users.email) &&
                hash.equals(users.hash) &&
                salt.equals(users.salt) &&
                firstName.equals(users.firstName) &&
                lastName.equals(users.lastName) &&
                middleName.equals(users.middleName) &&
                birthDate.equals(users.birthDate) &&
                workTitle.equals(users.workTitle) &&
                createdAt.equals(users.createdAt) &&
                avatar.equals(users.avatar) &&
                testsList.equals(users.testsList) &&
                attemptsList.equals(users.attemptsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleType, email, hash, salt, firstName, lastName,
                middleName, birthDate, workTitle, createdAt, avatar, groupId, testsList, attemptsList);
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", roleType=" + roleType +
                ", email='" + email + '\'' +
                ", hash='" + hash + '\'' +
                ", salt='" + salt + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", birthDate=" + birthDate +
                ", workTitle='" + workTitle + '\'' +
                ", createdAt=" + createdAt +
                ", avatar='" + avatar + '\'' +
                ", groupId=" + groupId +
                ", testsList=" + testsList +
                ", attemptsList=" + attemptsList +
                '}';
    }

    public Users(){

    }

    public Users(int id) {
        this.setId(id);
    }
}
