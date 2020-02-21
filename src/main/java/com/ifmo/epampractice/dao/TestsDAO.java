package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.Tests;
import com.ifmo.epampractice.service.DatabaseSource;
import com.ifmo.epampractice.service.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestsDAO extends DatabaseSource implements DAO<Tests> {
    private static final String INSERT_TESTS_QUERY = "INSERT INTO TESTS(title, description," +
            "subject_id, is_random, created_at, max_points, creator_id) VALUES(?,?,?,?,?,?,?) RETURNING id";
    private static final String INSERT_GROUPS_TESTS_QUERY = "INSERT INTO GROUPS_TESTS(test_id, group_id," +
            "is_necessary, max_attempts, deadline, time_limit) VALUES(?,?,?,?,?,?)";
    private static final String SELECT_ALL_QUERY = "SELECT id, title, description," +
            "subject_id, is_random, created_at, max_points, creator_id FROM TESTS";
    private static final String SELECT_TEST_BY_TEST_ID_QUERY = "SELECT id, title, description," +
            "subject_id, is_random, created_at, max_points, creator_id FROM TESTS WHERE id=?";
    private static final String SELECT_GROUPS_TESTS_BY_TEST_AND_GROUP_ID_QUERY = "SELECT test_id, group_id, " +
            "is_necessary, max_attempts, deadline, time_limit FROM GROUPS_TESTS WHERE test_id=? AND group_id=?";
    private static final String SELECT_ALL_GROUPS_TESTS_BY_TEST_ID_QUERY = "SELECT test_id, group_id, is_necessary," +
            "max_attempts, deadline, time_limit FROM GROUPS_TESTS WHERE test_id=?";
    private static final String UPDATE_TESTS_QUERY = "UPDATE TESTS SET title=?, description=?, " +
            "subject_id=?, is_random=?, created_at=?, max_points = ?, creator_id=? WHERE id=?";
    private static final String UPDATE_GROUPS_TESTS_QUERY = "UPDATE GROUPS_TESTS SET is_necessary=?, " +
            "max_attempts=?, deadline=?, time_limit = ? WHERE test_id=? AND group_id=?";
    private static final String REMOVE_QUERY = "DELETE FROM TESTS WHERE id=?";
    private static final String REMOVE_GROUPS_TESTS_QUERY = "DELETE FROM GROUPS_TESTS WHERE test_id=?";

    @Override
    public Tests addObject(final Tests test) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_TESTS_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            fillTestsQueryFromObject(test, preparedStatement);
            executeStatementAndCheck(test, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return test;
    }

    public Tests addTestsWithGroupsTests(final Tests test) {
        this.addObject(test);
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_GROUPS_TESTS_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            fillGroupsTestsQueryFromObject(test, preparedStatement);
            executeStatementAndCheck(test, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return test;
    }

    public Tests addGroupsTests(final Tests test) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GROUPS_TESTS_QUERY)) {
            fillGroupsTestsQueryFromObject(test, preparedStatement);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating record in GroupsTests failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return test;
    }

    @Override
    public List<Tests> getAll() {
        List<Tests> testsList = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);
            while (resultSet.next()) {
                Tests test = new Tests();
                fillGeneralTestObjectFromResultSet(test, resultSet);
                testsList.add(test);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return testsList;
    }

    @Override
    public Optional<Tests> getById(final int id) {
        Tests test = new Tests();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TEST_BY_TEST_ID_QUERY)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()){
                return Optional.empty();
            }
            fillGeneralTestObjectFromResultSet(test, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.of(test);
    }

    public List<Tests>  getAllGroupsTestsByTestId(final int id) {
        List<Tests> groupsTestsList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(SELECT_ALL_GROUPS_TESTS_BY_TEST_ID_QUERY)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Tests test = new Tests();
                fillTestForGroupObjectFromResultSet(test, resultSet);
                groupsTestsList.add(test);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groupsTestsList;
    }


    public Optional<Tests> getObjectByTestAndGroupId(final int testId, final int groupId) {
        Tests test = new Tests();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatementTest = connection.prepareStatement(SELECT_TEST_BY_TEST_ID_QUERY);
             PreparedStatement preparedStatementGroup =
                     connection.prepareStatement(SELECT_GROUPS_TESTS_BY_TEST_AND_GROUP_ID_QUERY)) {
            preparedStatementTest.setInt(1, testId);
            ResultSet resultSetTest = preparedStatementTest.executeQuery();
            if (!resultSetTest.next()){
                return Optional.empty();
            }
            fillGeneralTestObjectFromResultSet(test, resultSetTest);
            preparedStatementGroup.setInt(1, testId);
            preparedStatementGroup.setInt(2, groupId);
            ResultSet resultSetGroup = preparedStatementGroup.executeQuery();
            if (!resultSetGroup.next()){
                return Optional.empty();
            }
            fillTestForGroupObjectFromResultSet(test, resultSetGroup);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.of(test);
    }

    public Tests fillObjectByGroupId(final Tests test, final int groupId) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(SELECT_GROUPS_TESTS_BY_TEST_AND_GROUP_ID_QUERY)) {
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
    public void updateByObject(final Tests test) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TESTS_QUERY)) {
            fillTestsQueryFromObject(test, preparedStatement);
            preparedStatement.setInt(8, test.getId());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Update test failed, no rows affected.");
            }
            if (test.getGroupId() != 0) {
                updateGroupsTests(test);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateGroupsTests(final Tests test) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_GROUPS_TESTS_QUERY)) {
            preparedStatement.setBoolean(1, test.getIsNecessary());
            preparedStatement.setInt(2, test.getMaxAttempts());
            preparedStatement.setDate(3, test.getDeadline());
            preparedStatement.setInt(4, test.getTimeLimit());
            preparedStatement.setInt(5, test.getId());
            preparedStatement.setInt(6, test.getGroupId());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Update groups_tests failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeById(final int id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_QUERY)) {
            preparedStatement.setInt(1, id);
            List<Tests> groupsTestsList = getAllGroupsTestsByTestId(id);
            if (!groupsTestsList.isEmpty()) {
                removeGroupsTestsById(id);
            }
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Remove test failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeGroupsTestsById(final int id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_GROUPS_TESTS_QUERY)) {
            preparedStatement.setInt(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Remove groups_tests failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void executeStatementAndCheck(final Tests test, final PreparedStatement preparedStatement)
            throws SQLException {
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
    }

    private void fillGeneralTestObjectFromResultSet(final Tests test, final ResultSet resultSet) {
        try {
            test.setId(resultSet.getInt("id"));
            test.setTitle(resultSet.getString("title"));
            test.setDescription(resultSet.getString("description"));
            test.setSubjectId(resultSet.getInt("subject_id"));
            test.setIsRandom(resultSet.getBoolean("is_random"));
            test.setCreatedAt(resultSet.getDate("created_at"));
            test.setMaxPoints(resultSet.getInt("max_points"));
            test.setCreatorId(resultSet.getInt("creator_id"));
        } catch (SQLException e) {
            System.err.println("Error with fill general test object from result ser");
            e.printStackTrace();
        }
    }

    private void fillTestForGroupObjectFromResultSet(final Tests test, final ResultSet resultSet) {
        try {
            test.setId(resultSet.getInt("test_id"));
            test.setGroupId(resultSet.getInt("group_id"));
            test.setIsNecessary(resultSet.getBoolean("is_necessary"));
            test.setMaxAttempts(resultSet.getInt("max_attempts"));
            test.setDeadline(resultSet.getDate("deadline"));
            test.setTimeLimit(resultSet.getInt("time_limit"));
        } catch (SQLException e) {
            System.err.println("Error with fill test for groups object from result set");
            e.printStackTrace();
        }
    }

    private void fillTestsQueryFromObject(final Tests test, final PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, test.getTitle());
            preparedStatement.setString(2, test.getDescription());
            preparedStatement.setInt(3, test.getSubjectId());
            preparedStatement.setBoolean(4, test.getIsRandom());
            preparedStatement.setDate(5, test.getCreatedAt());
            preparedStatement.setInt(6, test.getMaxPoints());
            preparedStatement.setInt(7, test.getCreatorId());
        } catch (SQLException e) {
            System.err.println("Error with fill tests query");
            e.printStackTrace();
        }
    }

    private void fillGroupsTestsQueryFromObject(final Tests test, final PreparedStatement preparedStatement) {
        try {
            preparedStatement.setInt(1, test.getId());
            preparedStatement.setInt(2, test.getGroupId());
            preparedStatement.setBoolean(3, test.getIsNecessary());
            preparedStatement.setInt(4, test.getMaxAttempts());
            preparedStatement.setDate(5, test.getDeadline());
            preparedStatement.setInt(6, test.getTimeLimit());
        } catch (SQLException e) {
            System.err.println("Error with filling groups tests query");
            e.printStackTrace();
        }
    }
}
