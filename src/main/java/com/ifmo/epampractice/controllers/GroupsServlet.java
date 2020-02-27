package com.ifmo.epampractice.controllers;

import com.ifmo.epampractice.entity.Groups;
import com.ifmo.epampractice.service.GroupsService;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GroupsServlet extends HttpServlet {
    private static final GroupsService groupsService = new GroupsService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GroupsService groupsService = new GroupsService();
        List<Groups> groupsList = groupsService.getAll();
        request.setAttribute("groupsList", groupsList);
        request.getRequestDispatcher("/groups/all.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        final String idString = request.getParameter("id");
        final String nameString = request.getParameter("name");
        final String createdAtString = request.getParameter("createdAt");


    }
}
