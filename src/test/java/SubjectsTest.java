import com.ifmo.epampractice.entity.Subjects;
import com.ifmo.epampractice.dao.SubjectsDAO;

import java.util.List;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;

public class SubjectsTest {

    private static final SubjectsDAO SUBJECTS_DAO = new SubjectsDAO();
    private static final String NAME = "Math";
    private static final String NAME_UPDATE = "History";

    public Subjects createSubjectsObject(){
        Subjects subject = new Subjects();
        subject.setName(NAME);
        return subject;
    }

    public Subjects createSubjectsObjectForUpdate(){
        Subjects subject = new Subjects();
        subject.setName(NAME_UPDATE);
        return subject;
    }

    @Test
    public void testAddObject() {
        boolean controlSum;
        Subjects subject = createSubjectsObject();
        subject = SUBJECTS_DAO.addObject(subject);
        controlSum = SUBJECTS_DAO.getById(subject.getId()).isPresent();
        Assert.assertEquals(Boolean.TRUE, controlSum);
        int id = subject.getId();
        SUBJECTS_DAO.removeById(id);
    }

    @Test
    public void testUpdateByObject() {
        boolean controlSum;
        Subjects subjectBeforeUpdate = createSubjectsObject();
        subjectBeforeUpdate = SUBJECTS_DAO.addObject(subjectBeforeUpdate);
        int id = subjectBeforeUpdate.getId();
        Subjects subjectForUpdate = createSubjectsObjectForUpdate();
        subjectForUpdate.setId(id);
        SUBJECTS_DAO.updateByObject(subjectForUpdate);
        Optional<Subjects> subjectOptional = SUBJECTS_DAO.getById(id);
        Subjects controlSubject = new Subjects();
        if (subjectOptional.isPresent()) {
            controlSubject = subjectOptional.get();
        }
        if (controlSubject.equals(subjectForUpdate)) {
            controlSum = Boolean.TRUE;
        } else {
            controlSum = Boolean.FALSE;
        }
        Assert.assertEquals(Boolean.TRUE, controlSum);
        SUBJECTS_DAO.removeById(id);
    }

    @Test
    public void testRemoveById() {
        boolean controlSum;
        Subjects subject = createSubjectsObject();
        subject = SUBJECTS_DAO.addObject(subject);
        int id = subject.getId();
        SUBJECTS_DAO.removeById(id);
        controlSum = SUBJECTS_DAO.getById(id).isPresent();
        Assert.assertEquals(Boolean.FALSE, controlSum);
    }

    @Test
    public void testGetById() {
        boolean controlSum;
        Subjects subject = createSubjectsObject();
        subject = SUBJECTS_DAO.addObject(subject);
        Optional<Subjects> subjectOptional = SUBJECTS_DAO.getById(subject.getId());
        Subjects receivedAnswer = new Subjects();
        if (subjectOptional.isPresent()) {
            receivedAnswer = subjectOptional.get();
        }
        if (receivedAnswer.equals(subject)) {
            controlSum = Boolean.TRUE;
        } else {
            controlSum = Boolean.FALSE;
        }
        Assert.assertEquals(Boolean.TRUE, controlSum);
        int id = subject.getId();
        SUBJECTS_DAO.removeById(id);
    }

    @Test
    public void testGetAll() {
        Subjects subject = createSubjectsObject();
        subject = SUBJECTS_DAO.addObject(subject);
        List<Subjects> subjectsList = SUBJECTS_DAO.getAll();
        Assert.assertFalse(subjectsList.isEmpty());
        int id = subject.getId();
        SUBJECTS_DAO.removeById(id);
    }
}
