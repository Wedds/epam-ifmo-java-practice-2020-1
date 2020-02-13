package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.Groups;
import com.ifmo.epampractice.entity.Tests;
import com.ifmo.epampractice.service.DatabaseService;
import com.ifmo.epampractice.service.DatabaseSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupsDAO extends DatabaseSource implements DatabaseService<Groups> {

    @Override
    public void add(Groups group) throws SQLException {
        String query = "INSERT INTO groups(name, is_open) VALUES(?,?)";
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, group.getName());
            preparedStatement.setBoolean(2, group.isIs_open());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public List<Groups> getAll() throws SQLException {
        String query = "SELECT id, name, is_open FROM groups";
        Connection connection = getConnection();

        List<Groups> groupsList = new ArrayList<>();

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Groups group = new Groups();
                group.setId(resultSet.getInt("id"));
                group.setName(resultSet.getString("name"));
                group.setIs_open(resultSet.getBoolean("is_open"));
                groupsList.add(group);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return groupsList;
    }

    @Override
    public Groups getById(int id) throws SQLException {
        String query = "SELECT name, is_open FROM groups WHERE id=?";
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

        Groups group = new Groups();
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            group.setId(id);
            group.setName(resultSet.getString("name"));
            group.setIs_open(resultSet.getBoolean("is_open"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return group;
    }

    @Override
    public void update(Groups group) throws SQLException {
        String query = "UPDATE groups SET name=?, is_open=? WHERE id=?";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, group.getName());
            preparedStatement.setBoolean(2, group.isIs_open());
            preparedStatement.setInt(3, group.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void remove(Groups group) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM groups WHERE id=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, group.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
