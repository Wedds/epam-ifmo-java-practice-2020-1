package com.ifmo.epampractice.service;

import com.ifmo.epampractice.dao.AttemptsDAO;
import com.ifmo.epampractice.entity.Attempts;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AttemptsService {
    private static final AttemptsDAO ATTEMPTS_DAO = new AttemptsDAO();
    private static final TestsService TESTS_SERVICE = new TestsService();

    public Attempts getAttemptFromRequest(final HttpServletRequest request) {
        Attempts attempt = new Attempts();
        final String nullableId = request.getParameter("id");
        final String nullableTestId = request.getParameter("testId");
        final String nullableUserId = request.getParameter("userId");
        final String nullableScore = request.getParameter("score");
        final String nullablePassingDate = request.getParameter("passingDate");

        if (nullableTestId == null || nullableUserId == null
                || nullableScore == null || nullablePassingDate == null) {
            System.err.println("Attempt parameter is missing");
            throw new IllegalArgumentException("Parameter is missing");
        }

        try {

            // Добавить проверку на User

            if (TESTS_SERVICE.ifTestObjectExist(request)) {
                System.err.println("This test doesn't exist");
                throw new IllegalArgumentException("This test doesn't exist");
            }
            attempt.setTestId(Integer.parseInt(nullableTestId));
            attempt.setUserId(Integer.parseInt(nullableUserId));
            attempt.setScore(Integer.parseInt(nullableScore));
            attempt.setPassingDate(Date.valueOf(nullablePassingDate));

            if (nullableId != null) {
                attempt.setId(Integer.parseInt(nullableId));
            }
        } catch (NumberFormatException e) {
            System.err.println("Incorrect format of some parameters");
            throw new IllegalArgumentException("Incorrect format of parameters");
        }

        return new Attempts();
    }

    public Attempts addObject(final HttpServletRequest request) {
        Attempts attempt = getAttemptFromRequest(request);
        return ATTEMPTS_DAO.addObject(attempt);
    }

    public List<Attempts> getAll() {
        return ATTEMPTS_DAO.getAll();
    }

    public List<Attempts> getAttemptsListByTestId(final HttpServletRequest request) {
        final String nullableTestId = request.getParameter("testId");
        List<Attempts> attemptsList = new ArrayList<>();
        if (nullableTestId == null) {
            System.err.println("Attempt parameter is missing");
            throw new IllegalArgumentException("Parameter is missing");
        }

        try {
            int testId = Integer.parseInt(nullableTestId);
            attemptsList = ATTEMPTS_DAO.getAttemptsListByTestId(testId);
        } catch (NumberFormatException e) {
            System.err.println("Incorrect format of question id");
            throw new IllegalArgumentException("Incorrect format of id");
        }

        return attemptsList;
    }

    public List<Attempts> getAttemptsListByTestAndUserId(final HttpServletRequest request) {
        final String nullableTestId = request.getParameter("testId");
        final String nullableUserId = request.getParameter("userId");
        List<Attempts> attemptsList = new ArrayList<>();
        if (nullableTestId == null || nullableUserId == null) {
            System.err.println("Attempt parameter is missing");
            throw new IllegalArgumentException("Parameter is missing");
        }

        try {
            int testId = Integer.parseInt(nullableTestId);
            int userId = Integer.parseInt(nullableUserId);
            attemptsList = ATTEMPTS_DAO.getAttemptsListByTestAndUserId(testId, userId);
        } catch (NumberFormatException e) {
            System.err.println("Incorrect format of question id");
            throw new IllegalArgumentException("Incorrect format of id");
        }

        return attemptsList;
    }

    public Attempts getById(final HttpServletRequest request) {
        final String nullableTestId = request.getParameter("id");
        Attempts attempt = new Attempts();
        if (nullableTestId == null) {
            System.err.println("Attempt parameter is missing");
            throw new IllegalArgumentException("Parameter is missing");
        }

        try {
            int attemptId = Integer.parseInt(nullableTestId);
            Optional<Attempts> attemptsOptional = ATTEMPTS_DAO.getById(attemptId);
            if (!attemptsOptional.isPresent()) {
                System.err.println("This attempt doesn't exist");
                throw new IllegalArgumentException("This attempt doesn't exist");
            }
            attempt = attemptsOptional.get();
        } catch (NumberFormatException e) {
            System.err.println("Incorrect format of attempt id");
            throw new IllegalArgumentException("Incorrect format of id");
        }
        return attempt;
    }

    public void updateByObject(final HttpServletRequest request) {
        Attempts attempt = getAttemptFromRequest(request);
        ATTEMPTS_DAO.updateByObject(attempt);
    }

    public void removeById(final HttpServletRequest request) {
        final String nullableTestId = request.getParameter("id");

        if (nullableTestId == null) {
            System.err.println("Attempt parameter is missing");
            throw new IllegalArgumentException("Parameter is missing");
        }
        try {
            int attemptId = Integer.parseInt(nullableTestId);
            if (!ATTEMPTS_DAO.getById(attemptId).isPresent()) {
                System.err.println("This test attempt't exist");
                throw new IllegalArgumentException("This attempt doesn't exist");
            }
            ATTEMPTS_DAO.removeById(attemptId);
        } catch (NumberFormatException e) {
            System.err.println("Incorrect format of attempt id");
            throw new IllegalArgumentException("Incorrect format of id");
        }
    }

    public Boolean ifAttemptObjectExist(final HttpServletRequest request) {
        final String nullableId = request.getParameter("id");
        if (nullableId == null) {
            System.err.println("Attempt parameter is missing");
            throw new IllegalArgumentException("Parameter is missing");
        }

        try {
            if (ATTEMPTS_DAO.getById(Integer.parseInt(nullableId)).isPresent()) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } catch (NumberFormatException e) {
            System.err.println("Incorrect format of attempt id");
            throw new IllegalArgumentException("Incorrect format of id");
        }
    }
}
