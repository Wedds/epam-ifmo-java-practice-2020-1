package com.ifmo.epampractice.controllers;

import com.ifmo.epampractice.entity.Tests;
import com.ifmo.epampractice.entity.Users;
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

public class TestsResultsServlet extends HttpServlet {
    private static final TestsService TESTS_SERVICE = new TestsService();
    private static final UsersService USERS_SERVICE = new UsersService();

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException {
        RequestDispatcher view = req.getRequestDispatcher("tests/all.jsp");
        try {
            int userId = 3;
            int groupId = USERS_SERVICE.getById(3).getGroupId();
            List<Tests> testsList = TESTS_SERVICE.getAllTestsForGroupsByGroupId(groupId);
            Map<Integer, String> subjectDict = TESTS_SERVICE.getDictionaryWithSubjectTitleAndSubjectIdByGroupId(groupId);
            Map<Integer, Integer> userMaxScoreDict = TESTS_SERVICE.getDictionaryWithTestIdAndMaxScoreByUserId(userId);
            Map<Integer, Integer> attemptsCountDict = TESTS_SERVICE.getDictionaryWithTestIdAndAttemptsLeftCountByUserId(userId);
            req.setAttribute("subjectDict", subjectDict);
            req.setAttribute("attemptsCountDict", attemptsCountDict);
            req.setAttribute("maxScoreDict", userMaxScoreDict);
            req.setAttribute("testsList", testsList);
            view.forward(req, resp);

        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot forward to file.", e);
        }
    }
}