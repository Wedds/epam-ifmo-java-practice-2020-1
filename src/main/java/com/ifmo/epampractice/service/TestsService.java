package com.ifmo.epampractice.service;

import com.ifmo.epampractice.dao.TestsDAO;
import com.ifmo.epampractice.entity.Tests;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestsService {
    private static final TestsDAO TESTS_DAO = new TestsDAO();

    public Tests getTestFromRequest(final HttpServletRequest request) {
        Tests test = new Tests();
        final String nullableId = request.getParameter("id");
        final String nullableSubjectId = request.getParameter("subjectId");
        final String nullableTitle = request.getParameter("title");
        final String nullableDescription = request.getParameter("description");
        final String nullableIsRandom = request.getParameter("isRandom");
        final String nullableCreatedAt = request.getParameter("createdAt");
        final String nullableMaxPoints = request.getParameter("maxPoints");
        final String nullableCreatorId = request.getParameter("creatorId");
        //createdAt надо ли проверять на null? Или ее отдельно с сообщением вынести? Зависит ведь не от пользователя
        if (nullableSubjectId == null || nullableTitle == null || nullableIsRandom == null
                || nullableMaxPoints == null || nullableCreatorId == null || nullableCreatedAt == null) {
            throw new IllegalArgumentException("Parameter is missing");
        }

        try {
            test.setTitle(nullableTitle.trim());
            test.setDescription(nullableDescription.trim());
            test.setCreatedAt(Date.valueOf(nullableCreatedAt));
            test.setSubjectId(Integer.parseInt(nullableSubjectId));
            test.setIsRandom(Boolean.parseBoolean(nullableIsRandom));
            test.setCreatorId(Integer.parseInt(nullableCreatorId));
            test.setMaxPoints(Integer.parseInt(nullableMaxPoints));

            if (nullableId != null) {
                test.setId(Integer.parseInt(nullableId));
            }
        } catch (NumberFormatException e) {
            System.err.println("Incorrect format of some parameters");
        }

        return new Tests();
    }

    public Tests getTestForGroupFromRequest(final HttpServletRequest request) {

        Tests test = getTestFromRequest(request);
        final String nullableGroupId = request.getParameter("groupId");
        final String nullableIsNecessary = request.getParameter("isNecessary");
        final String nullableMaxAttempts = request.getParameter("maxAttempts");
        final String nullableDeadline = request.getParameter("deadline");
        final String nullableTimeLimit = request.getParameter("timeLimit");


        if (nullableGroupId == null || nullableIsNecessary == null || nullableMaxAttempts == null
                || nullableDeadline == null || nullableTimeLimit == null) {
            throw new IllegalArgumentException("Parameter is missing");
        }

        try {
            test.setGroupId(Integer.parseInt(nullableGroupId));
            test.setIsNecessary(Boolean.parseBoolean(nullableIsNecessary));
            test.setMaxAttempts(Integer.parseInt(nullableMaxAttempts));
            test.setDeadline(Date.valueOf(nullableDeadline));
            test.setTimeLimit(Integer.parseInt(nullableTimeLimit));
        } catch (NumberFormatException e) {
            System.err.println("Incorrect format of some parameters");
        }

        return new Tests();
    }

    public Tests addObject(final HttpServletRequest request) {
        Tests test = getTestFromRequest(request);
        return TESTS_DAO.addObject(test);

    }

    public Tests addTestsWithGroupsTests(final HttpServletRequest request) {
        Tests test = getTestForGroupFromRequest(request);
        return TESTS_DAO.addTestsWithGroupsTests(test);

    }

    public Tests addGroupsTests(final HttpServletRequest request) {
        Tests test = getTestForGroupFromRequest(request);
        return TESTS_DAO.addGroupsTests(test);

    }

    public List<Tests> getAll() {
        return TESTS_DAO.getAll();
    }

    public List<Tests> getAllTestsForGroupsByTestId(final HttpServletRequest request) {
        final String nullableTestId = request.getParameter("testId");
        List<Tests> testsList = new ArrayList<>();
        if (nullableTestId == null) {
            throw new IllegalArgumentException("Parameter is missing");
        }

        try {
            int testId = Integer.parseInt(nullableTestId);
            if (!TESTS_DAO.getById(testId).isPresent()) {
                throw new IllegalArgumentException("This test doesn't exist");
            }
            testsList = TESTS_DAO.getAllTestsForGroupsByTestId(testId);
        } catch (NumberFormatException e) {
            System.err.println("Incorrect format of test id");
        }

        return testsList;
    }

    public List<Tests> getAllTestsForGroupsByGroupId(final HttpServletRequest request) {
        final String nullableTestId = request.getParameter("groupId");
        List<Tests> testsList = new ArrayList<>();
        if (nullableTestId == null) {
            throw new IllegalArgumentException("Parameter is missing");
        }

        try {
            int groupId = Integer.parseInt(nullableTestId);

            // Проверяем наличие группы по id. Если не существует - выкидываем исключение

            testsList = TESTS_DAO.getAllTestsForGroupsByGroupId(groupId);
        } catch (NumberFormatException e) {
            System.err.println("Incorrect format of group id");
        }

        return testsList;
    }

    public Tests getById(final HttpServletRequest request) {
        final String nullableTestId = request.getParameter("testId");
        Tests test = new Tests();
        if (nullableTestId == null) {
            throw new IllegalArgumentException("Parameter is missing");
        }

        try {
            int testId = Integer.parseInt(nullableTestId);
            Optional<Tests> testsOptional = TESTS_DAO.getById(testId);
            if (!testsOptional.isPresent()) {
                throw new IllegalArgumentException("This test doesn't exist");
            }
            test = testsOptional.get();
        } catch (NumberFormatException e) {
            System.err.println("Incorrect format of test id");
        }
        return test;
    }

    public Tests getObjectByTestAndGroupId(final HttpServletRequest request) {
        final String nullableTestId = request.getParameter("testId");
        final String nullableGroupId = request.getParameter("groupId");
        Tests test = new Tests();
        if (nullableTestId == null || nullableGroupId == null) {
            throw new IllegalArgumentException("Parameter is missing");
        }

        try {
            int testId = Integer.parseInt(nullableTestId);
            int groupId = Integer.parseInt(nullableGroupId);
            Optional<Tests> testsOptional = TESTS_DAO.getObjectByTestAndGroupId(testId, groupId);
            // Пороверяем по тесту и по группе
            if (!testsOptional.isPresent()) {
                throw new IllegalArgumentException("This test doesn't exist");
            }
            test = testsOptional.get();
        } catch (NumberFormatException e) {
            System.err.println("Incorrect format of id");
        }
        return test;
    }

    public void updateTestByObject(final HttpServletRequest request) {
        Tests test = getTestFromRequest(request);
        TESTS_DAO.updateByObject(test);
    }

    public void updateTestForGroupByObject(final HttpServletRequest request) {
        Tests test = getTestForGroupFromRequest(request);
        TESTS_DAO.updateByObject(test);
    }

    public void removeTestById(final HttpServletRequest request) {
        final String nullableTestId = request.getParameter("testId");

        if (nullableTestId == null) {
            throw new IllegalArgumentException("Parameter is missing");
        }
        try {
            int testId = Integer.parseInt(nullableTestId);
            if (!TESTS_DAO.getById(testId).isPresent()) {
                throw new IllegalArgumentException("This test doesn't exist");
            }
            TESTS_DAO.removeById(testId);
        } catch (NumberFormatException e) {
            System.err.println("Incorrect format of test id");
        }
    }

    public void removeGroupsTestsByTestId(final HttpServletRequest request) {
        final String nullableTestId = request.getParameter("testId");

        if (nullableTestId == null) {
            throw new IllegalArgumentException("Parameter is missing");
        }
        try {
            int testId = Integer.parseInt(nullableTestId);
            //Проверяем на группу и тест
            if (!TESTS_DAO.getById(testId).isPresent()) {
                throw new IllegalArgumentException("This test doesn't exist");
            }
            TESTS_DAO.removeGroupsTestsByTestId(testId);
        } catch (NumberFormatException e) {
            System.err.println("Incorrect format of test id");
        }
    }

    public void removeGroupsTestsByGroupId(final HttpServletRequest request) {
        final String nullableGroupId = request.getParameter("groupId");

        if (nullableGroupId == null) {
            throw new IllegalArgumentException("Parameter is missing");
        }
        try {
            int groupId = Integer.parseInt(nullableGroupId);
            //Проверяем на группу
            TESTS_DAO.removeGroupsTestsByTestId(groupId);
        } catch (NumberFormatException e) {
            System.err.println("Incorrect format of group id");
        }
    }

    public void removeGroupsTestsByTestAndGroupId(final HttpServletRequest request) {
        final String nullableTestId = request.getParameter("testId");
        final String nullableGroupId = request.getParameter("groupId");

        if (nullableGroupId == null || nullableTestId == null) {
            throw new IllegalArgumentException("Parameter is missing");
        }
        try {
            int groupId = Integer.parseInt(nullableGroupId);
            int testId = Integer.parseInt(nullableTestId);
            //Проверяем на группу и тест
            if (!TESTS_DAO.getById(testId).isPresent()) {
                throw new IllegalArgumentException("This test doesn't exist");
            }
            TESTS_DAO.removeGroupsTestsByTestAndGroupId(testId, groupId);
        } catch (NumberFormatException e) {
            System.err.println("Incorrect format of id");
        }
    }

}
