package com.ifmo.epampractice.controllers;

import  com.ifmo.epampractice.entity.Groups;
import  com.ifmo.epampractice.service.GroupsService;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AllGroupsServlet extends HttpServlet {
    private static final GroupsService GROUPS_SERVICE = GroupsService.getInstance();

    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        List<Groups> groupsList = GROUPS_SERVICE.getAll();
        request.setAttribute("groupsList", groupsList);
        request.getRequestDispatcher("all.jsp").forward(request, response);
    }
}

