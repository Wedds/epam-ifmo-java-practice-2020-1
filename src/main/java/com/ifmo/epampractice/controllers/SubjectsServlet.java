package com.ifmo.epampractice.controllers;

import com.ifmo.epampractice.entity.Subjects;
import com.ifmo.epampractice.service.SubjectsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SubjectsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SubjectsService subjects = SubjectsService.getInstance();
        List<Subjects> subjectList = subjects.getAll();
        request.setAttribute("subjectList", subjectList);
        request.getRequestDispatcher("/subjects/all.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}