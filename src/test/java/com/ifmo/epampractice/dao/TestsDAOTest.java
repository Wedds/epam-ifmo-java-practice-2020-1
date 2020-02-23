package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.Tests;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.sql.Date;


public class TestsDAOTest {
    private static final TestsDAO TEST_DAO = new TestsDAO();
    private static final String TEST_TITLE = "Test test";
    private static final String TEST_DESCRIPTION = "First test test";
    private static final int SUBJECT_ID = 2;
    private static final boolean IS_RANDOM = Boolean.FALSE;
    private static final Date CREATION_DATE = Date.valueOf("2020-02-02");
    private static final int TEST_MAX_POINTS = 20;
    private static final int CREATOR_ID = 2;
    private static final int GROUP_ID = 1;
    private static final boolean IS_NECESSARY = Boolean.TRUE;
    private static final int MAX_ATTEMPTS = 2;
    private static final Date DEADLINE = Date.valueOf("2020-03-03");
    private static final int TIME_LIMIT = 30;
    private static final String TEST_TITLE_UPDATE = "Update test";
    private static final String TEST_DESCRIPTION_UPDATE = "OLOLOLOLO";
    private static final int SUBJECT_ID_UPDATE = 3;
    private static final boolean IS_RANDOM_UPDATE = Boolean.TRUE;
    private static final Date CREATION_DATE_UPDATE = Date.valueOf("2022-02-02");
    private static final int TEST_MAX_POINTS_UPDATE = 10;
    private static final int CREATOR_ID_UPDATE = 3;
    private static final boolean IS_NECESSARY_UPDATE = Boolean.FALSE;
    private static final int MAX_ATTEMPTS_UPDATE = 2;
    private static final Date DEADLINE_UPDATE = Date.valueOf("2021-04-01");
    private static final int TIME_LIMIT_UPDATE = 30;


    public Tests createTestsObject() {
        Tests test = new Tests();
        test.setTitle(TEST_TITLE);
        test.setDescription(TEST_DESCRIPTION);
        test.setSubjectId(SUBJECT_ID);
        test.setIsRandom(IS_RANDOM);
        test.setCreatedAt(CREATION_DATE);
        test.setMaxPoints(TEST_MAX_POINTS);
        test.setCreatorId(CREATOR_ID);
        return test;
    }

    public Tests createGroupsTestsObject() {
        Tests test = createTestsObject();
        test.setGroupId(GROUP_ID);
        test.setIsNecessary(IS_NECESSARY);
        test.setMaxAttempts(MAX_ATTEMPTS);
        test.setDeadline(DEADLINE);
        test.setTimeLimit(TIME_LIMIT);
        return test;
    }

    public Tests createGroupsTestsObjectForUpdate() {
        Tests test = new Tests();
        test.setTitle(TEST_TITLE_UPDATE);
        test.setDescription(TEST_DESCRIPTION_UPDATE);
        test.setSubjectId(SUBJECT_ID_UPDATE);
        test.setIsRandom(IS_RANDOM_UPDATE);
        test.setCreatedAt(CREATION_DATE_UPDATE);
        test.setMaxPoints(TEST_MAX_POINTS_UPDATE);
        test.setCreatorId(CREATOR_ID_UPDATE);
        test.setGroupId(GROUP_ID);
        test.setIsNecessary(IS_NECESSARY_UPDATE);
        test.setMaxAttempts(MAX_ATTEMPTS_UPDATE);
        test.setDeadline(DEADLINE_UPDATE);
        test.setTimeLimit(TIME_LIMIT_UPDATE);
        return test;
    }

    @Test
    public void testAddObject() {
        boolean controlSum;
        Tests test = createTestsObject();
        test = TEST_DAO.addObject(test);
        controlSum = TEST_DAO.getById(test.getId()).isPresent();
        Assert.assertEquals(Boolean.TRUE, controlSum);
    }

