package com.ifmo.epampractice.service;

import com.ifmo.epampractice.dao.TestsDAO;
import com.ifmo.epampractice.entity.Tests;

import java.util.List;

public final class TestsService {
    private  static final TestsDAO TESTS_DAO = new TestsDAO();
    private static final QuestionsService QUESTIONS_SERVICE = new QuestionsService();
    private static final AttemptsService ATTEMPTS_SERVICE = new AttemptsService();
    private static TestsService instance;

    private TestsService() {}

    public static TestsService getInstance() {
        if (instance != null) {
            return instance;
        } else {
            instance = new TestsService();
        }
        return instance;
    }

    public Tests addObject(final Tests test) {
        // TODO Check on user and subjects
        return TESTS_DAO.addObject(test);

    }

    public Tests addTestForGroup(final Tests test) {
        // TODO Check on group
        if (ifTestObjectExist(test.getId())) {
            System.err.println("This test doesn't exist");
            throw new IllegalArgumentException("This object doesn't exist");
        }
        return TESTS_DAO.addTestForGroup(test);

    }

    public List<Tests> getAll() {
        return TESTS_DAO.getAll();
    }

    public List<Tests> getAllTestsForGroupsByTestId(final int testId) {
        List<Tests> testsList;
        if (!ifTestObjectExist(testId)) {
            System.err.println("Test doesn't exist");
            throw new IllegalArgumentException("This object doesn't exist");
        }
        testsList = TESTS_DAO.getAllTestsForGroupsByTestId(testId);
        return testsList;
    }

    public List<Tests> getAllTestsForGroupsByGroupId(final int groupId) {
        // TODO Check on group
        List<Tests> testsList;
        testsList = TESTS_DAO.getAllTestsForGroupsByGroupId(groupId);
        return testsList;
    }

    public Tests getById(final int testId) {
        return TESTS_DAO.getById(testId).orElseThrow(() ->
                new IllegalArgumentException("This object doesn't exist"));
    }

    public Tests getObjectByTestAndGroupId(final int testId, final int groupId) {
        //TODO Check on group
        return TESTS_DAO.getObjectByTestAndGroupId(testId, groupId).orElseThrow(() ->
                new IllegalArgumentException("This object doesn't exist"));
    }

    public void updateTestByObject(final Tests test) {
        //TODO Check on user and subject
        TESTS_DAO.updateByObject(test);
    }

    public void updateGroupsTests(final Tests test) {
        //TODO Check on user and subject
        TESTS_DAO.updateGroupsTests(test);
    }

    public void removeTestById(final int testId) {
        if (!ifTestObjectExist(testId)) {
            System.err.println("Test doesn't exist");
            throw new IllegalArgumentException("This object doesn't exist");
        }
        TESTS_DAO.removeById(testId);
    }

    public void removeGroupsTestsByTestAndGroupId(final int testId, final int groupId) {
        //TODO Check on group
        if (!ifTestObjectExist(testId)) {
            System.err.println("Test doesn't exist");
            throw new IllegalArgumentException("This object doesn't exist");
        }
        TESTS_DAO.removeGroupsTestsByTestAndGroupId(testId, groupId);
    }

    public Tests getTestWithQuestionsByTestId(final int testId) {
        Tests test;
        if (!ifTestObjectExist(testId)) {
            System.err.println("Test doesn't exist");
            throw new IllegalArgumentException("This object doesn't exist");
        }
        test = getById(testId);
        test.setQuestionsList(QUESTIONS_SERVICE.getQuestionsWithAnswersListByTestId(testId));
        return test;
    }

    public Tests getTestForGroupWithQuestions(final int testId, final int groupId) {
        Tests test;
        // CHECK GROUP
        if (!ifTestObjectExist(testId)) {
            System.err.println("Test doesn't exist");
            throw new IllegalArgumentException("This object doesn't exist");
        }
        test = getObjectByTestAndGroupId(testId, groupId);
        test.setQuestionsList(QUESTIONS_SERVICE.getQuestionsWithAnswersListByTestId(testId));
        return test;
    }

    public Tests getTestWithAttemptsByTestId(final int testId) {
        Tests test;
        if (!ifTestObjectExist(testId)) {
            System.err.println("Test doesn't exist");
            throw new IllegalArgumentException("This object doesn't exist");
        }
        test = getById(testId);
        test.setAttemptsList(ATTEMPTS_SERVICE.getAttemptsListByTestId(testId));
        return test;
    }

    public Tests getTestForGroupWithAttempts(final int testId, final int groupId) {
        Tests test;
        //TODO Check on group
        if (!ifTestObjectExist(testId)) {
            System.err.println("Test doesn't exist");
            throw new IllegalArgumentException("This object doesn't exist");
        }
        test = getObjectByTestAndGroupId(testId, groupId);
        test.setAttemptsList(ATTEMPTS_SERVICE.getAttemptsListByTestId(testId));
        return test;
    }

    public Boolean ifTestObjectExist(final int id) {
        if (TESTS_DAO.getById(id).isPresent()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
