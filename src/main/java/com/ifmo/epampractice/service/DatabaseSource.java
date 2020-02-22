package com.ifmo.epampractice.service;

import java.sql.*;

public class DatabaseSource {
    private static volatile DatabaseSource instance;
    private Connection conn;

    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/testing_system_db";
    private static final String DB_USERNAME = "evangelion";
    private static final String DB_PASSWORD = "12345";

    public Connection getConnection() {
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
        return conn;
    }


    public static DatabaseSource getInstance() {
        DatabaseSource localInstance = instance;
        if (localInstance == null) {
            synchronized (DatabaseSource.class) {
                localInstance = instance;
                if (localInstance == null) {
                    localInstance = new DatabaseSource();
                    instance = localInstance;
                }
            }
        }
        return localInstance;
    }
}
