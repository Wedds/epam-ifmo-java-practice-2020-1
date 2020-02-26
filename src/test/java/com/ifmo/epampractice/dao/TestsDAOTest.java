package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.Tests;

import com.ifmo.epampractice.service.DatabaseSource;
import com.ifmo.epampractice.utilities.TestUtilities;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;


public class TestsDAOTest {
    private static final TestsDAO TEST_DAO = new TestsDAO();
    private static final String TEST_TITLE = "Test test";
    private static final String TEST_DESCRIPTION = "First test test";
    private static final int SUBJECT_ID = 2;
    private static final boolean IS_RANDOM = Boolean.FALSE;
    private static final LocalDateTime CREATION_DATE = LocalDateTime.of(2020, Month.JULY, 9, 11, 6, 22);
    private static final int TEST_MAX_POINTS = 20;
    private static final int CREATOR_ID = 2;
    private static final int GROUP_ID = 1;
    private static final boolean IS_NECESSARY = Boolean.TRUE;
    private static final int MAX_ATTEMPTS = 2;
    private static final LocalDateTime DEADLINE = LocalDateTime.of(2021, Month.JULY, 1, 12, 6, 22);
    private static final int TIME_LIMIT = 30;
    private static final String TEST_TITLE_UPDATE = "Update test";
    private static final String TEST_DESCRIPTION_UPDATE = "OLOLOLOLO";
    private static final int SUBJECT_ID_UPDATE = 3;
    private static final boolean IS_RANDOM_UPDATE = Boolean.TRUE;
    private static final LocalDateTime CREATION_DATE_UPDATE = LocalDateTime.of(2020, Month.JULY, 4, 14, 6, 22);
    private static final int TEST_MAX_POINTS_UPDATE = 10;
    private static final int CREATOR_ID_UPDATE = 3;
    private static final boolean IS_NECESSARY_UPDATE = Boolean.FALSE;
    private static final int MAX_ATTEMPTS_UPDATE = 2;
    private static final LocalDateTime DEADLINE_UPDATE = LocalDateTime.of(2022, Month.JULY, 2, 12, 6, 22);
    private static final int TIME_LIMIT_UPDATE = 30;
    private static final int GROUP_ID_UPDATE = 4;

    @BeforeClass
    public static void initTestDb() {
        try (Connection connection = DatabaseSource.getInstance().getConnection();
             Statement statement = connection.createStatement()
        ) {
            TestUtilities.executeSqlFile(Paths.get("src", "test", "resources", "Database_script_test.sql"), statement);
            TestUtilities.executeSqlFile(Paths.get("src", "test", "resources", "Insert_test_script_H2.sql"), statement);
        } catch (SQLException e) {
            throw new IllegalArgumentException("Unable to create a test database.", e);
        }
    }


    @Test
    public void testAddObject() {
        boolean controlSum;
        Tests test = createTestsObject();
        test = TEST_DAO.addObject(test);
        controlSum = TEST_DAO.getById(test.getId()).isPresent();
        Assert.assertEquals(Boolean.TRUE, controlSum);
        TEST_DAO.removeById(test.getId());
    }

    @Test
    public void testAddTestsWithGroupsTests() {
        Tests test = createTestForGroupObject();
        test = TEST_DAO.addTestsWithGroupsTests(test);
        Optional<Tests> testOptional = TEST_DAO.getObjectByTestAndGroupId(test.getId(), test.getGroupId());
        Assert.assertEquals(Boolean.TRUE, testOptional.isPresent());
        TEST_DAO.removeById(test.getId());
    }

    @Test
    public void testAddGroupsTests() {
        boolean controlSum;
        Tests test = createTestForGroupObject();
        test = TEST_DAO.addObject(test);
        test = TEST_DAO.addGroupsTests(test);
        controlSum = TEST_DAO.getObjectByTestAndGroupId(test.getId(), test.getGroupId()).isPresent();
        Assert.assertEquals(Boolean.TRUE, controlSum);
        TEST_DAO.removeById(test.getId());
    }

