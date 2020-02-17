package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.Groups;
import com.ifmo.epampractice.service.IDAO;
import com.ifmo.epampractice.service.DatabaseSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class GroupsDAO extends DatabaseSource implements IDAO<Groups> {
    private static final String addQuery = "INSERT INTO groups(name, created_at) VALUES(?,?)";
    private static final String getAllQuery = "SELECT id, name, created_at FROM groups";
    private static final String getByIdQuery = "SELECT name, created_at FROM groups WHERE id=?";
    private static final String updateQuery = "UPDATE groups SET name=?, created_at=? WHERE id=?";
    private static final String removeQuery = "DELETE FROM groups WHERE id=?";

    @Override
    public void add(Groups group) {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(addQuery)) {
            preparedStatement.setString(1, group.getName());
            preparedStatement.setDate(2, group.getCreatedAt());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Groups> getAll() {
        List<Groups> groupsList = new ArrayList<>();

        try (Connection connection = getConnection(); Statement statement = connection.createStatement();) {
            ResultSet resultSet = statement.executeQuery(getAllQuery);

            while (resultSet.next()) {
                Groups group = new Groups();

                group.setId(resultSet.getInt("id"));
                group.setName(resultSet.getString("name"));
                group.setCreatedAt(resultSet.getDate("created_at"));
                groupsList.add(group);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groupsList;
    }

    @Override
    public Groups getById(int id) {
        Groups group = new Groups();

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement =connection.prepareStatement(getByIdQuery);) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            group.setId(id);
            group.setName(resultSet.getString("name"));
            group.setCreatedAt(resultSet.getDate("created_at"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return group;
    }

    @Override
    public void update(Groups group) {
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)){
            preparedStatement.setString(1, group.getName());
            preparedStatement.setDate(2, group.getCreatedAt());
            preparedStatement.setInt(3, group.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Groups group) {
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(removeQuery);) {
            preparedStatement.setInt(1, group.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
