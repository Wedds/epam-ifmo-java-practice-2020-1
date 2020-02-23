package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.Subjects;
import com.ifmo.epampractice.service.DAO;
import com.ifmo.epampractice.service.DatabaseSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubjectsDAO implements DAO<Subjects> {
    private static final String INSERT_QUERY = "INSERT INTO SUBJECTS(name) VALUES(?) RETURNING id";
    private static final String SELECT_ALL_QUERY = "SELECT id, name FROM SUBJECTS";
    private static final String SELECT_BY_ID_QUERY = "SELECT id, name FROM SUBJECTS WHERE id=?";
    private static final String UPDATE_QUERY = "UPDATE SUBJECTS SET name=? WHERE id=?";
    private static final String REMOVE_QUERY = "DELETE FROM SUBJECTS WHERE id=?";

    @Override
    public Subjects addObject(final Subjects subject) {
        try (Connection connection = DatabaseSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, subject.getId());
            preparedStatement.setString(2, subject.getName());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new IllegalArgumentException("Creating subject failed, no rows affected.");
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    subject.setId(generatedKeys.getInt(1));
                } else {
                    throw new IllegalArgumentException("Creating subject failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subject;
    }

    @Override
    public List<Subjects> getAll() {
        ArrayList<Subjects> subjectsArrayList = new ArrayList<>();
        try (Connection connection = DatabaseSource.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);
            while (resultSet.next()) {
                Subjects subject = new Subjects();
                subject.setId(resultSet.getInt("id"));
                subject.setName(resultSet.getString("name"));
                subjectsArrayList.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Getting all subjects failed. Subjects are not presented.");
        }
        return subjectsArrayList;
    }

    @Override
    public Optional<Subjects> getById(final int id) {
        Subjects subject = new Subjects();
        subject.setId(id);
        try (Connection connection = DatabaseSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return Optional.empty();
            }
            subject.setName(resultSet.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.of(subject);
    }

    @Override
    public void updateByObject(final Subjects subject) {
        try (Connection connection = DatabaseSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            if (subject.getId() != 0) {
                preparedStatement.setInt(1, subject.getId());
            }
            preparedStatement.setString(2, subject.getName());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new IllegalArgumentException("Update subject failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeById(final int id) {
        try (Connection connection = DatabaseSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_QUERY)) {
            preparedStatement.setInt(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Remove subject failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
