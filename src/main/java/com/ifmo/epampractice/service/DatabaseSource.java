package com.ifmo.epampractice.service;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public final class DatabaseSource {
    private static final String DATABASE_PROPERTIES = "database.properties";
    private static final String JDBC_URL = "jdbcUrl";
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static final String DATABASE_DRIVER = "databaseDriver";

    private static volatile DatabaseSource instance;
    private final ComboPooledDataSource dataSource;

    private DatabaseSource() {
        this.dataSource = getDataSource();
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
        return this.dataSource.getConnection();
    }

    private ComboPooledDataSource getDataSource() {
        ComboPooledDataSource pool = new ComboPooledDataSource();
        PropertiesService props = new PropertiesService(DATABASE_PROPERTIES);

        pool.setJdbcUrl(props.getProperty(JDBC_URL));
        pool.setUser(props.getProperty(USER));
        pool.setPassword(props.getProperty(PASSWORD));

        try {
            pool.setDriverClass(props.getProperty(DATABASE_DRIVER));
        } catch (PropertyVetoException e) {
            throw new IllegalArgumentException("Cannot set a database driver.", e);
        }
        return dataSource;
    }

    public void cleanUp() {
        this.dataSource.close();
    }
}
