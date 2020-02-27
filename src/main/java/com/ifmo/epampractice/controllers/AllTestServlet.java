package com.ifmo.epampractice.controllers;

import com.ifmo.epampractice.entity.Tests;
import com.ifmo.epampractice.service.TestsService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AllTestServlet extends HttpServlet {
    private static final TestsService TESTS_SERVICE = new TestsService();

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException {
        RequestDispatcher view = req.getRequestDispatcher("tests/test_group_all.jsp");
        try {
            int userId = 2;
            List <Tests> testsList = TESTS_SERVICE.getAllTestsByCreatorId(userId);
            view.forward(req, resp);

        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot forward to file.", e);
        }
    }
}
