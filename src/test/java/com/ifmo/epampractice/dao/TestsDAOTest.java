package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.Tests;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Optional;


public class TestsDAOTest {
    private static final TestsDAO TEST_DAO = new TestsDAO();

    public Tests createTestsObject() {
        Tests test = new Tests();
        test.setTitle("Test test");
        test.setDescription("First test test");
        test.setSubjectId(2);
        test.setIsRandom(Boolean.FALSE);
        java.sql.Date date = java.sql.Date.valueOf("2020-02-02");
        test.setCreatedAt(date);
        test.setMaxPoints(20);
        test.setCreatorId(2);
        return test;
    }

    public Tests createGroupsTestsObject() {
        Tests test = createTestsObject();
        test.setGroupId(1);
        test.setIsNecessary(Boolean.TRUE);
        test.setMaxAttempts(2);
        java.sql.Date deadline = java.sql.Date.valueOf("2020-03-03");
        test.setDeadline(deadline);
        test.setTimeLimit(30);
        java.sql.Date date = java.sql.Date.valueOf("2020-02-02");
        test.setCreatedAt(date);
        test.setMaxPoints(20);
        test.setCreatorId(2);
        return test;
    }

    public Tests createGroupsTestsObjectForUpdate() {
        Tests test = new Tests();
        test.setTitle("Update test");
        test.setDescription("OLOLOLOLO");
        test.setSubjectId(3);
        test.setIsRandom(Boolean.TRUE);
        java.sql.Date birthday = java.sql.Date.valueOf("2022-02-02");
        test.setCreatedAt(birthday);
        test.setMaxPoints(10);
        test.setCreatorId(3);
        test.setGroupId(1);
        test.setIsNecessary(Boolean.FALSE);
        test.setMaxAttempts(2);
        java.sql.Date deadline = java.sql.Date.valueOf("2021-04-01");
        test.setDeadline(deadline);
        test.setTimeLimit(30);
        java.sql.Date date = java.sql.Date.valueOf("2020-02-01");
        test.setCreatedAt(date);
        test.setMaxPoints(20);
        test.setCreatorId(2);
        return test;
    }

    @Test
    public void addObject() {
        Boolean controlSum;
        Tests test = createTestsObject();
        test = TEST_DAO.addObject(test);
        controlSum = TEST_DAO.getById(test.getId()).isPresent();
        Assert.assertEquals(Boolean.TRUE, controlSum);
    }

    @Test
    public void addTestsWithGroupsTests() {
        Boolean controlSum;
        Tests test = createGroupsTestsObject();
        test = TEST_DAO.addTestsWithGroupsTests(test);
        Optional<Tests> testOptional = TEST_DAO.getObjectByTestAndGroupId(test.getId(), test.getGroupId());
        if (testOptional.isPresent()){
            controlSum = Boolean.TRUE;
        } else {
            controlSum = Boolean.FALSE;
        }
        Assert.assertEquals(Boolean.TRUE, controlSum);
    }

    @Test
    public void addGroupsTests() {
        Boolean controlSum;
        Tests test = createGroupsTestsObject();
        test = TEST_DAO.addObject(test);
        test = TEST_DAO.addGroupsTests(test);
        controlSum = TEST_DAO.getObjectByTestAndGroupId(test.getId(), test.getGroupId()).isPresent();
        Assert.assertEquals(Boolean.TRUE, controlSum);
    }

    @Test
    public void updateByObject() {
        Boolean controlSum;
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
    public void removeById() {
        Boolean controlSum;
        Tests test = createGroupsTestsObject();
        test = TEST_DAO.addTestsWithGroupsTests(test);
        int id = test.getId();
        TEST_DAO.removeById(id);
        controlSum = TEST_DAO.getById(id).isPresent() | !(TEST_DAO.getAllGroupsTestsByTestId(id).isEmpty());
        Assert.assertEquals(Boolean.FALSE, controlSum);
    }

    @Test
    public void fillObjectByGroupId() {
        Boolean controlSum;
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
    public void getById() {
        Boolean controlSum;
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
    public void getAll() {
        Tests test = createTestsObject();
        test = TEST_DAO.addObject(test);
        List<Tests> testsList = TEST_DAO.getAll();
        Assert.assertFalse(testsList.isEmpty());
    }
}

