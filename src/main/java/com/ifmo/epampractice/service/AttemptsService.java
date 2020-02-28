package com.ifmo.epampractice.service;

import com.ifmo.epampractice.dao.AttemptsDAO;
import com.ifmo.epampractice.entity.Attempts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AttemptsService {
    private static final AttemptsDAO ATTEMPTS_DAO = new AttemptsDAO();
    private static final TestsService TESTS_SERVICE = new TestsService();

    public Attempts addObject(final Attempts attempt) {
        // ПРОВЕРИТЬ НА ЮЗЕРА
        if (!TESTS_SERVICE.ifTestObjectExist(attempt.getTestId())) {
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
        if (!TESTS_SERVICE.ifTestObjectExist(testId)) {
            System.err.println("This test doesn't exist");
            throw new IllegalArgumentException("This object doesn't exist");
        }
        attemptsList = ATTEMPTS_DAO.getAttemptsListByTestId(testId);

        return attemptsList;
    }

    public List<Attempts> getAttemptsListByTestAndUserId(final int testId, final int userId) {
        List<Attempts> attemptsList;
        if (!TESTS_SERVICE.ifTestObjectExist(testId)) {
            System.err.println("This test doesn't exist");
            throw new IllegalArgumentException("This object doesn't exist");
        }
        attemptsList = ATTEMPTS_DAO.getAttemptsListByTestAndUserId(testId, userId);
        return attemptsList;
    }

    public int getMaximumScoreTestIdAndByUserId(final int testId, final int userId) {
        List<Integer> userScoreList = new ArrayList<>();
        List<Attempts> attemptsList = getAttemptsListByTestAndUserId(testId, userId);
        for (Attempts attempts : attemptsList) {
            userScoreList.add(attempts.getScore());
        }
        if (userScoreList.size() == 0) {
            return 0;
        }
        return Collections.max(userScoreList);
    }

    public Attempts getById(final int attemptId) {
        return ATTEMPTS_DAO.getById(attemptId).orElseThrow(() ->
                new IllegalArgumentException("This object doesn't exist"));
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
        ATTEMPTS_DAO.getById(attemptId).orElseThrow(() ->
                new IllegalArgumentException("This object doesn't exist"));
        ATTEMPTS_DAO.removeById(attemptId);
    }

    public Boolean ifAttemptObjectExist(final int id) {
        return (ATTEMPTS_DAO.getById(id).isPresent());
    }
}
