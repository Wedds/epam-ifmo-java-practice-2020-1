package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.TestAttemptAnswers;

import java.util.List;

public interface TestAttemptAnswersDAO {

    //create
    void add(TestAttemptAnswers testAttemptAnswer);

    //read
    List<TestAttemptAnswers> getAll();

    TestAttemptAnswers getById(int id);

    //update
    void update (TestAttemptAnswers testAttemptAnswer);

    //delete
    void remove(TestAttemptAnswers testAttemptAnswer);

}
