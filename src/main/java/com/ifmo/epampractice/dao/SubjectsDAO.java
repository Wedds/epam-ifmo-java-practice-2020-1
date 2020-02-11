package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.Subjects;

import java.util.List;

public interface SubjectsDAO {

    //create
    void add(Subjects subject);

    //read
    List<Subjects> getAll();

    Subjects getById(int id);

    //update
    void update (Subjects subject);

    //delete
    void remove(Subjects subject);

}
