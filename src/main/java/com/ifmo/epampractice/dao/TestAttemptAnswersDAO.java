package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.TestAttemptAnswers;
import com.ifmo.epampractice.service.DatabaseService;
import com.ifmo.epampractice.service.DatabaseSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestAttemptAnswersDAO extends DatabaseSource implements DatabaseService<TestAttemptAnswers> {
    @Override
    public void add(TestAttemptAnswers testAttemptAnswer) throws SQLException {
        String query = "INSERT INTO TEST_ATTEMPT_ANSWERS(test_attempt_id, question_id, answer, score) VALUES(?,?,?,?)";
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, testAttemptAnswer.getTestAttemptId());
            preparedStatement.setInt(2, testAttemptAnswer.getQuestionId());
            preparedStatement.setString(3, testAttemptAnswer.getAnswer());
            preparedStatement.setInt(4, testAttemptAnswer.getScore());

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
    public List<TestAttemptAnswers> getAll() throws SQLException {
        String query = "SELECT id, test_attempt_id, question_id, answer, score FROM TEST_ATTEMPT_ANSWERS";
        Connection connection = getConnection();

        List<TestAttemptAnswers> testAttemptAnswersList = new ArrayList<>();

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                TestAttemptAnswers testAttemptAnswer = new TestAttemptAnswers();
                testAttemptAnswer.setId(resultSet.getInt("id"));
                testAttemptAnswer.setTestAttemptId(resultSet.getInt("test_attempt_id"));
                testAttemptAnswer.setQuestionId(resultSet.getInt("question_id"));
                testAttemptAnswer.setAnswer(resultSet.getString("answer"));
                testAttemptAnswer.setScore(resultSet.getInt("score"));
                testAttemptAnswersList.add(testAttemptAnswer);
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

        return testAttemptAnswersList;
    }

    @Override
    public TestAttemptAnswers getById(int id) throws SQLException {
        String query = "SELECT test_attempt_id, question_id, answer, score FROM TEST_ATTEMPT_ANSWERS WHERE id=?";
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

        TestAttemptAnswers testAttemptAnswer = new TestAttemptAnswers();
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            testAttemptAnswer.setId(id);
            testAttemptAnswer.setTestAttemptId(resultSet.getInt("test_attempt_id"));
            testAttemptAnswer.setQuestionId(resultSet.getInt("question_id"));
            testAttemptAnswer.setAnswer(resultSet.getString("answer"));
            testAttemptAnswer.setScore(resultSet.getInt("score"));
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
        return testAttemptAnswer;
    }

    @Override
    public void update(TestAttemptAnswers testAttemptAnswer) throws SQLException {
        String query = "UPDATE TEST_ATTEMPT_ANSWERS SET test_attempt_id=?, question_id=?, answer=?, score=? WHERE id=?";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, testAttemptAnswer.getTestAttemptId());
            preparedStatement.setInt(2, testAttemptAnswer.getQuestionId());
            preparedStatement.setString(3, testAttemptAnswer.getAnswer());
            preparedStatement.setInt(4, testAttemptAnswer.getScore());
            preparedStatement.setInt(5, testAttemptAnswer.getId());

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
    public void remove(TestAttemptAnswers testAttemptAnswer) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM TEST_ATTEMPT_ANSWERS WHERE id=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, testAttemptAnswer.getId());
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
