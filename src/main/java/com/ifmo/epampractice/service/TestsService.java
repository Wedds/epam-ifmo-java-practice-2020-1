package com.ifmo.epampractice.service;

import com.ifmo.epampractice.dao.TestsDAO;
import com.ifmo.epampractice.entity.Tests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TestsService {
    private static final TestsDAO TESTS_DAO = new TestsDAO();
    private static final QuestionsService QUESTIONS_SERVICE = new QuestionsService();
    private static final AttemptsService ATTEMPTS_SERVICE = new AttemptsService();
    private static final SubjectsService SUBJECTS_SERVICE = new SubjectsService();
    private static final UsersService USERS_SERVICE = new UsersService();
    private static TestsService instance;

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

    public List<Tests> getAllTestsByCreatorId(final int creatorId) {
        if (!USERS_SERVICE.ifUserObjectExist(creatorId)) {
            System.err.println("User doesn't exist");
            throw new IllegalArgumentException("This object doesn't exist");
        }
        return TESTS_DAO.getAllTestsByCreatorId(creatorId);
    }

    public List<Tests> getAllTestsForGroupByCreatorId(final int creatorId) {
        if (!USERS_SERVICE.ifUserObjectExist(creatorId)) {
            System.err.println("User doesn't exist");
            throw new IllegalArgumentException("This object doesn't exist");
        }
        return TESTS_DAO.getAllTestsForGroupsByCreatorId(creatorId);
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

    public Map<Integer, Integer> getDictionaryWithTestIdAndMaxScoreByUserId(final int userId) {
        //TODO Check on group
        Map<Integer, Integer> userMaxScoreDict = new HashMap<>();
        int groupId = USERS_SERVICE.getById(userId).getGroupId();
        List<Tests> testsList = TESTS_DAO.getAllTestsForGroupsByGroupId(groupId);

        for (Tests test : testsList) {
            int maxScore = ATTEMPTS_SERVICE.getMaximumScoreTestIdAndByUserId(test.getId(), userId);
            userMaxScoreDict.put(test.getId(), maxScore);
        }
        return userMaxScoreDict;
    }

    public Map<Integer, Integer> getDictionaryWithTestIdAndAttemptsLeftCountByUserId(final int userId) {
        Map<Integer, Integer> attemptsCountDict = new HashMap<>();
        int groupId = USERS_SERVICE.getById(userId).getGroupId();
        List<Tests> testsList = TESTS_DAO.getAllTestsForGroupsByGroupId(groupId);

        for (Tests test : testsList) {
            int passedAttempts = ATTEMPTS_SERVICE.getAttemptsListByTestAndUserId(test.getId(), userId).size();

            attemptsCountDict.put(test.getId(), test.getMaxAttempts() - passedAttempts);
        }
        return attemptsCountDict;
    }

    public Map<Integer, Integer> getDictionaryWithTestIdAndGroupsCountByCreatorId(final int userId) {
        Map<Integer, Integer> groupsCountDict = new HashMap<>();
        List<Tests> testsList = getAllTestsByCreatorId(userId);
        for (Tests test : testsList) {
            int groupsCount = getAllTestsForGroupsByTestId(test.getId()).size();
            if (groupsCount != 0) {
                groupsCountDict.put(test.getId(), groupsCount);
            }
        }
        return groupsCountDict;
    }

    public Boolean ifTestObjectExist(final int id) {
        return (TESTS_DAO.getById(id).isPresent());
    }
}
