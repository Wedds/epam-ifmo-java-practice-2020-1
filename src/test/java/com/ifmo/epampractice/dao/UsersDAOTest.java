package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.dao.UsersDAO;
import com.ifmo.epampractice.entity.Users;
import com.ifmo.epampractice.enums.Roles;
import com.ifmo.epampractice.service.DatabaseSource;
import com.ifmo.epampractice.utilities.TestUtilities;
import org.junit.*;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersDAOTest {
    private static final int GROUP_ID = 1;
    private static final int USER_ID = 1;
    private static final String TEST_EMAIL = "test@email.com";
    private static final String TEST_HASH = "34fjbg45jhgb65hnyjy";
    private static final String TEST_SALT = "3jhb6rjhyntfwdsfjhb";
    private static final String FIRST_NAME1 = "Nike";
    private static final String FIRST_NAME2 = "Bob";
    private static final String LAST_NAME = "Borzov";
    private static final String MIDDLE_NAME = "Vladimirovich";
    private static final String TEST_BIRTH_DATE = "1972-05-23";
    private static final LocalDateTime TEST_CREATION_DATE = LocalDateTime.of(2020, Month.JULY, 9, 11, 6, 22);
    private static final String AVATAR = "sdkfmslkdfmlskdfmlskdfm";
    private static final String WORK_TITLE = "Guru";
    private static final UsersDAO USERS_DAO = new UsersDAO();
    public static final String CREATE_GROUP_FOR_TEST = "INSERT INTO groups (name, created_at) VALUES ('K5148', '2012-09-17 18:47:52');";

    @BeforeClass
    public static void initTestDb() {
        try (Connection connection = DatabaseSource.getInstance().getConnection();
             Statement statement = connection.createStatement();
        ) {
            TestUtilities.executeSqlFile(Paths.get("src","main", "resources", "Database_script_test.sql"), statement);
            statement.execute(CREATE_GROUP_FOR_TEST);
        } catch (SQLException e) {
            throw new IllegalArgumentException("Unable to create a test database.", e);
        }
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
        user.setCreatedAt(TEST_CREATION_DATE);
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
