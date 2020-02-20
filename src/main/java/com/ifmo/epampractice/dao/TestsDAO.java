package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.Tests;
import com.ifmo.epampractice.service.DatabaseSource;
import com.ifmo.epampractice.service.IDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestsDAO extends DatabaseSource implements IDAO<Tests> {
    private static final String INSERT_QUERY = "INSERT INTO TESTS(title, description," +
            "subject_id, is_random, created_at, max_points, creator_id) VALUES(?,?,?,?,?,?,?) RETURNING id";
    private static final String INSERT_GROUPS_TESTS_QUERY = "INSERT INTO GROUPS_TESTS(test_id, group_id," +
            "is_necessary, max_attempts, deadline, time_limit) VALUES(?,?,?,?,?,?)";
    private static final String SELECT_ALL_QUERY = "SELECT id, title, description," +
            "subject_id, is_random, created_at, max_points, creator_id FROM TESTS";
    private static final String SELECT_BY_ID_QUERY = "SELECT title, description," +
            "subject_id, is_random, created_at, max_points, creator_id FROM TESTS WHERE id=?";
    private static final String SELECT_BY_TEST_ID_QUERY = "SELECT title, description," +
            "subject_id, is_random, created_at, max_points, creator_id FROM TESTS WHERE id=?";
    private static final String SELECT_BY_TEST_AND_GROUP_ID_QUERY = "SELECT is_necessary, max_attempts," +
            "deadline, time_limit FROM GROUPS_TESTS WHERE test_id=? AND group_id=?";
    private static final String UPDATE_QUERY = "UPDATE TESTS SET title=?, description=?, " +
            "subject_id=?, is_random=?, created_at=?, max_points = ?, creator_id=? WHERE id=?";
    private static final String REMOVE_QUERY = "DELETE FROM TESTS WHERE id=?";

    @Override
    public Tests addObject(Tests test) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            fillTestsQueryFromObject(test, preparedStatement);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating test failed, no rows affected.");
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    test.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating test failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return test;
    }

    public Tests addTestsWithGroupsTests(Tests test) {
        this.addObject(test);
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_GROUPS_TESTS_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            fillGroupsTestsQueryFromObject(test, preparedStatement);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating test failed, no rows affected.");
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    test.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating test failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return test;
    }

    public void addGroupsTests(Tests test) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GROUPS_TESTS_QUERY)) {
            fillGroupsTestsQueryFromObject(test, preparedStatement);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating record in GroupsTests failed, no rows affected.");
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    test.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating record in GroupsTests failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Tests> getAll() {
        List<Tests> testsList = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);
            while (resultSet.next()) {
                Tests test = new Tests();
                test.setId(resultSet.getInt("id"));
                fillGeneralTestObjectFromResultSet(test, resultSet);
                testsList.add(test);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return testsList;
    }

    @Override
    public Tests getById(int id) {
        Tests test = new Tests();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            test.setId(id);
            fillGeneralTestObjectFromResultSet(test, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return test;
    }

    public Tests getByTestAndGroupId(int testId, int groupId) {
        Tests test = new Tests();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatementTest = connection.prepareStatement(SELECT_BY_TEST_ID_QUERY);
             PreparedStatement preparedStatementGroup =
                     connection.prepareStatement(SELECT_BY_TEST_AND_GROUP_ID_QUERY)) {
            preparedStatementTest.setInt(1, testId);
            ResultSet resultSetTest = preparedStatementTest.executeQuery();
            resultSetTest.next();
            test.setId(testId);
            fillGeneralTestObjectFromResultSet(test, resultSetTest);
            preparedStatementGroup.setInt(1, testId);
            preparedStatementGroup.setInt(2, groupId);
            ResultSet resultSetGroup = preparedStatementGroup.executeQuery();
            resultSetGroup.next();
            test.setGroupId(groupId);
            fillTestForGroupObjectFromResultSet(test, resultSetGroup);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return test;
    }

    public Tests fillByGroupId(Tests test, int groupId) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_TEST_AND_GROUP_ID_QUERY)) {
            preparedStatement.setInt(1, test.getId());
            preparedStatement.setInt(2, groupId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            fillTestForGroupObjectFromResultSet(test, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return test;
    }

    @Override
    public void updateByObject(Tests test) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            fillTestsQueryFromObject(test, preparedStatement);
            preparedStatement.setInt(8, test.getId());
            if (test.getGroupId() != 0) {
                fillGroupsTestsQueryFromObject(test, preparedStatement);
            }
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Update test failed, no rows affected.");
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
                throw new SQLException("Remove test failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fillGeneralTestObjectFromResultSet(Tests test, ResultSet resultSet) throws SQLException {
        test.setTitle(resultSet.getString("title"));
        test.setDescription(resultSet.getString("description"));
        test.setSubjectId(resultSet.getInt("subject_id"));
        test.setIsRandom(resultSet.getBoolean("is_random"));
        test.setCreatedAt(resultSet.getDate("created_at"));
        test.setMaxPoints(resultSet.getInt("max_points"));
        test.setCreatorId(resultSet.getInt("creator_id"));
    }

    private void fillTestForGroupObjectFromResultSet(Tests test, ResultSet resultSet) throws SQLException {
        test.setIsNecessary(resultSet.getBoolean("is_necessary"));
        test.setMaxAttempts(resultSet.getInt("max_attempts"));
        test.setDeadline(resultSet.getDate("deadline"));
        test.setTimeLimit(resultSet.getInt("time_limit"));
    }

    private void fillTestsQueryFromObject(Tests test, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, test.getTitle());
        preparedStatement.setString(2, test.getDescription());
        preparedStatement.setInt(3, test.getSubjectId());
        preparedStatement.setBoolean(4, test.getIsRandom());
        preparedStatement.setDate(5, test.getCreatedAt());
        preparedStatement.setInt(6, test.getMaxPoints());
        preparedStatement.setInt(7, test.getCreatorId());
    }

    private void fillGroupsTestsQueryFromObject(Tests test, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, test.getId());
        preparedStatement.setInt(2, test.getGroupId());
        preparedStatement.setBoolean(3, test.getIsNecessary());
        preparedStatement.setInt(4, test.getMaxAttempts());
        preparedStatement.setDate(5, test.getDeadline());
        preparedStatement.setInt(6, test.getTimeLimit());
    }
}
