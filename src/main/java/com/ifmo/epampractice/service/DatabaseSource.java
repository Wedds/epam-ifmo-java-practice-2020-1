package com.ifmo.epampractice.service;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public final class DatabaseSource {
    private static volatile DatabaseSource instance;
    private final ComboPooledDataSource dataSource;

    private DatabaseSource() {

        PropertiesService props = new PropertiesService("database.properties");

        dataSource = new ComboPooledDataSource();

        String jdbcUrl = props.getProperty("dbProto") + props.getProperty("serverName") +
                "/" + props.getProperty("databaseName");

        try {
            dataSource.setDriverClass(props.getProperty("databaseDriver"));
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUser(props.getProperty("user"));
        dataSource.setPassword(props.getProperty("password"));

    }

    public static DatabaseSource getInstance() {
        if (instance != null) {
            return instance;
        }
        synchronized (DatabaseSource.class) {
            if (instance == null) {
                instance = new DatabaseSource();
            }
            return instance;
        }
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
