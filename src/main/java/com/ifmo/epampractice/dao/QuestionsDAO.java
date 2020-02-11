package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.Questions;

import java.util.List;

public interface QuestionsDAO {

    //create
    void add(Questions questions);

    //read
    List<Questions> getAll();

    Questions getById(int id);

    Questions getByTestId(int testId);

    //update
    void update (Questions questions);

    //delete
    void remove(Questions questions);

}
