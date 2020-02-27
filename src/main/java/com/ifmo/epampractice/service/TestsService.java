package com.ifmo.epampractice.service;

import com.ifmo.epampractice.entity.Tests;
import java.util.List;
import java.util.ArrayList;

public class TestsService {
    private static TestsService instance;

    public static TestsService getInstance() {
        if (instance != null) {
            return instance;
        } else {
            instance = new TestsService();
        }
        return instance;
    }

    public List<Tests> getAllTestsForGroupsByGroupId(final int id) {
        return new ArrayList<Tests>();
    }
}
