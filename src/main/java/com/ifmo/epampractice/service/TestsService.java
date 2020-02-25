package com.ifmo.epampractice.service;

import com.ifmo.epampractice.dao.TestsDAO;
import com.ifmo.epampractice.entity.Tests;

import java.util.List;
import java.util.Optional;

public class TestsService {
    private static final TestsDAO TESTS_DAO = new TestsDAO();
    private static final QuestionsService QUESTIONS_SERVICE = new QuestionsService();

    public Tests addObject(final Tests test) {
        // ПРВЕРИТЬ НА ЮЗЕРА И САБДЖЕКТ
        return TESTS_DAO.addObject(test);

    }

    public Tests addTestForGroup(final Tests test) {
        // ПРОВЕРИТЬ НА ГРУППУ
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
        // ПРОВЕРЯЕМ ПО ГРУППЕ
        List<Tests> testsList;
        testsList = TESTS_DAO.getAllTestsForGroupsByGroupId(groupId);
        return testsList;
    }

    public Tests getById(final int testId) {
        Tests test;
        Optional<Tests> testsOptional = TESTS_DAO.getById(testId);
        if (!testsOptional.isPresent()) {
            System.err.println("Test doesn't exist");
            throw new IllegalArgumentException("This object doesn't exist");
        }
        test = testsOptional.get();
        return test;
    }

    public Tests getObjectByTestAndGroupId(final int testId, final int groupId) {
        Tests test;
        Optional<Tests> testsOptional = TESTS_DAO.getObjectByTestAndGroupId(testId, groupId);
        // Пороверяем по тесту и по группе
        if (!testsOptional.isPresent()) {
            System.err.println("Test doesn't exist");
            throw new IllegalArgumentException("This object doesn't exist");
        }
        test = testsOptional.get();
        return test;
    }

    public void updateTestByObject(final Tests test) {
        // ПРВЕРИТЬ НА ЮЗЕРА И САБДЖЕКТ;
        TESTS_DAO.updateByObject(test);
    }

    public void updateTestForGroupByObject(final Tests test) {
        // ПРВЕРИТЬ НА ЮЗЕРА И САБДЖЕКТ;
        TESTS_DAO.updateByObject(test);
    }

    public void removeTestById(final int testId) {
        if (!ifTestObjectExist(testId)) {
            System.err.println("Test doesn't exist");
            throw new IllegalArgumentException("This object doesn't exist");
        }
        TESTS_DAO.removeById(testId);
    }

    public void removeGroupsTestsByTestId(final int testId) {
        //Проверяем на группу и тест
        if (!ifTestObjectExist(testId)) {
            System.err.println("Test doesn't exist");
            throw new IllegalArgumentException("This object doesn't exist");
        }
        TESTS_DAO.removeGroupsTestsByTestId(testId);
    }

    public void removeGroupsTestsByGroupId(final int groupId) {
        //Проверяем на группу
        TESTS_DAO.removeGroupsTestsByTestId(groupId);
    }

    public void removeGroupsTestsByTestAndGroupId(final int testId, final int groupId) {
        //Проверяем на группу и тест
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
        if (!ifTestObjectExist(testId)) {
            System.err.println("Test doesn't exist");
            throw new IllegalArgumentException("This object doesn't exist");
        }
        test = getObjectByTestAndGroupId(testId, groupId);
        test.setQuestionsList(QUESTIONS_SERVICE.getQuestionsWithAnswersListByTestId(testId));
        return test;
    }

    public Boolean ifTestObjectExist(final int id) {
        if (TESTS_DAO.getById(id).isPresent()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