    @Test
    public void testGetById() {
        Tests test = createTestsObject();
        test = TEST_DAO.addObject(test);
        Optional<Tests> testOptional = TEST_DAO.getById(test.getId());
        Tests receivedTest = new Tests();
        if (testOptional.isPresent()) {
            receivedTest = testOptional.get();
        }
        Assert.assertEquals(Boolean.TRUE, receivedTest.equals(test));
        TEST_DAO.removeById(test.getId());
    }

    @Test
    public void testGetAll() {
        int wasElements = TEST_DAO.getAll().size();
        Tests test = createTestsObject();
        test = TEST_DAO.addObject(test);
        Assert.assertEquals(wasElements + 1, TEST_DAO.getAll().size());
        TEST_DAO.removeById(test.getId());
    }

    @Test
    public void testGetAllTestsForGroupsByGroupId() {
        int wasElements = TEST_DAO.getAllTestsForGroupsByGroupId(1).size();
        Tests test = createTestForGroupObject();
        test = TEST_DAO.addTestsWithGroupsTests(test);
        Assert.assertEquals(wasElements + 1, TEST_DAO.getAllTestsForGroupsByGroupId(1).size());
        TEST_DAO.removeById(test.getId());
    }

    @Test
    public void testGetAllTestsForGroupsByTestId() {
        Tests test = createTestForGroupObject();
        test = TEST_DAO.addTestsWithGroupsTests(test);
        Tests groupsTests = fillTestsForGroup(test);
        int wasElements = TEST_DAO.getAllTestsForGroupsByTestId(test.getId()).size();
        TEST_DAO.addGroupsTests(groupsTests);
        Assert.assertEquals(wasElements + 1, TEST_DAO.getAllTestsForGroupsByTestId(test.getId()).size());
        TEST_DAO.removeById(test.getId());
    }

    @Test
    public void testUpdateByObject() {
        Tests testBeforeUpdate = createTestForGroupObject();
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
        Assert.assertEquals(Boolean.TRUE, controlTest.equals(testForUpdate));
        TEST_DAO.removeById(testForUpdate.getId());
    }

    @Test
    public void testRemoveById() {
        boolean controlSum;
        Tests test = createTestForGroupObject();
        test = TEST_DAO.addTestsWithGroupsTests(test);
        int id = test.getId();
        TEST_DAO.removeById(id);
        controlSum = TEST_DAO.getById(id).isPresent() | !(TEST_DAO.getAllTestsForGroupsByTestId(id).isEmpty());
        Assert.assertEquals(Boolean.FALSE, controlSum);
    }

    @Test
    public void testFillObjectByGroupId() {
        Tests testForInsert = createGroupsTestsObjectForUpdate();
        Tests testForCheck = TEST_DAO.addTestsWithGroupsTests(testForInsert);
        testForCheck = TEST_DAO.fillObjectByGroupId(testForCheck, testForInsert.getGroupId());

        Optional<Tests> controlTestOptional =
                TEST_DAO.getObjectByTestAndGroupId(testForCheck.getId(), testForCheck.getGroupId());
        Tests controlTest = new Tests();
        if (controlTestOptional.isPresent()) {
            controlTest = controlTestOptional.get();
        }
        Assert.assertEquals(Boolean.TRUE, controlTest.equals(testForCheck));
    }

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

    public Tests createTestForGroupObject() {
        Tests test = createTestsObject();
        test.setGroupId(GROUP_ID);
        test.setIsNecessary(IS_NECESSARY);
        test.setMaxAttempts(MAX_ATTEMPTS);
        test.setDeadline(DEADLINE);
        test.setTimeLimit(TIME_LIMIT);
        return test;
    }

    public Tests fillTestsForGroup(final Tests test) {
        test.setGroupId(GROUP_ID_UPDATE);
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
}

