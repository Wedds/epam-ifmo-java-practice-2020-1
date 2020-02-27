package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.Groups;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import com.ifmo.epampractice.service.DatabaseSource;
import com.ifmo.epampractice.utilities.TestUtilities;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


public class GroupsDAOTest {

    @BeforeClass
    public static void initTestDb() {
        try (Connection connection = DatabaseSource.getInstance().getConnection();
                Statement statement = connection.createStatement()) {
            TestUtilities.executeSqlFile(Paths.get("src", "test", "resources", "Database_script_test.sql"), statement);
            TestUtilities.executeSqlFile(Paths.get("src", "test", "resources", "Insert_test_script_H2.sql"), statement);
        } catch (SQLException e) {
            throw new IllegalArgumentException("Unable to create a test database.", e);
        }
    }


    @Test
    public void testCRUD() {
        final int year2017 = 2017;
        final int year2020 = 2020;
        final int day19 = 19;
        final int day17 = 17;
        final int hour13 = 13;
        final int minute10 = 10;
        final int seconds50 = 50;
        Groups expectedGroup1 = new Groups();
        expectedGroup1.setId(1);
        expectedGroup1.setName("K3120");
        expectedGroup1.setCreatedAt(LocalDateTime.of(year2020, Month.DECEMBER, day19, hour13, minute10, seconds50));
        Groups expectedGroup2 = new Groups();
        expectedGroup2.setName("AAA");
        expectedGroup2.setCreatedAt(LocalDateTime.of(year2017, Month.NOVEMBER, day17, hour13, minute10, seconds50));
        Groups realGroup1 = null;
        Groups realGroup2 = null;
        GroupsDAO groupDao = new GroupsDAO();
        List<Groups> listGroups = null;

        /* Checking getById */
        Optional<Groups> groupOptional = groupDao.getById(1);
        Assert.assertTrue(groupOptional.isPresent());
        realGroup1 = groupOptional.get();
        Assert.assertEquals(expectedGroup1, realGroup1);

        /* Checking addObject */
        realGroup2 = groupDao.addObject(expectedGroup2);
        expectedGroup2.setId(7);
        Assert.assertEquals(expectedGroup2, realGroup2);

        /*Checking getAll */
        listGroups = groupDao.getAll();
        Assert.assertEquals(7, listGroups.size());
        for (Groups group: listGroups) {
            if (group.getId() == 1) {
                realGroup1 = group;
            } else if (group.getId() == 7) {
                realGroup2 = group;
            }
        }
        Assert.assertEquals(expectedGroup1, realGroup1);
        Assert.assertEquals(expectedGroup2, realGroup2);

        /*Checking removeById */
        groupDao.removeById(7);
        listGroups = groupDao.getAll();
        Assert.assertEquals(6, listGroups.size());
    }
}
