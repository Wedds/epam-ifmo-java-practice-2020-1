package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.Attempts;
import com.ifmo.epampractice.service.IDAO;
import com.ifmo.epampractice.service.DatabaseSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttemptsDAO extends DatabaseSource implements IDAO<Attempts> {
    private static final String INSERT_QUERY = "INSERT INTO ATTEMPTS(user_id, " +
            "test_id, score, passing_date) VALUES(?,?,?,?) RETURNING id";
    private static final String SELECT_ALL_QUERY = "SELECT id, user_id, test_id, " +
            "score, passing_date FROM ATTEMPTS";
    private static final String SELECT_BY_ID_QUERY = "SELECT user_id, test_id, " +
            "score, passing_date FROM ATTEMPTS WHERE id=?";
    private static final String UPDATE_QUERY = "UPDATE ATTEMPTS SET " +
            "user_id=?, test_id=?, score=?, passing_date=? WHERE id=?";
    private static final String REMOVE_QUERY = "DELETE FROM ATTEMPTS WHERE id=?";


    @Override
    public Attempts addObject(Attempts attempt) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            execQueryFromObject(attempt, preparedStatement);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating attempt failed, no rows affected.");
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    attempt.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating attempt failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attempt;
    }


    @Override
    public List<Attempts> getAll() {
        List<Attempts> testAttemptsList = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
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
        return testAttemptsList;
    }

    @Override
    public Attempts getById(int id) {
        Attempts attempt = new Attempts();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            attempt.setId(id);
            fillAttemptObjectFromResultSet(attempt, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attempt;
    }

    @Override
    public void updateByObject(Attempts attempt) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            execQueryFromObject(attempt, preparedStatement);
            preparedStatement.setInt(8, attempt.getId());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Update attempt failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeById(int id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_QUERY)) {
            preparedStatement.setInt(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Remove attempt failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
