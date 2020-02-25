package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.Groups;
import com.ifmo.epampractice.service.DatabaseSource;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class GroupsDAO implements DAO<Groups> {
    private static final String ADD_QUERY = "INSERT INTO groups(name, created_at) VALUES(?,?) RETURNING id";
    private static final String GET_ALL_QUERY = "SELECT id, name, created_at FROM groups";
    private static final String GET_BY_ID_QUERY = "SELECT name, created_at FROM groups WHERE id=?";
    private static final String UPDATE_QUERY = "UPDATE groups SET name=?, created_at=? WHERE id=?";
    private static final String REMOVE_QUERY = "DELETE FROM groups WHERE id=?";

    @Override
    public Groups addObject(final Groups group) {
        try (Connection connection = DatabaseSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_QUERY,
                     Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, group.getName());
            preparedStatement.setObject(2, group.getCreatedAt());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("No group has been added");
            }
            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                if (keys.next()) {
                    group.setId(keys.getInt("id"));
                } else {
                    throw new RuntimeException("No id has been received");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return group;
    }

    @Override
    public List<Groups> getAll() {
        List<Groups> groupsList = new ArrayList<>();

        try (Connection connection = DatabaseSource.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL_QUERY);

            while (resultSet.next()) {
                Groups group = new Groups();

                group.setId(resultSet.getInt("id"));
                group.setName(resultSet.getString("name"));
                group.setCreatedAt(resultSet.getObject("created_at", LocalDateTime.class));
                groupsList.add(group);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groupsList;
    }

    @Override
    public Optional<Groups> getById(final int id) {
        Groups group = new Groups();

        try (Connection connection = DatabaseSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return Optional.empty();
            }
            group.setId(id);
            group.setName(resultSet.getString("name"));
            group.setCreatedAt(resultSet.getObject("created_at", LocalDateTime.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.of(group);
    }

    @Override
    public void updateByObject(final Groups group) {
        try (Connection connection = DatabaseSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, group.getName());
            preparedStatement.setObject(2, group.getCreatedAt());
            preparedStatement.setInt(3, group.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeById(final int id) {
        try (Connection connection = DatabaseSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_QUERY)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
