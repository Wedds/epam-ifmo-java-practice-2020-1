import com.ifmo.epampractice.entity.Groups;
import com.ifmo.epampractice.dao.GroupsDAO;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;

public class testCRUD {
    @Test
    public void main() throws Exception{
        /* Initialization & Setting expected values*/
        Groups expectedGroup1 = new Groups();
        expectedGroup1.setId(1);
        expectedGroup1.setName("K3120");
        expectedGroup1.setCreatedAt(Date.valueOf(LocalDate.of(2020, 1, 3)));
        Groups expectedGroup2 = new Groups();
        expectedGroup2.setName("AAA");
        expectedGroup2.setCreatedAt(Date.valueOf(LocalDate.of(2017, 10, 10)));
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
