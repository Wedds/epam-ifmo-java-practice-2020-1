package com.ifmo.epampractice.service;

import java.sql.*;
import java.util.List;

public class DatabaseService {
    private static volatile DatabaseService instance;
    private Connection conn;

    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbs:postgresql://localhost:5432/testing_system_db";
    private static final String DB_USERNAME = "esclide";
    private static final String DB_PASSWORD = "Pa$$w0rd";

    public Connection getConnection(){
        return conn;
    }

    public DatabaseService() {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(
                    DB_URL,
                    DB_USERNAME, DB_PASSWORD);
            System.out.println("Connection established");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection error");
        }
    }

    public static DatabaseService getInstance() {
        DatabaseService localInstance = instance;
        if (localInstance == null) {
            synchronized (DatabaseService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DatabaseService();
                }
            }
        }
        return localInstance;
    }
}
