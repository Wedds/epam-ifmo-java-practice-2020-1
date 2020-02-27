package com.ifmo.epampractice.service;

import com.ifmo.epampractice.entity.Users;
import java.util.List;
import java.util.ArrayList;

public class UsersService {
    private static UsersService instance;

    public static UsersService getInstance() {
        if (instance != null) {
            return instance;
        } else {
            instance = new UsersService();
        }
        return instance;
    }

    public List<Users> getAllByGroupId(final int id) {
        return new ArrayList<Users>();
    }
}
