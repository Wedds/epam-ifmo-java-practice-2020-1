package com.ifmo.epampractice.controllers;

import  com.ifmo.epampractice.entity.Groups;
import  com.ifmo.epampractice.service.GroupsService;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GroupsServlet extends HttpServlet {
    private static final GroupsService groupsService = GroupsService.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Groups> groupsList = groupsService.getAll();
        request.setAttribute("groupsList", groupsList);
        request.getRequestDispatcher("groups/all.jsp").forward(request, response);
    }
}
