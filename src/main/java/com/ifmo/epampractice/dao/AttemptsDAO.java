package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.Attempts;
import com.ifmo.epampractice.service.DatabaseSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AttemptsDAO implements DAO<Attempts> {
    private static final String INSERT_QUERY = "INSERT INTO ATTEMPTS(user_id, " +
            "test_id, score, passing_date) VALUES(?,?,?,?)";
    private static final String SELECT_ALL_QUERY = "SELECT id, user_id, test_id, " +
            "score, passing_date FROM ATTEMPTS";
    private static final String SELECT_ALL_BY_TEST_ID_QUERY = "SELECT id, user_id, test_id, " +
            "score, passing_date FROM ATTEMPTS WHERE test_id=?";
    private static final String SELECT_ALL_BY_TEST_AND_USER_ID_QUERY = "SELECT id, user_id, test_id, " +
            "score, passing_date FROM ATTEMPTS WHERE test_id=? AND user_id=?";
    private static final String SELECT_BY_ID_QUERY = "SELECT id, user_id, test_id, " +
            "score, passing_date FROM ATTEMPTS WHERE id=?";
    private static final String UPDATE_QUERY = "UPDATE ATTEMPTS SET " +
            "user_id=?, test_id=?, score=?, passing_date=? WHERE id=?";
    private static final String REMOVE_QUERY = "DELETE FROM ATTEMPTS WHERE id=?";


    @Override
    public Attempts addObject(final Attempts attempt) {
        try (Connection connection = DatabaseSource.getInstance().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            fillQueryFromObject(attempt, preparedStatement);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new IllegalArgumentException("Creating attempt failed, no rows affected.");
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    attempt.setId(generatedKeys.getInt(1));
                } else {
                    throw new IllegalArgumentException("Creating attempt failed, no ID obtained.");
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
        try (Connection connection = DatabaseSource.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY)) {
                while (resultSet.next()) {
                    Attempts attempt = new Attempts();
                    fillAttemptObjectFromResultSet(attempt, resultSet);
                    testAttemptsList.add(attempt);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return testAttemptsList;
    }

    public List<Attempts> getAttemptsListByTestId(final int testId) {
        List<Attempts> testAttemptsList = new ArrayList<>();
        try (Connection connection = DatabaseSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_TEST_ID_QUERY)) {
            preparedStatement.setInt(1, testId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Attempts attempt = new Attempts();
                    fillAttemptObjectFromResultSet(attempt, resultSet);
                    testAttemptsList.add(attempt);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return testAttemptsList;
    }

    public List<Attempts> getAttemptsListByTestAndUserId(final int testId, final int userId) {
        List<Attempts> testAttemptsList = new ArrayList<>();
        try (Connection connection = DatabaseSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_TEST_AND_USER_ID_QUERY)) {
            preparedStatement.setInt(1, testId);
            preparedStatement.setInt(2, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Attempts attempt = new Attempts();
                    fillAttemptObjectFromResultSet(attempt, resultSet);
                    testAttemptsList.add(attempt);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return testAttemptsList;
    }

    @Override
    public Optional<Attempts> getById(final int id) {
        Attempts attempt = new Attempts();
        try (Connection connection = DatabaseSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.next()) {
                    return Optional.empty();
                }
                fillAttemptObjectFromResultSet(attempt, resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.of(attempt);
    }

    @Override
    public void updateByObject(final Attempts attempt) {
        try (Connection connection = DatabaseSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            fillQueryFromObject(attempt, preparedStatement);
            preparedStatement.setInt(5, attempt.getId());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new IllegalArgumentException("Update attempt failed, no rows affected.");
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
                throw new IllegalArgumentException("Remove attempt failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fillAttemptObjectFromResultSet(final Attempts attempt, final ResultSet resultSet) {
        try {
            attempt.setId(resultSet.getInt("id"));
            attempt.setUserId(resultSet.getInt("user_id"));
            attempt.setTestId(resultSet.getInt("test_id"));
            attempt.setScore(resultSet.getInt("score"));
            attempt.setPassingDate(resultSet.getObject("passing_date", LocalDateTime.class));
        } catch (SQLException e) {
            System.err.println("Error with fill group object from result set");
            e.printStackTrace();
        }
    }

    private void fillQueryFromObject(final Attempts attempt, final PreparedStatement preparedStatement) {
        try {
            preparedStatement.setInt(1, attempt.getUserId());
            preparedStatement.setInt(2, attempt.getTestId());
            preparedStatement.setInt(3, attempt.getScore());
            preparedStatement.setObject(4, attempt.getPassingDate());
        } catch (SQLException e) {
            System.err.println("Error with fill query");
            e.printStackTrace();
        }
    }
}
