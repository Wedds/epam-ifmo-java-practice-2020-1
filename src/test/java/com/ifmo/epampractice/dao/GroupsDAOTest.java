package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.Groups;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

public class GroupsDAOTest {
    @Test
    public void testCRUD() throws Exception {
        /* Initialization & Setting expected values*/
        final int year2017 = 2017;
        final int year2020 = 2020;
        final int day3 = 3;
        final int day10 = 10;
        final int hour20 = 20;
        final int minute38 = 38;
        final int seconds40 = 40;
        Groups expectedGroup1 = new Groups();
        expectedGroup1.setId(1);
        expectedGroup1.setName("K3120");
        expectedGroup1.setCreatedAt(LocalDateTime.of(year2020, Month.JANUARY, day3, hour20, minute38, seconds40));
        Groups expectedGroup2 = new Groups();
        expectedGroup2.setName("AAA");
        expectedGroup2.setCreatedAt(LocalDateTime.of(year2017, Month.NOVEMBER, day10, hour20, minute38, seconds40));
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
        expectedGroup2.setId(realGroup2.getId());
        Assert.assertEquals(expectedGroup2, realGroup2);

        /*Checking getAll */
        listGroups = groupDao.getAll();
        Assert.assertEquals(7, listGroups.size());
        Assert.assertEquals(expectedGroup1, listGroups.get(0));
        Assert.assertEquals(expectedGroup2, listGroups.get(realGroup2.getId() - 1));

        /*Checking removeById */
        groupDao.removeById(7);
        listGroups = groupDao.getAll();
        Assert.assertEquals(6, listGroups.size());
    }
}
