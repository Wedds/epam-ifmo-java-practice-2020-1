package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.Questions;
import com.ifmo.epampractice.enums.QuestionType;
import com.ifmo.epampractice.service.IDAO;
import com.ifmo.epampractice.service.DatabaseSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionsDAO extends DatabaseSource implements IDAO<Questions> {
    private static final String INSERT_QUERY = "INSERT INTO QUESTIONS(question_type, test_id, " +
            "title, image, question_text) VALUES(?,?,?,?,?) RETURNING id";
    private static final String SELECT_ALL_QUERY = "SELECT id, question_type, test_id, " +
            "title, image, question_text FROM QUESTIONS";
    private static final String SELECT_BY_ID_QUERY = "SELECT question_type, test_id, " +
            "title, image, question_text FROM QUESTIONS WHERE id=?";
    private static final String UPDATE_QUERY = "UPDATE QUESTIONS SET question_type=?, " +
            "test_id=?, title=?, image=?, question_text=? WHERE id=?";
    private static final String REMOVE_QUERY = "DELETE FROM QUESTIONS WHERE id=?";

    @Override
    public Questions addObject(Questions question) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            fillQueryFromObject(question, preparedStatement);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating question failed, no rows affected.");
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    question.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating question failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return question;
    }


    @Override
    public List<Questions> getAll() {
        List<Questions> questionsList = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);
            while (resultSet.next()) {
                Questions question = new Questions();
                question.setId(resultSet.getInt("id"));
                fillQuestionObjectFromResultSet(question, resultSet);
                questionsList.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questionsList;
    }

    @Override
    public Questions getById(int id) {
        Questions question = new Questions();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            question.setId(id);
            fillQuestionObjectFromResultSet(question, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return question;
    }

    @Override
    public void updateByObject(Questions question) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            fillQueryFromObject(question, preparedStatement);
            preparedStatement.setInt(8, question.getId());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Update question failed, no rows affected.");
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
                throw new SQLException("Remove question failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fillQuestionObjectFromResultSet(Questions question, ResultSet resultSet) {
        try {
            question.setQuestionType(getQuestionTypeFromString(resultSet.getString("question_type")));
            question.setTestId(resultSet.getInt("test_id"));
            question.setTitle(resultSet.getString("title"));
            question.setImage(resultSet.getString("image"));
            question.setQuestionText(resultSet.getString("question_text"));
        } catch (SQLException e) {
            System.err.println("Error with fill group object from result set");
            e.printStackTrace();
        }
    }

    private void fillQueryFromObject(Questions question, PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, getStringFromQuestionType(question.getQuestionType()));
            preparedStatement.setInt(2, question.getTestId());
            preparedStatement.setString(3, question.getTitle());
            preparedStatement.setString(4, question.getImage());
            preparedStatement.setString(5, question.getQuestionText());
            preparedStatement.setInt(6, question.getId());
        } catch (SQLException e) {
            System.err.println("Error with fill query");
            e.printStackTrace();
        }
    }

    private QuestionType getQuestionTypeFromString(String questionTypeString) {
        if (questionTypeString.equals("checkbox")) {
            return QuestionType.CHECKBOX;
        } else {
            return QuestionType.RADIOBUTTON;
        }
    }

    private String getStringFromQuestionType(QuestionType questionType) {
        if (questionType.equals(QuestionType.CHECKBOX)) {
            return ("checkbox");
        } else {
            return ("radiobutton");
        }
    }
}
