package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.GroupsTests;

import java.util.List;

public interface GroupsTestsDAO {

    //create
    void add(GroupsTests groupTest);

    //read
    List<GroupsTests> getAll();

    GroupsTests getById(int id);

    GroupsTests getByGroupAndTestId(int groupId, int testId);

    //update
    void update (GroupsTests groupTest);

    //delete
    void remove(GroupsTests groupTest);
}
