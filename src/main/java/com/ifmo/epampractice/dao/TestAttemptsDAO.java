package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.TestAttempts;

import java.util.List;

public interface TestAttemptsDAO {

    //create
    void add(TestAttempts testAttempt);

    //read
    List<TestAttempts> getAll();

    TestAttempts getById(int id);

    //update
    void update (TestAttempts testAttempt);

    //delete
    void remove(TestAttempts testAttempt);


}
