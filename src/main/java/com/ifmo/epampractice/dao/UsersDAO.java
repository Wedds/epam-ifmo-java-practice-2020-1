package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.Tests;
import com.ifmo.epampractice.entity.Users;
import com.ifmo.epampractice.service.DatabaseService;
import com.ifmo.epampractice.service.DatabaseSource;

import java.sql.SQLException;
import java.util.List;

public class UsersDAO extends DatabaseSource implements DatabaseService<Users> {


    @Override
    public void add(Users object) throws SQLException {

    }

    @Override
    public List<Users> getAll() throws SQLException {
        return null;
    }

    @Override
    public Users getById(int id) throws SQLException {
        return null;
    }

    @Override
    public void update(Users object) throws SQLException {

    }

    @Override
    public void remove(Users object) throws SQLException {

    }
}
