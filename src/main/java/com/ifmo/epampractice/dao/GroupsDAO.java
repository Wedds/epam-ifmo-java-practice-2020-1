package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.Groups;
import com.ifmo.epampractice.service.DatabaseService;
import com.ifmo.epampractice.service.DatabaseSource;

import java.util.List;

public class GroupsDAO extends DatabaseSource implements DatabaseService<Groups> {

    @Override
    public void add(Groups group) {
    }

    @Override
    public List<Groups> getAll() {
        return null;
    }

    @Override
    public Groups getById(int id) {
        return null;
    }

    @Override
    public void update(Groups group) {
    }

    @Override
    public void remove(Groups group) {
    }
}
