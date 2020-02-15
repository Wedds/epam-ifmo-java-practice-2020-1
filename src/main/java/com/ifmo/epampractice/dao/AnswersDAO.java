package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.Answers;
import com.ifmo.epampractice.service.IDAO;
import com.ifmo.epampractice.service.DatabaseSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnswersDAO extends DatabaseSource implements IDAO<Answers> {
    private static final String INSERT_QUERY = "INSERT INTO ANSWERS(image, answer_text, " +
            "question_id, is_correct, points) VALUES(?,?,?,?)";
    private static final String SELECT_ALL_QUERY = "SELECT id, image, answer_text, " +
            "question_id, is_correct, points FROM ANSWERS";
    private static final String SELECT_BY_ID_QUERY = "SELECT image, answer_text, " +
            "question_id, is_correct, points FROM ANSWERS WHERE id=?";
    private static final String UPDATE_QUERY = "UPDATE ANSWERS SET image=?, answer_text=?, " +
            "question_id=?, is_correct=?, points=? WHERE id=?";
    private static final String REMOVE_QUERY = "DELETE FROM ANSWERS WHERE id=?";


    @Override
    public void add(Answers answer) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            try {
                execQueryFromObject(answer, preparedStatement);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Answers> getAll() throws SQLException {
        List<Answers> answersList = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            try {
                ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);
                while (resultSet.next()) {
                    Answers answer = new Answers();
                    answer.setId(resultSet.getInt("id"));
                    setObjectFromResultSet(answer, resultSet);
                    answersList.add(answer);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return answersList;
    }

    @Override
    public Answers getById(int id) throws SQLException {
        Answers answer = new Answers();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            try {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                answer.setId(id);
                setObjectFromResultSet(answer, resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return answer;
    }

    public void update(Answers answer) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            try {
                execQueryFromObject(answer, preparedStatement);
                preparedStatement.setInt(8, answer.getId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void remove(Answers answer) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_QUERY)) {
            try {
                preparedStatement.setInt(1, answer.getId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void setObjectFromResultSet(Answers answer, ResultSet resultSet) throws SQLException {
        answer.setImage(resultSet.getString("image"));
        answer.setAnswerText(resultSet.getString("answer_text"));
        answer.setQuestionId(resultSet.getInt("question_id"));
        answer.setIsCorrect(resultSet.getBoolean("is_correct"));
        answer.setPoints(resultSet.getInt("points"));
    }


    private void execQueryFromObject(Answers answer, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, answer.getImage());
        preparedStatement.setString(2, answer.getAnswerText());
        preparedStatement.setInt(3, answer.getQuestionId());
        preparedStatement.setBoolean(4, answer.getIsCorrect());
        preparedStatement.setInt(5, answer.getPoints());
        preparedStatement.setInt(6, answer.getId());
    }
}
