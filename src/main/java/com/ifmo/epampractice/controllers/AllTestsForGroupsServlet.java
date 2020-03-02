package com.ifmo.epampractice.controllers;

import com.ifmo.epampractice.entity.Tests;
import com.ifmo.epampractice.service.GroupsService;
import com.ifmo.epampractice.service.SubjectsService;
import com.ifmo.epampractice.service.TestsService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AllTestsForGroupsServlet extends HttpServlet {
    private static final TestsService TESTS_SERVICE = new TestsService();
    private static final GroupsService GROUPS_SERVICE  = new GroupsService();
    private static final SubjectsService SUBJECTS_SERVICE = new SubjectsService();


    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException {
        RequestDispatcher view = req.getRequestDispatcher("tests/test_group_all.jsp");
        try {
            int userId = 2;
            List<Tests> testsForGroupsList = TESTS_SERVICE.getAllTestsForGroupByCreatorId(userId);
            List<Tests> testsList = TESTS_SERVICE.getAllTestsByCreatorId(userId);
            Map<Integer, String> groupsDict =
                    GROUPS_SERVICE.getDictionaryWithGroupTitleAndGroupId();
            Map<Integer, String> subjectDict = SUBJECTS_SERVICE.getDictionaryWithSubjectTitleAndSubjectId();
            req.setAttribute("subjectDict", subjectDict);
            req.setAttribute("groupsDict", groupsDict);
            req.setAttribute("testsForGroupsList", testsForGroupsList);
            req.setAttribute("testsList", testsList);

            List<Integer> subjectIdList = new ArrayList<>();
            for (Tests test:testsList){
                if (!subjectIdList.contains(test.getSubjectId())){
                    subjectIdList.add(test.getSubjectId());
                }
            }
            req.setAttribute("subjectIdList", subjectIdList);

            view.forward(req, resp);

        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot forward to file.", e);
        }
    }
}
