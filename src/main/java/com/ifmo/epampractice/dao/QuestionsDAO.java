package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.GroupsTests;
import com.ifmo.epampractice.entity.Questions;
import com.ifmo.epampractice.service.DatabaseService;
import com.ifmo.epampractice.service.DatabaseSource;

import java.util.List;

public class QuestionsDAO extends DatabaseSource implements DatabaseService<Questions> {


    @Override
    public void add(Questions object) {

    }

    @Override
    public List<Questions> getAll() {
        return null;
    }

    @Override
    public Questions getById(int id) {
        return null;
    }

    @Override
    public void update(Questions object) {

    }

    @Override
    public void remove(Questions object) {

    }
}
