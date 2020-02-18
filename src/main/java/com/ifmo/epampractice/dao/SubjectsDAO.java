package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.Subjects;
import com.ifmo.epampractice.service.IDAO;
import com.ifmo.epampractice.service.DatabaseSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectsDAO extends DatabaseSource implements IDAO<Subjects> {

    private static final String INSERT_QUERY = "INSERT INTO SUBJECTS(id, name) VALUES(?,?)";
    private static final String SELECT_ALL_QUERY = "SELECT id, name FROM SUBJECTS";
    private static final String SELECT_BY_ID_QUERY = "SELECT id, name FROM SUBJECTS WHERE id=?";
    private static final String UPDATE_QUERY = "UPDATE SUBJECTS SET id=?, name=? WHERE id=?";
    private static final String REMOVE_QUERY = "DELETE FROM SUBJECTS WHERE id=?";


    @Override
    public void add(Subjects subject) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            try {
                preparedStatement.setInt(1, subject.getId());
                preparedStatement.setString(2, subject.getName());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Subjects> getAll() throws SQLException {
        List<Subjects> subjectsList = new ArrayList<>();
        try (Connection connection = getConnection(); Statement statement = connection.createStatement();) {
            try {
                ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);
                while (resultSet.next()) {
                    Subjects subject = new Subjects();
                    subject.setId(resultSet.getInt("id"));
                    subject.setName(resultSet.getString("name"));
                    subjectsList.add(subject);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return subjectsList;
    }


    @Override
    public Subjects getById(int id) throws SQLException {
        Subjects subject = new Subjects();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY);) {
            try {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                subject.setId(id);
                subject.setName(resultSet.getString("name"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return subject;
    }

    @Override
    public void update(Subjects subject) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            try {
                preparedStatement.setInt(1, subject.getId());
                preparedStatement.setString(2, subject.getName());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removeByObject(Subjects subject) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_QUERY)) {
            try {
                preparedStatement.setInt(1, subject.getId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removeById(int id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_QUERY)) {
            try {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
