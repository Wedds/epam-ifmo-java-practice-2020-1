package com.ifmo.epampractice.controllers;

import  com.ifmo.epampractice.entity.Groups;
import  com.ifmo.epampractice.service.GroupsService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewGroupServlet extends HttpServlet {
    private static final GroupsService GROUPS_SERVICE = GroupsService.getInstance();

    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        int id;
        try {
            id = Integer.parseInt(request.getParameter("id"));
            Groups group = GROUPS_SERVICE.getById(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("group", group);
            request.getRequestDispatcher("view.jsp").forward(request, response);
        } catch (RuntimeException e) {
            /* Temporary */
            response.sendRedirect("/index.jsp");
        }
    }
}
