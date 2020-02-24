package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.Users;
import com.ifmo.epampractice.enums.Roles;
import com.ifmo.epampractice.service.DatabaseSource;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersDAOTest {
    private static final String DELETE = "DELETE FROM users WHERE id > 0; ALTER SEQUENCE users_id_seq RESTART WITH 1;";
    private static final String INIT_GROUP_QUERY = "INSERT INTO groups VALUES (?,?,?) ON CONFLICT DO NOTHING";
    private static final int GROUP_ID = 1;
    private static final int USER_ID = 1;
    private static final String GROUP_K_1234 = "K1234";
    private static final String DEFAULT_DATE = "2000-01-01";
    private static final String TEST_EMAIL = "test@email.com";
    private static final String TEST_HASH = "34fjbg45jhgb65hnyjy";
    private static final String TEST_SALT = "3jhb6rjhyntfwdsfjhb";
    private static final String FIRST_NAME1 = "Nike";
    private static final String FIRST_NAME2 = "Bob";
    private static final String LAST_NAME = "Borzov";
    private static final String MIDDLE_NAME = "Vladimirovich";
    private static final String TEST_BIRTH_DATE = "1972-05-23";
    private static final String AVATAR = "sdkfmslkdfmlskdfmlskdfm";
    private static final String WORK_TITLE = "Guru";
    private static final UsersDAO USERS_DAO = new UsersDAO();;


    @Test
    public void testCRUD() {
        this.getEmptyList();
        this.addObject();
        this.getAll();
        this.getById();
        this.updateByObject();
        this.removeById();
    }

    @AfterClass
    public static void clearChanges() {
        try (
                Connection connection = DatabaseSource.getInstance().getConnection();
                Statement stmt = connection.createStatement()
        ) {
            stmt.executeUpdate(DELETE);
        } catch (SQLException e) {
            throw new IllegalArgumentException("Cannot clear testing db.", e);
        }
    }

    @Before
    public void initGroups() {
        try (
                Connection connection = DatabaseSource.getInstance().getConnection();
                PreparedStatement stmt = connection.prepareStatement(INIT_GROUP_QUERY)
        ) {
            stmt.setInt(1, GROUP_ID);
            stmt.setString(2, GROUP_K_1234);
            stmt.setDate(3, Date.valueOf(DEFAULT_DATE));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Cannot add record in the table groups.", e);
        }
    }

    private Users createUserObject() {
        Users user = new Users();
        user.setRoleType(Roles.ADMIN);
        user.setEmail(TEST_EMAIL);
        user.setHash(TEST_HASH);
        user.setSalt(TEST_SALT);
        user.setFirstName(FIRST_NAME1);
        user.setLastName(LAST_NAME);
        user.setMiddleName(MIDDLE_NAME);
        user.setBirthDate(Date.valueOf(TEST_BIRTH_DATE));
        user.setWorkTitle(WORK_TITLE);
        user.setCreatedAt(Date.valueOf(TEST_BIRTH_DATE));
        user.setAvatar(AVATAR);
        user.setGroupId(GROUP_ID);
        return user;
    }

    private void getEmptyList() {
        List<Users> usersList = USERS_DAO.getAll();
        List<Users> emptyList = new ArrayList<>();
        Assert.assertEquals(emptyList, usersList);
    }

    private void addObject() {
        boolean testResult;
        Users user = USERS_DAO.addObject(this.createUserObject());
        testResult = USERS_DAO.getById(user.getId()).isPresent();
        Assert.assertTrue(testResult);
    }

    private void getAll() {
        List<Users> usersList = USERS_DAO.getAll();
        Assert.assertFalse(usersList.isEmpty());
    }

    private void getById() {
        Users users = USERS_DAO.addObject(this.createUserObject());
        Optional<Users> optionalUser = USERS_DAO.getById(users.getId());

        Assert.assertTrue(optionalUser.isPresent());
        Users dbUser = optionalUser.get();

        Assert.assertEquals(dbUser, users);
    }

    private void updateByObject() {
        Optional<Users> optionalUser = USERS_DAO.getById(USER_ID);
        Assert.assertTrue(optionalUser.isPresent());
        Users dbUser = optionalUser.get();

        dbUser.setFirstName(FIRST_NAME2);
        USERS_DAO.updateByObject(dbUser);

        Optional<Users> optionalUserAfterChanges = USERS_DAO.getById(USER_ID);
        Assert.assertTrue(optionalUserAfterChanges.isPresent());
        Users dbUserAfterChanges = optionalUserAfterChanges.get();

        Assert.assertEquals(dbUser, dbUserAfterChanges);
    }

    private void removeById() {
        Optional<Users> optionalUser = USERS_DAO.getById(USER_ID);
        Assert.assertTrue(optionalUser.isPresent());

        USERS_DAO.removeById(1);
        Assert.assertFalse(USERS_DAO.getById(USER_ID).isPresent());
    }

}
