package com.ifmo.epampractice.controllers;

import com.ifmo.epampractice.entity.Tests;
import com.ifmo.epampractice.service.SubjectsService;
import com.ifmo.epampractice.service.TestsService;
import com.ifmo.epampractice.service.UsersService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AllTestsServlet extends HttpServlet {
    private static final TestsService TESTS_SERVICE = new TestsService();
    private static final UsersService USERS_SERVICE = new UsersService();
    private static final SubjectsService SUBJECTS_SERVICE = new SubjectsService();

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException {
        RequestDispatcher view = req.getRequestDispatcher("tests/all_tests.jsp");
        try {
            int userId = 2;
            List<Tests> testsList = TESTS_SERVICE.getAllTestsByCreatorId(userId);
            Map<Integer, String> subjectDict = SUBJECTS_SERVICE.getDictionaryWithSubjectTitleAndSubjectId();
            Map<Integer, Integer> groupsCountDict = TESTS_SERVICE.getDictionaryWithTestIdAndGroupsCountByCreatorId(userId);
            req.setAttribute("subjectDict", subjectDict);
            req.setAttribute("groupsCountDict", groupsCountDict);
            req.setAttribute("testsList", testsList);
            view.forward(req, resp);

        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot forward to file.", e);
        }
    }
}