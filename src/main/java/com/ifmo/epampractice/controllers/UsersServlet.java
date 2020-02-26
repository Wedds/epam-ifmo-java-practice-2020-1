package com.ifmo.epampractice.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UsersServlet extends HttpServlet {
    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException {
        RequestDispatcher view = req.getRequestDispatcher("users/all.jsp");
        try {
            view.forward(req, resp);
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot forward to file.", e);
        }
    }
}
