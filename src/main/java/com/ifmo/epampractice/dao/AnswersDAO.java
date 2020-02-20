package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.Answers;
import com.ifmo.epampractice.service.IDAO;
import com.ifmo.epampractice.service.DatabaseSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnswersDAO extends DatabaseSource implements IDAO<Answers> {
    private static final String INSERT_QUERY = "INSERT INTO ANSWERS(image, answer_text, " +
            "question_id, is_correct, points) VALUES(?,?,?,?,?) RETURNING id";
    private static final String SELECT_ALL_QUERY = "SELECT id, image, answer_text, " +
            "question_id, is_correct, points FROM ANSWERS";
    private static final String SELECT_BY_ID_QUERY = "SELECT image, answer_text, " +
            "question_id, is_correct, points FROM ANSWERS WHERE id=?";
    private static final String UPDATE_QUERY = "UPDATE ANSWERS SET image=?, answer_text=?, " +
            "question_id=?, is_correct=?, points=? WHERE id=?";
    private static final String REMOVE_QUERY = "DELETE FROM ANSWERS WHERE id=?";


    @Override
    public Answers addObject(Answers answer) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            fillQueryFromObject(answer, preparedStatement);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating answer failed, no rows affected.");
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    answer.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating answer failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answer;
    }

    @Override
    public List<Answers> getAll() {
        List<Answers> answersList = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);
            while (resultSet.next()) {
                Answers answer = new Answers();
                answer.setId(resultSet.getInt("id"));
                fillAnswerObjectFromResultSet(answer, resultSet);
                answersList.add(answer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answersList;
    }

    @Override
    public Answers getById(int id) {
        Answers answer = new Answers();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            answer.setId(id);
            fillAnswerObjectFromResultSet(answer, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answer;
    }

    @Override
    public void updateByObject(Answers answer) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            fillQueryFromObject(answer, preparedStatement);
            preparedStatement.setInt(8, answer.getId());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Update answer failed, no rows affected.");
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
                throw new SQLException("Remove answer failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fillAnswerObjectFromResultSet(Answers answer, ResultSet resultSet) {
        try {
            answer.setImage(resultSet.getString("image"));
            answer.setAnswerText(resultSet.getString("answer_text"));
            answer.setQuestionId(resultSet.getInt("question_id"));
            answer.setIsCorrect(resultSet.getBoolean("is_correct"));
            answer.setPoints(resultSet.getInt("points"));
        } catch (SQLException e) {
            System.err.println("Error with fill group object from result set");
            e.printStackTrace();
        }
    }

    private void fillQueryFromObject(Answers answer, PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, answer.getImage());
            preparedStatement.setString(2, answer.getAnswerText());
            preparedStatement.setInt(3, answer.getQuestionId());
            preparedStatement.setBoolean(4, answer.getIsCorrect());
            preparedStatement.setInt(5, answer.getPoints());
            preparedStatement.setInt(6, answer.getId());
        } catch (SQLException e) {
            System.err.println("Error with fill query");
            e.printStackTrace();
        }
    }
}
