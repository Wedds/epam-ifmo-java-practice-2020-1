package com.ifmo.epampractice;
import com.ifmo.epampractice.dao.TestsDAO;
import com.ifmo.epampractice.entity.Tests;

import java.sql.SQLException;
import java.util.List;


public class testClass {
    public static void main(String[] args) throws SQLException {
        System.out.println("Test1");
        TestsDAO testsDAO = new TestsDAO();
        List<Tests> testsList = testsDAO.getAll();
        for (Tests test: testsList){
            System.out.println(test);
        }
        System.out.println("Test2");
        Tests test =  testsDAO.getById(3);
        System.out.println(test);

    }
}
