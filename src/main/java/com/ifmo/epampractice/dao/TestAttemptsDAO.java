package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.TestAttempts;
import com.ifmo.epampractice.service.DatabaseService;
import com.ifmo.epampractice.service.DatabaseSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestAttemptsDAO extends DatabaseSource implements DatabaseService<TestAttempts> {
    @Override
    public void add(TestAttempts testAttempt) throws SQLException {
        String query = "INSERT INTO TEST_ATTEMPTS(user_id, test_id, score, passing_date) VALUES(?,?,?,?)";
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, testAttempt.getUserId());
            preparedStatement.setInt(2, testAttempt.getTestId());
            preparedStatement.setInt(3, testAttempt.getScore());
            preparedStatement.setDate(4, testAttempt.getPassingDate());

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
    public List<TestAttempts> getAll() throws SQLException {
        String query = "SELECT id, user_id, test_id, score, passing_date FROM TEST_ATTEMPTS";
        Connection connection = getConnection();

        List<TestAttempts> testAttemptsList = new ArrayList<>();

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                TestAttempts testAttempt = new TestAttempts();
                testAttempt.setId(resultSet.getInt("id"));
                testAttempt.setUserId(resultSet.getInt("user_id"));
                testAttempt.setTestId(resultSet.getInt("test_id"));
                testAttempt.setScore(resultSet.getInt("score"));
                testAttempt.setPassingDate(resultSet.getDate("passing_date"));
                testAttemptsList.add(testAttempt);
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

        return testAttemptsList;
    }

    @Override
    public TestAttempts getById(int id) throws SQLException {
        String query = "SELECT user_id, test_id, score, passing_date FROM TEST_ATTEMPTS WHERE id=?";
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

        TestAttempts testAttempt = new TestAttempts();
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            testAttempt.setId(id);
            testAttempt.setUserId(resultSet.getInt("user_id"));
            testAttempt.setTestId(resultSet.getInt("test_id"));
            testAttempt.setScore(resultSet.getInt("score"));
            testAttempt.setPassingDate(resultSet.getDate("passing_date"));

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
        return testAttempt;
    }

    @Override
    public void update(TestAttempts testAttempt) throws SQLException {
        String query = "UPDATE TEST_ATTEMPTS SET user_id=?, test_id=?, score=?, passing_date=? WHERE id=?";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, testAttempt.getUserId());
            preparedStatement.setInt(2, testAttempt.getTestId());
            preparedStatement.setInt(3, testAttempt.getScore());
            preparedStatement.setDate(4, testAttempt.getPassingDate());
            preparedStatement.setInt(5, testAttempt.getId());

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
    public void remove(TestAttempts testAttempt) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM TEST_ATTEMPTS WHERE id=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, testAttempt.getId());
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
