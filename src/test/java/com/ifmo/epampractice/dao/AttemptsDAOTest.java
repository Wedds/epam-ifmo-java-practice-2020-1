package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.Attempts;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.sql.Date;

public class AttemptsDAOTest {
    private static final AttemptsDAO ATTEMPTS_DAO = new AttemptsDAO();
    private static final int TEST_ID = 2;
    private static final int USER_ID = 3;
    private static final int SCORE = 10;
    private static final Date PASSING_DATE = Date.valueOf("2020-01-03");
    private static final int TEST_ID_UPDATE = 3;
    private static final int USER_ID_UPDATE = 2;
    private static final int SCORE_UPDATE = 5;
    private static final Date PASSING_DATE_UPDATE = Date.valueOf("2020-02-01");

    public Attempts createAttemptsObject() {
        Attempts attempt = new Attempts();
        attempt.setTestId(TEST_ID);
        attempt.setUserId(USER_ID);
        attempt.setScore(SCORE);
        attempt.setPassingDate(PASSING_DATE);
        return attempt;
    }

    public Attempts createAttemptsObjectForUpdate() {
        Attempts attempt = new Attempts();
        attempt.setTestId(TEST_ID_UPDATE);
        attempt.setUserId(USER_ID_UPDATE);
        attempt.setScore(SCORE_UPDATE);
        attempt.setPassingDate(PASSING_DATE_UPDATE);
        return attempt;
    }

    @Test
    public void testAddObject() {
        boolean controlSum;
        Attempts attempt = createAttemptsObject();
        attempt = ATTEMPTS_DAO.addObject(attempt);
        controlSum = ATTEMPTS_DAO.getById(attempt.getId()).isPresent();
        Assert.assertEquals(Boolean.TRUE, controlSum);
    }

    @Test
    public void testUpdateByObject() {
        boolean controlSum;
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
        if (controlAttempt.equals(attemptForUpdate)) {
            controlSum = Boolean.TRUE;
        } else {
            controlSum = Boolean.FALSE;
        }
        Assert.assertEquals(Boolean.TRUE, controlSum);
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

    @Test
    public void testGetById() {
        boolean controlSum;
        Attempts attempt = createAttemptsObject();
        attempt = ATTEMPTS_DAO.addObject(attempt);
        Optional<Attempts> attemptOptional = ATTEMPTS_DAO.getById(attempt.getId());
        Attempts receivedAnswer = new Attempts();
        if (attemptOptional.isPresent()) {
            receivedAnswer = attemptOptional.get();
        }
        if (receivedAnswer.equals(attempt)) {
            controlSum = Boolean.TRUE;
        } else {
            controlSum = Boolean.FALSE;
        }
        Assert.assertEquals(Boolean.TRUE, controlSum);
    }

    @Test
    public void testGetAll() {
        Attempts attempt = createAttemptsObject();
        attempt = ATTEMPTS_DAO.addObject(attempt);
        List<Attempts> attemptsList = ATTEMPTS_DAO.getAll();
        Assert.assertFalse(attemptsList.isEmpty());
    }
}