    @Test
    public void testAddTestsWithGroupsTests() {
        boolean controlSum;
        Tests test = createGroupsTestsObject();
        test = TEST_DAO.addTestsWithGroupsTests(test);
        Optional<Tests> testOptional = TEST_DAO.getObjectByTestAndGroupId(test.getId(), test.getGroupId());
        if (testOptional.isPresent()) {
            controlSum = Boolean.TRUE;
        } else {
            controlSum = Boolean.FALSE;
        }
        Assert.assertEquals(Boolean.TRUE, controlSum);
    }

    @Test
    public void testAddGroupsTests() {
        boolean controlSum;
        Tests test = createGroupsTestsObject();
        test = TEST_DAO.addObject(test);
        test = TEST_DAO.addGroupsTests(test);
        controlSum = TEST_DAO.getObjectByTestAndGroupId(test.getId(), test.getGroupId()).isPresent();
        Assert.assertEquals(Boolean.TRUE, controlSum);
    }

    @Test
    public void testUpdateByObject() {
        boolean controlSum;
        Tests testBeforeUpdate = createGroupsTestsObject();
        testBeforeUpdate = TEST_DAO.addTestsWithGroupsTests(testBeforeUpdate);
        int testId = testBeforeUpdate.getId();
        Tests testForUpdate = createGroupsTestsObjectForUpdate();
        testForUpdate.setId(testId);
        TEST_DAO.updateByObject(testForUpdate);
        Optional<Tests> testOptional = TEST_DAO.getObjectByTestAndGroupId(testId, testBeforeUpdate.getGroupId());
        Tests controlTest = new Tests();
        if (testOptional.isPresent()) {
            controlTest = testOptional.get();
        }
        if (controlTest.equals(testForUpdate)) {
            controlSum = Boolean.TRUE;
        } else {
            controlSum = Boolean.FALSE;
        }
        Assert.assertEquals(Boolean.TRUE, controlSum);
    }

    @Test
    public void testRemoveById() {
        boolean controlSum;
        Tests test = createGroupsTestsObject();
        test = TEST_DAO.addTestsWithGroupsTests(test);
        int id = test.getId();
        TEST_DAO.removeById(id);
        controlSum = TEST_DAO.getById(id).isPresent() | !(TEST_DAO.getAllGroupsTestsByTestId(id).isEmpty());
        Assert.assertEquals(Boolean.FALSE, controlSum);
    }

    @Test
    public void testFillObjectByGroupId() {
        boolean controlSum;
        Tests testForInsert = createGroupsTestsObjectForUpdate();
        Tests testForCheck = TEST_DAO.addTestsWithGroupsTests(testForInsert);
        testForCheck = TEST_DAO.fillObjectByGroupId(testForCheck, testForInsert.getGroupId());

        Optional<Tests> controlTestOptional =
                TEST_DAO.getObjectByTestAndGroupId(testForCheck.getId(), testForCheck.getGroupId());
        Tests controlTest = new Tests();
        if (controlTestOptional.isPresent()) {
            controlTest = controlTestOptional.get();
        }
        if (controlTest.equals(testForCheck)) {
            controlSum = Boolean.TRUE;
        } else {
            controlSum = Boolean.FALSE;
        }
        Assert.assertEquals(Boolean.TRUE, controlSum);
    }

    @Test
    public void testGetById() {
        boolean controlSum;
        Tests test = createTestsObject();
        test = TEST_DAO.addObject(test);
        Optional<Tests> testOptional = TEST_DAO.getById(test.getId());
        Tests receivedTest = new Tests();
        if (testOptional.isPresent()) {
            receivedTest = testOptional.get();
        }
        if (receivedTest.equals(test)) {
            controlSum = Boolean.TRUE;
        } else {
            controlSum = Boolean.FALSE;
        }
        Assert.assertEquals(Boolean.TRUE, controlSum);
    }


    @Test
    public void testGetAll() {
        Tests test = createTestsObject();
        test = TEST_DAO.addObject(test);
        List<Tests> testsList = TEST_DAO.getAll();
        Assert.assertFalse(testsList.isEmpty());
    }
}

