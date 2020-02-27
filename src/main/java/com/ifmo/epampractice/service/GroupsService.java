package com.ifmo.epampractice.service;

import com.ifmo.epampractice.dao.GroupsDAO;
import com.ifmo.epampractice.entity.Groups;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

public class GroupsService {
    private static final int[] LETTER_CHAR_BOUNDARIES = {'A', 'z'};
    private static final int[] DIGIT_CHAR_BOUNDARIES = {'0', '9'};
    private static final int MAX_LENGTH = 25;
    private static final String APPROVED_CHARS = " _-";
    private static final GroupsDAO GROUPS_DAO = new GroupsDAO();
    //private static final TestsService TESTS_SERVICE;
    //private static final UsersService USERS_SERVICE;

    public Groups addObject(final Groups group) {
        checkFields(group);
        return GROUPS_DAO.addObject(group);
    }

    public List<Groups> getAll() {
        return GROUPS_DAO.getAll();
    }

    public Groups getById(final int id) {
        Groups group;
        Optional<Groups> groupsOptional = GROUPS_DAO.getById(id);
        if (!groupsOptional.isPresent()) {
            System.err.println("No such group has been found");
            throw new IllegalArgumentException("No group with such id has been found");
        }
        group = groupsOptional.get();
        return group;
    }

    public void updateGroupByObject(final Groups group) {
        checkFields(group);
        GROUPS_DAO.updateByObject(group);
    }

    public void removeTestById(final int id) {
        if (!ifGroupsObjectExists(id)) {
            System.err.println("No such group has been found");
            throw new IllegalArgumentException("No group with such id has been found");
        }
        GROUPS_DAO.removeById(id);
    }

    public Groups getGroupWithTestsById(final int id) {
        Groups group;
        if (!ifGroupsObjectExists(id)) {
            System.err.println("No such group has been found");
            throw new IllegalArgumentException("No group with such id has been found");
        }
        group = getById(id);
        //group.setTestsList(TESTS_SERVICE.getAllTestsForGroupsByGroupId(id));
        return group;
    }

    public Groups getGroupWithUsersById(final int id) {
        Groups group;
        if (!ifGroupsObjectExists(id)) {
            System.err.println("No such group has been found");
            throw new IllegalArgumentException("No group with such id has been found");
        }
        group = getById(id);
        //group.setUsersList(USERS_SERVICE.getAllByGroupId(id));
        return group;
    }

    public Boolean ifGroupsObjectExists(final int id) {
        return GROUPS_DAO.getById(id).isPresent();
    }

    private void checkFields(final Groups group) {
        LocalDateTime currentLocalDateTime = LocalDateTime.now();
        LocalDateTime groupLocalDateTime = group.getCreatedAt();
        final String name = group.getName();

        for (int i = 0; i < name.length(); i++) {
            if ((name.charAt(i) < LETTER_CHAR_BOUNDARIES[0] && name.charAt(i) > LETTER_CHAR_BOUNDARIES[1])
                    && (name.charAt(i) < DIGIT_CHAR_BOUNDARIES[0] && name.charAt(i) > DIGIT_CHAR_BOUNDARIES[1])
                    && (APPROVED_CHARS.contains(String.valueOf(name.charAt(i))))) {
                System.err.println("Wrong Name parameter: unsupported chars have been found");
                throw new IllegalArgumentException("Wrong name parameter: unsupported chars have been found");
            } else if (name.length() > MAX_LENGTH) {
                System.err.println("Wrong Name parameter: length more than 25");
                throw new IllegalArgumentException("Wrong name parameter: length more than 25");
            }
        }
        if (currentLocalDateTime.isBefore(groupLocalDateTime)) {
            System.err.println("Wrong DateTime parameter: the moment wasn't achieved yet");
            throw new IllegalArgumentException("Wrong DateTime parameter: the moment wasn't achieved yet");
        }
    }
}
