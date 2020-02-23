package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.Attempts;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

public class AttemptsDAOTest {
    private static final AttemptsDAO ATTEMPTS_DAO = new AttemptsDAO();

    public Attempts createAttemptsObject() {
        Attempts attempt = new Attempts();
        attempt.setTestId(2);
        attempt.setUserId(3);
        attempt.setScore(10);
        java.sql.Date passingDate = java.sql.Date.valueOf("2020-01-03");
        attempt.setPassingDate(passingDate);
        return attempt;
    }

    public Attempts createAttemptsObjectForUpdate() {
        Attempts attempt = new Attempts();
        attempt.setTestId(3);
        attempt.setUserId(2);
        attempt.setScore(5);
        java.sql.Date passingDate = java.sql.Date.valueOf("2020-02-01");
        attempt.setPassingDate(passingDate);
        return attempt;
    }

    @Test
    public void addObject() {
        Boolean controlSum;
        Attempts attempt = createAttemptsObject();
        attempt = ATTEMPTS_DAO.addObject(attempt);
        controlSum = ATTEMPTS_DAO.getById(attempt.getId()).isPresent();
        Assert.assertEquals(Boolean.TRUE, controlSum);
    }

    @Test
    public void updateByObject() {
        Boolean controlSum;
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
    public void removeById() {
        Boolean controlSum;
        Attempts attempt = createAttemptsObject();
        attempt = ATTEMPTS_DAO.addObject(attempt);
        int id = attempt.getId();
        ATTEMPTS_DAO.removeById(id);
        controlSum = ATTEMPTS_DAO.getById(id).isPresent();
        Assert.assertEquals(Boolean.FALSE, controlSum);
    }

    @Test
    public void getById() {
        Boolean controlSum;
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
    public void getAll() {
        Attempts attempt = createAttemptsObject();
        attempt = ATTEMPTS_DAO.addObject(attempt);
        List<Attempts> attemptsList = ATTEMPTS_DAO.getAll();
        Assert.assertFalse(attemptsList.isEmpty());
    }
}
