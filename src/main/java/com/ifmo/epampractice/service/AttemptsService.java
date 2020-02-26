package com.ifmo.epampractice.service;

import com.ifmo.epampractice.dao.AttemptsDAO;
import com.ifmo.epampractice.entity.Attempts;

import java.util.List;
import java.util.Optional;

public class AttemptsService {
    private static final AttemptsDAO ATTEMPTS_DAO = new AttemptsDAO();
    private static final TestsService TESTS_SERVICE = new TestsService();

    public Attempts addObject(final Attempts attempt) {
        // ПРОВЕРИТЬ НА ЮЗЕРА
        if (TESTS_SERVICE.ifTestObjectExist(attempt.getTestId())) {
            System.err.println("This test doesn't exist");
            throw new IllegalArgumentException("This object doesn't exist");
        }
        return ATTEMPTS_DAO.addObject(attempt);
    }

    public List<Attempts> getAll() {
        return ATTEMPTS_DAO.getAll();
    }

    public List<Attempts> getAttemptsListByTestId(final int testId) {
        List<Attempts> attemptsList;
        if (TESTS_SERVICE.ifTestObjectExist(testId)) {
            System.err.println("This test doesn't exist");
            throw new IllegalArgumentException("This object doesn't exist");
        }
        attemptsList = ATTEMPTS_DAO.getAttemptsListByTestId(testId);

        return attemptsList;
    }

    public List<Attempts> getAttemptsListByTestAndUserId(final int testId, final int userId) {
        List<Attempts> attemptsList;
        if (TESTS_SERVICE.ifTestObjectExist(testId)) {
            System.err.println("This test doesn't exist");
            throw new IllegalArgumentException("This object doesn't exist");
        }
        attemptsList = ATTEMPTS_DAO.getAttemptsListByTestAndUserId(testId, userId);
        return attemptsList;
    }

    public Attempts getById(final int attemptId) {
        Attempts attempt;
        Optional<Attempts> attemptsOptional = ATTEMPTS_DAO.getById(attemptId);
        if (!attemptsOptional.isPresent()) {
            System.err.println("This attempt doesn't exist");
            throw new IllegalArgumentException("This object doesn't exist");
        }
        attempt = attemptsOptional.get();
        return attempt;
    }

    public void updateByObject(final Attempts attempt) {
        // ПРОВЕРИТЬ НА ЮЗЕРА
        if (TESTS_SERVICE.ifTestObjectExist(attempt.getTestId())) {
            System.err.println("This test doesn't exist");
            throw new IllegalArgumentException("This object doesn't exist");
        }
        ATTEMPTS_DAO.updateByObject(attempt);
    }

    public void removeById(final int attemptId) {
        if (!ATTEMPTS_DAO.getById(attemptId).isPresent()) {
            System.err.println("This test attempt't exist");
            throw new IllegalArgumentException("This object doesn't exist");
        }
        ATTEMPTS_DAO.removeById(attemptId);
    }

    public Boolean ifAttemptObjectExist(final int id) {
        if (ATTEMPTS_DAO.getById(id).isPresent()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
