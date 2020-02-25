package com.ifmo.epampractice.service;

import com.ifmo.epampractice.dao.GroupsDAO;
import com.ifmo.epampractice.entity.Groups;
import com.ifmo.epampractice.entity.Tests;
import com.ifmo.epampractice.entity.Users;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GroupsService {
    private static final GroupsDAO Groups_DAO = new GroupsDAO();

    public Groups getGroupFromRequest(final HttpServletRequest request) {
        Groups group = new Groups();
        final String nullableId = request.getParameter("id");
        final String nullableName = request.getParameter("name");
        final String nullableCreatedAt = request.getParameter("createdAt");

        if (nullableName == null || nullableCreatedAt == null) {
            System.err.println("Group parameter is missing");
            throw new IllegalArgumentException("Parameter is missing");
        }

        try {
            group.setName(nullableName.trim());
            group.setCreatedAt(LocalDateTime.parse(nullableCreatedAt.trim()));
            if (nullableId != null) {
                group.setId(Integer.parseInt(nullableId.trim()));
            }
        } catch (NumberFormatException e) {
            System.err.println("Incorrect format of some parameters");
            throw new IllegalArgumentException("Incorrect format of parameters");
        }

        return group;
    }

    public Groups addObject(final HttpServletRequest request) {
        Groups test = getGroupFromRequest(request);
        return Groups_DAO.addObject(test);

    }

    public List<Groups> getAll() {
        return Groups_DAO.getAll();
    }

    /* empty method */
    public List<Users> getAllUsersByGroupId(final HttpServletRequest request) {
        return new ArrayList<>();
    }

    /* empty method */
    public List<Groups> getAllGroupsForGroupsByGroupId(final HttpServletRequest request) {
        return new ArrayList<>();
    }

    public Groups getById(final HttpServletRequest request) {
        final String nullableId = request.getParameter("id");
        Groups group;
        
        if (nullableId == null) {
            System.err.println("Id parameter is missing");
            throw new IllegalArgumentException("Parameter is missing");
        }
        try {
            int id = Integer.parseInt(nullableId);
            Optional<Groups> GroupsOptional = Groups_DAO.getById(id);
            if (!GroupsOptional.isPresent()) {
                System.err.println("No such group has been found");
                throw new IllegalArgumentException("No such group has been found");
            }
            group = GroupsOptional.get();
        } catch (NumberFormatException e) {
            System.err.println("Incorrect format of group id");
            throw new IllegalArgumentException("Incorrect format of id");
        }
        return group;
    }
    
    public void updateTestByObject(final HttpServletRequest request) {
        Groups test = getGroupFromRequest(request);
        Groups_DAO.updateByObject(test);
    }

    public void removeTestById(final HttpServletRequest request) {
        final String nullableId = request.getParameter("id");

        if (nullableId == null) {
            System.err.println("Id parameter is missing");
            throw new IllegalArgumentException("Parameter is missing");
        }
        try {
            int id = Integer.parseInt(nullableId);
            if (!Groups_DAO.getById(id).isPresent()) {
                System.err.println("No such group has been found");
                throw new IllegalArgumentException("No such group has been found");
            }
            Groups_DAO.removeById(id);
        } catch (NumberFormatException e) {
            System.err.println("Incorrect format of group id");
            throw new IllegalArgumentException("Incorrect format of id");
        }
    }

    public Boolean ifTestObjectExist(final HttpServletRequest request) {
        final String nullableId = request.getParameter("id");
        if (nullableId == null) {
            System.err.println("Group parameter is missing");
            throw new IllegalArgumentException("Parameter is missing");
        }
        try {
            return Groups_DAO.getById(Integer.parseInt(nullableId)).isPresent();
        } catch (NumberFormatException e) {
            System.err.println("No group with such id exists");
            throw new IllegalArgumentException("Incorrect format of id");
        }
    }
}