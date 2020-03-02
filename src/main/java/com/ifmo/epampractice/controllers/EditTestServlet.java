package com.ifmo.epampractice.controllers;

import com.ifmo.epampractice.entity.Tests;
import com.ifmo.epampractice.service.SubjectsService;
import com.ifmo.epampractice.service.TestsService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

public class EditTestServlet extends HttpServlet {
    private static final TestsService TESTS_SERVICE = new TestsService();
    private static final SubjectsService SUBJECTS_SERVICE = new SubjectsService();

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException {
        RequestDispatcher view = req.getRequestDispatcher("tests/edit.jsp");
        try {
            Map<Integer, String> subjectDict = SUBJECTS_SERVICE.getDictionaryWithSubjectTitleAndSubjectId();
            req.setAttribute("subjectDict", subjectDict);
            String idString = req.getParameter("id");
            Tests test = new Tests();
            try {
                int id = Integer.parseInt(idString);
                test = TESTS_SERVICE.getById(id);

            } catch (Exception ignored){}
            req.setAttribute("test", test);
            req.setAttribute("success", Boolean.FALSE);
            view.forward(req, resp);
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot forward to file.", e);
        }
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException {
        RequestDispatcher view = req.getRequestDispatcher("tests/edit.jsp");
        try {
            req.setCharacterEncoding("UTF-8");
            String idString = req.getParameter("id");
            Tests test = new Tests();
            try {
                int id = Integer.parseInt(idString);
                test = TESTS_SERVICE.getById(id);
                test.setTitle(req.getParameter("title"));
                test.setDescription(req.getParameter("description"));
                test.setSubjectId(Integer.parseInt(req.getParameter("subject")));
                test.setIsRandom(Boolean.parseBoolean(req.getParameter("isRandom")));
                test.setMaxPoints(TESTS_SERVICE.getMaxScoreFromAnswersByTestId(id));
                TESTS_SERVICE.updateTestByObject(test);
            } catch (Exception e){
                e.printStackTrace();
                test.setTitle(req.getParameter("title"));
                test.setDescription(req.getParameter("description"));
                test.setSubjectId(Integer.parseInt(req.getParameter("subject")));
                test.setIsRandom(Boolean.parseBoolean(req.getParameter("isRandom")));
                test.setMaxPoints(0);
                test.setCreatedAt(LocalDateTime.now());
                test.setCreatorId(2);
                test = TESTS_SERVICE.addObject(test);
            }
            req.setAttribute("test", test);
            req.setAttribute("success", Boolean.TRUE);
            Map<Integer, String> subjectDict = SUBJECTS_SERVICE.getDictionaryWithSubjectTitleAndSubjectId();
            req.setAttribute("subjectDict", subjectDict);
            view.forward(req, resp);
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot forward to file.", e);
        }
    }
}
