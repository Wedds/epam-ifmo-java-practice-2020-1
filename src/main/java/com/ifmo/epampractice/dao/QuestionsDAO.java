package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.Questions;
import com.ifmo.epampractice.enums.QuestionType;
import com.ifmo.epampractice.service.DatabaseSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QuestionsDAO implements DAO<Questions> {
    private static final String INSERT_QUERY = "INSERT INTO QUESTIONS(question_type, test_id, " +
            "title, image, question_text) VALUES(?::types,?,?,?,?) RETURNING id";
    private static final String SELECT_ALL_QUERY = "SELECT id, question_type, test_id, " +
            "title, image, question_text FROM QUESTIONS";
    private static final String SELECT_ALL_BY_TEST_ID_QUERY = "SELECT id, question_type, test_id, " +
            "title, image, question_text FROM QUESTIONS WHERE test_id=?";
    private static final String SELECT_BY_ID_QUERY = "SELECT id, question_type, test_id, " +
            "title, image, question_text FROM QUESTIONS WHERE id=?";
    private static final String UPDATE_QUERY = "UPDATE QUESTIONS SET question_type=?::types, " +
            "test_id=?, title=?, image=?, question_text=? WHERE id=?";
    private static final String REMOVE_QUERY = "DELETE FROM QUESTIONS WHERE id=?";

    @Override
    public Questions addObject(final Questions question) {
        try (Connection connection = DatabaseSource.getInstance().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            fillQueryFromObject(question, preparedStatement);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new IllegalArgumentException("Creating question failed, no rows affected.");
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    question.setId(generatedKeys.getInt(1));
                } else {
                    throw new IllegalArgumentException("Creating question failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Error connecting to database");
        }
        return question;
    }

    @Override
    public List<Questions> getAll() {
        List<Questions> questionsList = new ArrayList<>();
        try (Connection connection = DatabaseSource.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY)) {
                while (resultSet.next()) {
                    Questions question = new Questions();
                    fillQuestionObjectFromResultSet(question, resultSet);
                    questionsList.add(question);
                }
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Error connecting to database");
        }
        return questionsList;
    }

    public List<Questions> getQuestionsListByTestId(final int testId) {
        List<Questions> questionsList = new ArrayList<>();
        try (Connection connection = DatabaseSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_TEST_ID_QUERY)) {
            preparedStatement.setInt(1, testId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Questions question = new Questions();
                    fillQuestionObjectFromResultSet(question, resultSet);
                    questionsList.add(question);
                }
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Error connecting to database");
        }
        return questionsList;
    }

    @Override
    public Optional<Questions> getById(final int id) {
        Questions question = new Questions();
        try (Connection connection = DatabaseSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.next()) {
                    return Optional.empty();
                }
                fillQuestionObjectFromResultSet(question, resultSet);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Error connecting to database");
        }
        return Optional.of(question);
    }

    @Override
    public void updateByObject(final Questions question) {
        try (Connection connection = DatabaseSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            fillQueryFromObject(question, preparedStatement);
            preparedStatement.setInt(6, question.getId());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new IllegalArgumentException("Update question failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Error connecting to database");
        }
    }

    @Override
    public void removeById(final int id) {
        try (Connection connection = DatabaseSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_QUERY)) {
            preparedStatement.setInt(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new IllegalArgumentException("Remove question failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Error connecting to database");
        }
    }

    private void fillQuestionObjectFromResultSet(final Questions question, final ResultSet resultSet) {
        try {
            question.setId(resultSet.getInt("id"));
            question.setQuestionType(QuestionType.valueOf(resultSet.getString("question_type").toUpperCase()));
            question.setTestId(resultSet.getInt("test_id"));
            question.setTitle(resultSet.getString("title"));
            question.setImage(resultSet.getString("image"));
            question.setQuestionText(resultSet.getString("question_text"));
        } catch (SQLException e) {
            System.err.println("Error with fill group object from result set");
            e.printStackTrace();
        }
    }

    private void fillQueryFromObject(final Questions question, final PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, question.getQuestionType().getValue());
            preparedStatement.setInt(2, question.getTestId());
            preparedStatement.setString(3, question.getTitle());
            preparedStatement.setString(4, question.getImage());
            preparedStatement.setString(5, question.getQuestionText());
        } catch (SQLException e) {
            System.err.println("Error with fill query");
            e.printStackTrace();
        }
    }
}
