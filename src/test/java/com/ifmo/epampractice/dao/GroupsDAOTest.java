package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.Groups;
import com.ifmo.epampractice.dao.GroupsDAO;

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
        Groups expectedGroup1 = new Groups();
        expectedGroup1.setId(1);
        expectedGroup1.setName("K3120");
        expectedGroup1.setCreatedAt(LocalDateTime.of(2020, Month.DECEMBER, 11, 11, 6, 22));
        Groups expectedGroup2 = new Groups();
        expectedGroup2.setName("AAA");
        expectedGroup2.setCreatedAt(LocalDateTime.of(2020, Month.NOVEMBER, 9, 12, 6, 22));
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
        Assert.assertEquals(expectedGroup1, listGroups.get(0));
        Assert.assertEquals(expectedGroup2, listGroups.get(6));

        /*Checking removeById */
        groupDao.removeById(7);
        listGroups = groupDao.getAll();
        Assert.assertEquals(6, listGroups.size());
    }
}
