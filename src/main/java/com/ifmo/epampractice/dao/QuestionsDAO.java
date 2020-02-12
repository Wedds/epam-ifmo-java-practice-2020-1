package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.Questions;
import com.ifmo.epampractice.service.DatabaseService;
import com.ifmo.epampractice.service.DatabaseSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionsDAO extends DatabaseSource implements DatabaseService<Questions> {


    @Override
    public void add(Questions question) throws SQLException {
        String query = "INSERT INTO QUESTIONS(question_type, test_id, questions.phrase, right_answer, structure) VALUES(?,?,?,?,?)";
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, question.getQuestionType());
            preparedStatement.setInt(2, question.getTestId());
            preparedStatement.setString(3, question.getPhrase());
            preparedStatement.setString(4, question.getRightAnswer());
            preparedStatement.setString(5, question.getStructure());

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
    public List<Questions> getAll() throws SQLException {
        String query = "SELECT id, question_type, test_id, phrase, right_answer, structure FROM QUESTIONS";
        Connection connection = getConnection();

        List<Questions> questionsList = new ArrayList<>();

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Questions question = new Questions();
                question.setId(resultSet.getInt("id"));
                question.setQuestionType(resultSet.getString("question_type"));
                question.setTestId(resultSet.getInt("test_id"));
                question.setPhrase(resultSet.getString("phrase"));
                question.setRightAnswer(resultSet.getString("right_answer"));
                question.setStructure(resultSet.getString("structure"));
                questionsList.add(question);
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

        return questionsList;
    }

    @Override
    public Questions getById(int id) throws SQLException {
        String query = "SELECT question_type, test_id, phrase, right_answer, structure FROM QUESTIONS WHERE id=?";
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

        Questions question = new Questions();
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            question.setId(id);
            question.setQuestionType(resultSet.getString("question_type"));
            question.setTestId(resultSet.getInt("test_id"));
            question.setPhrase(resultSet.getString("phrase"));
            question.setRightAnswer(resultSet.getString("right_answer"));
            question.setStructure(resultSet.getString("structure"));
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
        return question;
    }

    @Override
    public void update(Questions question) throws SQLException {
        String query = "UPDATE QUESTIONS SET question_type=?, test_id=?, phrase=?, right_answer=?, structure=? WHERE id=?";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(query);

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, question.getQuestionType());
            preparedStatement.setInt(2, question.getTestId());
            preparedStatement.setString(3, question.getPhrase());
            preparedStatement.setString(4, question.getRightAnswer());
            preparedStatement.setString(5, question.getStructure());
            preparedStatement.setInt(6, question.getId());

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
    public void remove(Questions question) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM QUESTIONS WHERE id=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, question.getId());
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
