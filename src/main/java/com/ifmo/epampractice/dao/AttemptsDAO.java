package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.Attempts;
import com.ifmo.epampractice.service.IDAO;
import com.ifmo.epampractice.service.DatabaseSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttemptsDAO extends DatabaseSource implements IDAO<Attempts> {
    private static final String INSERT_QUERY = "INSERT INTO ATTEMPTS(user_id, " +
            "test_id, score, passing_date) VALUES(?,?,?,?)";
    private static final String SELECT_ALL_QUERY = "SELECT id, user_id, test_id, " +
            "score, passing_date FROM ATTEMPTS";
    private static final String SELECT_BY_ID_QUERY = "SELECT user_id, test_id, " +
            "score, passing_date FROM ATTEMPTS WHERE id=?";
    private static final String UPDATE_QUERY = "UPDATE ATTEMPTS SET " +
            "user_id=?, test_id=?, score=?, passing_date=? WHERE id=?";
    private static final String REMOVE_QUERY = "DELETE FROM ATTEMPTS WHERE id=?";


    @Override
    public void add(Attempts attempt) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            try {
                execQueryFromObject(attempt, preparedStatement);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public List<Attempts> getAll() throws SQLException {
        List<Attempts> testAttemptsList = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            try {
                ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);

                while (resultSet.next()) {
                    Attempts attempt = new Attempts();
                    attempt.setId(resultSet.getInt("id"));
                    fillAttemptObjectFromResultSet(attempt, resultSet);
                    testAttemptsList.add(attempt);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return testAttemptsList;
    }

    @Override
    public Attempts getById(int id) throws SQLException {
        Attempts attempt = new Attempts();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            try {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                attempt.setId(id);
                fillAttemptObjectFromResultSet(attempt, resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return attempt;
    }


    @Override
    public void update(Attempts attempt) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            try {
                execQueryFromObject(attempt, preparedStatement);
                preparedStatement.setInt(8, attempt.getId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void remove(Attempts testAttempt) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_QUERY)) {
            try {
                preparedStatement.setInt(1, testAttempt.getId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void fillAttemptObjectFromResultSet(Attempts attempt, ResultSet resultSet) throws SQLException {
        attempt.setUserId(resultSet.getInt("user_id"));
        attempt.setTestId(resultSet.getInt("test_id"));
        attempt.setScore(resultSet.getInt("score"));
        attempt.setPassingDate(resultSet.getDate("passing_date"));
    }

    private void execQueryFromObject(Attempts attempt, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, attempt.getUserId());
        preparedStatement.setInt(2, attempt.getTestId());
        preparedStatement.setInt(3, attempt.getScore());
        preparedStatement.setDate(4, attempt.getPassingDate());
        preparedStatement.setInt(5, attempt.getId());
    }
}
