package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.Attempts;

import com.ifmo.epampractice.service.DatabaseSource;
import com.ifmo.epampractice.utilities.TestUtilities;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;


public class AttemptsDAOTest {
    private static final AttemptsDAO ATTEMPTS_DAO = new AttemptsDAO();
    private static final int TEST_ID = 2;
    private static final int USER_ID = 3;
    private static final int SCORE = 10;
    private static final LocalDateTime PASSING_DATE = LocalDateTime.of(2020, Month.JULY, 9, 11, 6, 22);
    private static final int TEST_ID_UPDATE = 3;
    private static final int USER_ID_UPDATE = 2;
    private static final int SCORE_UPDATE = 5;
    private static final LocalDateTime PASSING_DATE_UPDATE = LocalDateTime.of(2020, Month.JANUARY, 10, 11, 3, 22);

    @BeforeClass
    public static void initTestDb() {
        try (Connection connection = DatabaseSource.getInstance().getConnection();
             Statement statement = connection.createStatement()
        ) {
            TestUtilities.executeSqlFile(Paths.get("src", "test", "resources", "Database_script_test.sql"), statement);
            TestUtilities.executeSqlFile(Paths.get("src", "test", "resources", "Insert_test_script_H2.sql"), statement);
        } catch (SQLException e) {
            throw new IllegalArgumentException("Unable to create a test database.", e);
        }
    }

    @Test
    public void testAddObject() {
        boolean controlSum;
        Attempts attempt = createAttemptsObject();
        attempt = ATTEMPTS_DAO.addObject(attempt);
        controlSum = ATTEMPTS_DAO.getById(attempt.getId()).isPresent();
        Assert.assertEquals(Boolean.TRUE, controlSum);
        ATTEMPTS_DAO.removeById(attempt.getId());
    }

    @Test
    public void testGetAttemptsListByTestId() {
        int wasElements = ATTEMPTS_DAO.getAttemptsListByTestId(2).size();
        Attempts attempt = createAttemptsObject();
        attempt = ATTEMPTS_DAO.addObject(attempt);
        Assert.assertEquals(wasElements + 1, ATTEMPTS_DAO.getAttemptsListByTestId(2).size());
        ATTEMPTS_DAO.removeById(attempt.getId());
    }

    @Test
    public void testGetAttemptsListByTestAndUserId() {
        int wasElements = ATTEMPTS_DAO.getAttemptsListByTestAndUserId(2, 3).size();
        Attempts attempt = createAttemptsObject();
        attempt = ATTEMPTS_DAO.addObject(attempt);
        Assert.assertEquals(wasElements + 1, ATTEMPTS_DAO.getAttemptsListByTestAndUserId(2, 3).size());
        ATTEMPTS_DAO.removeById(attempt.getId());
    }

    @Test
    public void testGetAll() {
        int wasElements = ATTEMPTS_DAO.getAll().size();
        Attempts attempt = createAttemptsObject();
        attempt = ATTEMPTS_DAO.addObject(attempt);
        Assert.assertEquals(wasElements + 1, ATTEMPTS_DAO.getAll().size());
        ATTEMPTS_DAO.removeById(attempt.getId());
    }

    @Test
    public void testGetById() {
        Attempts attempt = createAttemptsObject();
        attempt = ATTEMPTS_DAO.addObject(attempt);
        Optional<Attempts> attemptOptional = ATTEMPTS_DAO.getById(attempt.getId());
        Attempts receivedAnswer = new Attempts();
        if (attemptOptional.isPresent()) {
            receivedAnswer = attemptOptional.get();
        }
        Assert.assertEquals(Boolean.TRUE, receivedAnswer.equals(attempt));
        ATTEMPTS_DAO.removeById(attempt.getId());
    }

    @Test
    public void testUpdateByObject() {
        Attempts attemptBeforeUpdate = createAttemptsObject();
        attemptBeforeUpdate = ATTEMPTS_DAO.addObject(attemptBeforeUpdate);
        int id = attemptBeforeUpdate.getId();
        Attempts attemptForUpdate = createAttemptsObjectForUpdate();
        attemptForUpdate.setId(id);
        ATTEMPTS_DAO.updateByObject(attemptForUpdate);
        Optional<Attempts> attemptOptional = ATTEMPTS_DAO.getById(id);
        Attempts controlAttempt = new Attempts();
        if (attemptOptional.isPresent()) {
            controlAttempt = attemptOptional.get();
        }
        Assert.assertEquals(Boolean.TRUE, controlAttempt.equals(attemptForUpdate));
        ATTEMPTS_DAO.removeById(attemptForUpdate.getId());
    }

    @Test
    public void testRemoveById() {
        boolean controlSum;
        Attempts attempt = createAttemptsObject();
        attempt = ATTEMPTS_DAO.addObject(attempt);
        int id = attempt.getId();
        ATTEMPTS_DAO.removeById(id);
        controlSum = ATTEMPTS_DAO.getById(id).isPresent();
        Assert.assertEquals(Boolean.FALSE, controlSum);
    }

    private Attempts createAttemptsObject() {
        Attempts attempt = new Attempts();
        attempt.setTestId(TEST_ID);
        attempt.setUserId(USER_ID);
        attempt.setScore(SCORE);
        attempt.setPassingDate(PASSING_DATE);
        return attempt;
    }

    private Attempts createAttemptsObjectForUpdate() {
        Attempts attempt = new Attempts();
        attempt.setTestId(TEST_ID_UPDATE);
        attempt.setUserId(USER_ID_UPDATE);
        attempt.setScore(SCORE_UPDATE);
        attempt.setPassingDate(PASSING_DATE_UPDATE);
        return attempt;
    }
}
