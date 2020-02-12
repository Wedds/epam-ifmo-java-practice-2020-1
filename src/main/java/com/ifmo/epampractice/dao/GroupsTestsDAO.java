package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.Groups;
import com.ifmo.epampractice.entity.GroupsTests;
import com.ifmo.epampractice.service.DatabaseService;
import com.ifmo.epampractice.service.DatabaseSource;

import java.util.List;

public class GroupsTestsDAO extends DatabaseSource implements DatabaseService<GroupsTests> {

    @Override
    public void add(GroupsTests groupTest) {
    }

    @Override
    public List<GroupsTests> getAll() {
        return null;
    }

    @Override
    public GroupsTests getById(int id) {
        return null;
    }

    @Override
    public void update(GroupsTests groupTest) {
    }

    @Override
    public void remove(GroupsTests groupTest) {
    }

    public GroupsTests getByGroupAndTestId(int groupId, int testId) {
        return null;
    }


}
