import com.ifmo.epampractice.entity.Groups;
import com.ifmo.epampractice.dao.GroupsDAO;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;

public class TestGroups {
    @Test
    public void testCRUD() throws Exception{
        /* Initialization & Setting expected values*/
        final int year2017 = 2017;
        final int year2020 = 2020;
        Groups expectedGroup1 = new Groups();
        expectedGroup1.setId(1);
        expectedGroup1.setName("K3120");
        expectedGroup1.setCreatedAt(Timestamp.valueOf("2020-02-10 10:56:20"));
        Groups expectedGroup2 = new Groups();
        expectedGroup2.setName("AAA");
        expectedGroup2.setCreatedAt(Timestamp.valueOf("2020-03-02 13:13:20"));
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
