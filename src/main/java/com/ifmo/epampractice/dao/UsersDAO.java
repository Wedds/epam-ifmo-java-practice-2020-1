package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.Users;

import java.util.List;

public interface UsersDAO {

    //create
    void add(Users user);

    //read
    List<Users> getAll();

    Users getById(int id);

    //update
    void update (Users user);

    //delete
    void remove(Users user);

}
