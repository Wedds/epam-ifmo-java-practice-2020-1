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
    public static final String FIRST_NAME = "Bob";
    private static final String DELETE = "DELETE FROM users WHERE id > 0; ALTER SEQUENCE users_id_seq RESTART WITH 1;";
    private static final String INIT_GROUP_QUERY = "INSERT INTO groups VALUES (?,?,?) ON CONFLICT DO NOTHING";
    private static final int GROUP_ID = 1;
    private static final int USER_ID = 1;
    private final UsersDAO usersDAO;

    public UsersDAOTest() {
        this.usersDAO = new UsersDAO();
    }

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
            stmt.setString(2, "K1234");
            stmt.setDate(3, Date.valueOf("2000-01-01"));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Cannot add record in the table groups.", e);
        }
    }

    private Users createUserObject() {
        Users user = new Users();
        user.setRoleType(Roles.ADMIN);
        user.setEmail("test@email.com");
        user.setHash("34fjbg45jhgb65hnyjy");
        user.setSalt("3jhb6rjhyntfwdsfjhb");
        user.setFirstName("Nike");
        user.setLastName("Borzov");
        user.setMiddleName("Vladimirovich");
        user.setBirthDate(Date.valueOf("1972-05-23"));
        user.setWorkTitle("Guru");
        user.setCreatedAt(Date.valueOf("1972-05-23"));
        user.setAvatar("sdkfmslkdfmlskdfmlskdfm");
        user.setGroupId(GROUP_ID);
        return user;
    }

    private void getEmptyList() {
        List<Users> usersList = usersDAO.getAll();
        List<Users> emptyList = new ArrayList<>();
        Assert.assertEquals(emptyList, usersList);
    }

    private void addObject() {
        boolean testResult;
        Users user = usersDAO.addObject(this.createUserObject());
        testResult = usersDAO.getById(user.getId()).isPresent();
        Assert.assertTrue(testResult);
    }

    private void getAll() {
        List<Users> usersList = usersDAO.getAll();
        Assert.assertFalse(usersList.isEmpty());
    }

    private void getById() {
        Users users = usersDAO.addObject(this.createUserObject());
        Optional<Users> optionalUser = usersDAO.getById(users.getId());

        Assert.assertTrue(optionalUser.isPresent());
        Users dbUser = optionalUser.get();

        Assert.assertEquals(dbUser, users);
    }

    private void updateByObject() {
        Optional<Users> optionalUser = usersDAO.getById(USER_ID);
        Assert.assertTrue(optionalUser.isPresent());
        Users dbUser = optionalUser.get();

        dbUser.setFirstName(FIRST_NAME);
        usersDAO.updateByObject(dbUser);

        Optional<Users> optionalUserAfterChanges = usersDAO.getById(USER_ID);
        Assert.assertTrue(optionalUserAfterChanges.isPresent());
        Users dbUserAfterChanges = optionalUserAfterChanges.get();

        Assert.assertEquals(dbUser, dbUserAfterChanges);
    }

    private void removeById() {
        Optional<Users> optionalUser = usersDAO.getById(USER_ID);
        Assert.assertTrue(optionalUser.isPresent());

        usersDAO.removeById(1);
        Assert.assertFalse(usersDAO.getById(USER_ID).isPresent());
    }

}
