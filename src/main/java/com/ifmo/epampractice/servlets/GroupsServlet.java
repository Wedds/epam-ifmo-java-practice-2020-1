package com.ifmo.epampractice.servlets;

import  com.ifmo.epampractice.service.GroupsService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GroupsServlet extends HttpServlet {
    private static final GroupsService groupsService = new GroupsService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        final String idString = request.getParameter("id");
        final String nameString = request.getParameter("name");
        final String createdAtString = request.getParameter("createdAt");


    }
}
