package com.ifmo.epampractice.service;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public final class DatabaseSource {
    private static volatile DatabaseSource instance;
    ComboPooledDataSource cpds;

    private DatabaseSource() {

        PropertiesService props = new PropertiesService("database.properties");

        cpds = new ComboPooledDataSource();

        String jdbcUrl = props.getProperty("dbProto") + props.getProperty("serverName") +
                "/" + props.getProperty("databaseName");

        try {
            cpds.setDriverClass(props.getProperty("databaseDriver"));
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        cpds.setJdbcUrl(jdbcUrl);
        cpds.setUser(props.getProperty("user"));
        cpds.setPassword(props.getProperty("password"));

    }

    public static DatabaseSource getInstance() {
        DatabaseSource result = instance;
        if (result != null) {
            return result;
        }
        synchronized (DatabaseSource.class) {
            if (instance == null) {
                instance = new DatabaseSource();
            }
            return instance;
        }
    }

    public Connection getConnection() throws SQLException {
        return cpds.getConnection();
    }

    private void closeConnection(final Connection connection) throws SQLException {
        connection.close();
    }
}
