package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.Tests;

import java.sql.SQLException;
import java.util.List;

public interface TestsDAO {

    //create
    void add(Tests test) throws SQLException;

    //read
    List<Tests> getAll();

    Tests getById(int id);

    //update
    void update (Tests test);

    //delete
    void remove(Tests test);

}
