package com.ifmo.epampractice.service;

import com.ifmo.epampractice.dao.SubjectsDAO;
import com.ifmo.epampractice.entity.Subjects;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class SubjectsService {
    private static final SubjectsDAO SUBJECTS_DAO = new SubjectsDAO();

    public Subjects getSubjectFromRequest(final HttpServletRequest request) {
        Subjects subject = new Subjects();
        final String nullableId = request.getParameter("id");
        final String nullableName = request.getParameter("name");

        if (nullableName == null) {
            System.err.println("Subject parameter is missing");
            throw new IllegalArgumentException("Parameter is missing");
        }

        try {

            subject.setName(nullableName);

            if (nullableId != null) {
                subject.setId(Integer.parseInt(nullableId));
            }
        } catch (NumberFormatException e) {
            System.err.println("Incorrect format of some parameters");
            throw new IllegalArgumentException("Incorrect format of parameters");
        }

        return new Subjects();
    }

    public Subjects addObject(final HttpServletRequest request) {
        Subjects subject = getSubjectFromRequest(request);
        return SUBJECTS_DAO.addObject(subject);
    }

    public List<Subjects> getAll() {
        return SUBJECTS_DAO.getAll();
    }

    public Subjects getById(final HttpServletRequest request) {
        final String nullableId = request.getParameter("id");
        Subjects subject = new Subjects();
        if (nullableId == null) {
            System.err.println("Subject parameter is missing");
            throw new IllegalArgumentException("Parameter is missing");
        }

        try {
            int subjectId = Integer.parseInt(nullableId);
            Optional<Subjects> subjectsOptional = SUBJECTS_DAO.getById(subjectId);
            if (!subjectsOptional.isPresent()) {
                System.err.println("This subject doesn't exist");
                throw new IllegalArgumentException("This subject doesn't exist");
            }
            subject = subjectsOptional.get();
        } catch (NumberFormatException e) {
            System.err.println("Incorrect format of subject id");
            throw new IllegalArgumentException("Incorrect format of id");
        }
        return subject;
    }

    public void updateByObject(final HttpServletRequest request) {
        Subjects subject = getSubjectFromRequest(request);
        SUBJECTS_DAO.updateByObject(subject);
    }

    public void removeById(final HttpServletRequest request) {
        final String nullableId = request.getParameter("id");

        if (nullableId == null) {
            System.err.println("Subject parameter is missing");
            throw new IllegalArgumentException("Subject is missing");
        }
        try {
            int subjectId = Integer.parseInt(nullableId);
            if (!SUBJECTS_DAO.getById(subjectId).isPresent()) {
                System.err.println("This subject doesn't exist");
                throw new IllegalArgumentException("This subject doesn't exist");
            }
            SUBJECTS_DAO.removeById(subjectId);
        } catch (NumberFormatException e) {
            System.err.println("Incorrect format of subject id");
            throw new IllegalArgumentException("Incorrect format of id");
        }
    }

    public Boolean ifSubjectObjectExist(final HttpServletRequest request) {
        final String nullableId = request.getParameter("id");
        if (nullableId == null) {
            System.err.println("Subject parameter is missing");
            throw new IllegalArgumentException("Parameter is missing");
        }

        try {
            if (SUBJECTS_DAO.getById(Integer.parseInt(nullableId)).isPresent()) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } catch (NumberFormatException e) {
            System.err.println("Incorrect format of subject id");
            throw new IllegalArgumentException("Incorrect format of id");
        }
    }
}
