package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.Subjects;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import com.ifmo.epampractice.service.DatabaseSource;
import com.ifmo.epampractice.utilities.TestUtilities;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;


public class SubjectsDAOTest {

    private static final SubjectsDAO SUBJECTS_DAO = new SubjectsDAO();
    private static final String NAME = "Math";
    private static final String NAME_UPDATE = "History";

    @BeforeClass
    public static void initTestDb() {
        try (Connection connection = DatabaseSource.getInstance().getConnection();
             Statement statement = connection.createStatement();
        ) {
            TestUtilities.executeSqlFile(Paths.get("src","main", "resources", "Database_script_test.sql"), statement);
        } catch (SQLException e) {
            throw new IllegalArgumentException("Unable to create a test database.", e);
        }
    }

    public Subjects createSubjectsObject() {
        Subjects subject = new Subjects();
        subject.setName(NAME);
        return subject;
    }

    public Subjects createSubjectsObjectForUpdate() {
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
        SUBJECTS_DAO.removeById(subject.getId());
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
        Assert.assertEquals(Boolean.TRUE, receivedAnswer.equals(subject));
        SUBJECTS_DAO.removeById(subject.getId());
    }

    @Test
    public void testGetAll() {
        int wasElements = SUBJECTS_DAO.getAll().size();
        Subjects subject = createSubjectsObject();
        subject = SUBJECTS_DAO.addObject(subject);
        Assert.assertEquals(wasElements + 1, SUBJECTS_DAO.getAll().size());
        SUBJECTS_DAO.removeById(subject.getId());
    }
}
