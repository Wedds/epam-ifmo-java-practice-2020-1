package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.GroupsTests;
import com.ifmo.epampractice.entity.Subjects;
import com.ifmo.epampractice.service.DatabaseService;
import com.ifmo.epampractice.service.DatabaseSource;

import java.util.List;

public class SubjectsDAO extends DatabaseSource implements DatabaseService<Subjects> {


    @Override
    public void add(Subjects object) {

    }

    @Override
    public List<Subjects> getAll() {
        return null;
    }

    @Override
    public Subjects getById(int id) {
        return null;
    }

    @Override
    public void update(Subjects object) {

    }

    @Override
    public void remove(Subjects object) {

    }
}
