package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.Tests;
import com.ifmo.epampractice.service.DatabaseSource;

import java.time.LocalDateTime;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestsDAO implements DAO<Tests> {
    private static final String INSERT_TESTS_QUERY = "INSERT INTO TESTS(title, description," +
            "subject_id, is_random, created_at, max_points, creator_id) VALUES(?,?,?,?,?,?,?)";
    private static final String INSERT_GROUPS_TESTS_QUERY = "INSERT INTO GROUPS_TESTS(test_id, group_id," +
            "is_necessary, max_attempts, deadline, time_limit) VALUES(?,?,?,?,?,?)";
    private static final String SELECT_ALL_QUERY = "SELECT id, title, description," +
            "subject_id, is_random, created_at, max_points, creator_id FROM TESTS";
    private static final String SELECT_ALL_TESTS_BY_SUBJECT_ID_QUERY = "SELECT id, title, description," +
            "subject_id, is_random, created_at, max_points, creator_id FROM TESTS WHERE subject_id = ?";
    private static final String SELECT_ALL_TESTS_BY_CREATOR_ID_QUERY = "SELECT id, title, description," +
            "subject_id, is_random, created_at, max_points, creator_id FROM TESTS WHERE creator_id = ?";
    private static final String SELECT_TEST_BY_TEST_ID_QUERY = "SELECT id, title, description," +
            "subject_id, is_random, created_at, max_points, creator_id FROM TESTS WHERE id=?";
    private static final String SELECT_GROUPS_TESTS_BY_TEST_AND_GROUP_ID_QUERY = "SELECT test_id, group_id, " +
            "is_necessary, max_attempts, deadline, time_limit FROM GROUPS_TESTS WHERE test_id=? AND group_id=?";
    private static final String SELECT_ALL_TESTS_FOR_GROUPS_BY_TEST_ID_QUERY = "SELECT tests.id AS id, tests.title " +
            "AS title, tests.description AS description, tests.subject_id AS subject_id, tests.is_random  AS " +
            "is_random, tests.created_at AS created_at, tests.max_points AS max_points, tests.creator_id AS " +
            "creator_id, groups_tests.test_id AS test_id, groups_tests.group_id, groups_tests.is_necessary, " +
            "groups_tests.max_attempts, groups_tests.deadline, groups_tests.time_limit FROM TESTS INNER JOIN " +
            "GROUPS_TESTS ON tests.id = groups_tests.test_id WHERE id = ?";
    private static final String SELECT_ALL_GROUPS_TESTS_BY_GROUP_ID_QUERY = "SELECT test_id, group_id, is_necessary," +
            "max_attempts, deadline, time_limit FROM GROUPS_TESTS WHERE group_id=?";
    private static final String UPDATE_TESTS_QUERY = "UPDATE TESTS SET title=?, description=?, " +
            "subject_id=?, is_random=?, created_at=?, max_points = ?, creator_id=? WHERE id=?";
    private static final String UPDATE_GROUPS_TESTS_QUERY = "UPDATE GROUPS_TESTS SET is_necessary=?, " +
            "max_attempts=?, deadline=?, time_limit = ? WHERE test_id=? AND group_id=?";
    private static final String REMOVE_QUERY = "DELETE FROM TESTS WHERE id=?";
    private static final String REMOVE_GROUPS_TESTS_BY_TEST_AND_GROUP_ID_QUERY = "DELETE FROM GROUPS_TESTS " +
            "WHERE test_id=? AND group_id=?";

    @Override
    public Tests addObject(final Tests test) {
        try (Connection connection = DatabaseSource.getInstance().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_TESTS_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            fillTestsQueryFromObject(test, preparedStatement);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new IllegalArgumentException("Creating test failed, no rows affected.");
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    test.setId(generatedKeys.getInt(1));
                } else {
                    throw new IllegalArgumentException("Creating test failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Error connecting to database");
        }
        return test;
    }

    public Tests addTestForGroup(final Tests test) {
        try (Connection connection = DatabaseSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GROUPS_TESTS_QUERY)) {
            fillTestForGroupQueryFromObject(test, preparedStatement);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new IllegalArgumentException("Creating record in GroupsTests failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Error connecting to database");
        }
        return test;
    }

    @Override
    public List<Tests> getAll() {
        List<Tests> testsList = new ArrayList<>();
        try (Connection connection = DatabaseSource.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY)) {
                while (resultSet.next()) {
                    Tests test = new Tests();
                    fillGeneralTestObjectFromResultSet(test, resultSet);
                    testsList.add(test);
                }
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Error connecting to database");
        }
        return testsList;
    }

    public List<Tests> getAllTestsForGroupsByTestId(final int testId) {
        List<Tests> groupsTestsList = new ArrayList<>();
        try (Connection connection = DatabaseSource.getInstance().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(SELECT_ALL_TESTS_FOR_GROUPS_BY_TEST_ID_QUERY)) {
            preparedStatement.setInt(1, testId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Tests test = new Tests();
                    fillGeneralTestObjectFromResultSet(test, resultSet);
                    fillTestForGroupObjectFromResultSet(test, resultSet);
                    groupsTestsList.add(test);
                }
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Error connecting to database");
        }
        return groupsTestsList;
    }

    public List<Tests> getAllTestsForGroupsByGroupId(final int groupId) {
        List<Tests> groupsTestsList = new ArrayList<>();
        try (Connection connection = DatabaseSource.getInstance().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(SELECT_ALL_GROUPS_TESTS_BY_GROUP_ID_QUERY)) {
            preparedStatement.setInt(1, groupId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int testId = resultSet.getInt("test_id");
                    Optional<Tests> testOptional = getById(testId);
                    Tests test = new Tests();
                    if (testOptional.isPresent()) {
                        test = testOptional.get();
                    }
                    fillTestForGroupObjectFromResultSet(test, resultSet);
                    groupsTestsList.add(test);
                }
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Error connecting to database");
        }
        return groupsTestsList;
    }

    public List<Tests> getAllTestsBySubjectId(final int subjectId) {
        return getTestsListByForeignKey(subjectId, SELECT_ALL_TESTS_BY_SUBJECT_ID_QUERY);
    }

    public List<Tests> getAllTestsByCreatorId(final int creatorId) {
        return getTestsListByForeignKey(creatorId, SELECT_ALL_TESTS_BY_CREATOR_ID_QUERY);
    }

    private List<Tests> getTestsListByForeignKey(final int id, final String query) {
        List<Tests> testsList = new ArrayList<>();
        try (Connection connection = DatabaseSource.getInstance().getConnection();
             PreparedStatement preparedStatement
                     = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Tests test = new Tests();
                    fillGeneralTestObjectFromResultSet(test, resultSet);
                    testsList.add(test);
                }
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Error connecting to database");
        }
        return testsList;
    }

    @Override
    public Optional<Tests> getById(final int id) {
        Tests test = new Tests();
        try (Connection connection = DatabaseSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TEST_BY_TEST_ID_QUERY)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.next()) {
                    return Optional.empty();
                }
                fillGeneralTestObjectFromResultSet(test, resultSet);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Error connecting to database");
        }
        return Optional.of(test);
    }

    public Optional<Tests> getObjectByTestAndGroupId(final int testId, final int groupId) {
        Optional<Tests> testsOptional = getById(testId);
        return testsOptional.map(tests -> fillObjectByGroupId(tests, groupId));
    }

    public Tests fillObjectByGroupId(final Tests test, final int groupId) {
        try (Connection connection = DatabaseSource.getInstance().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(SELECT_GROUPS_TESTS_BY_TEST_AND_GROUP_ID_QUERY)) {
            preparedStatement.setInt(1, test.getId());
            preparedStatement.setInt(2, groupId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    fillTestForGroupObjectFromResultSet(test, resultSet);
                }
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Error connecting to database");
        }
        return test;
    }

    @Override
    public void updateByObject(final Tests test) {
        try (Connection connection = DatabaseSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TESTS_QUERY)) {
            fillTestsQueryFromObject(test, preparedStatement);
            preparedStatement.setInt(8, test.getId());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new IllegalArgumentException("Update test failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Error connecting to database");
        }
    }

    public void updateGroupsTests(final Tests test) {
        try (Connection connection = DatabaseSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_GROUPS_TESTS_QUERY)) {
            preparedStatement.setBoolean(1, test.getIsNecessary());
            preparedStatement.setInt(2, test.getMaxAttempts());
            preparedStatement.setObject(3, test.getDeadline());
            preparedStatement.setInt(4, test.getTimeLimit());
            preparedStatement.setInt(5, test.getId());
            preparedStatement.setInt(6, test.getGroupId());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new IllegalArgumentException("Update groups_tests failed, no rows affected.");
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
                throw new IllegalArgumentException("Remove test failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Error connecting to database");
        }
    }

    public void removeGroupsTestsByTestAndGroupId(final int testId, final int groupId) {
        try (Connection connection = DatabaseSource.getInstance().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(REMOVE_GROUPS_TESTS_BY_TEST_AND_GROUP_ID_QUERY)) {
            preparedStatement.setInt(1, testId);
            preparedStatement.setInt(2, groupId);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new IllegalArgumentException("Remove groups_tests failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Error connecting to database");
        }
    }

    private void fillGeneralTestObjectFromResultSet(final Tests test, final ResultSet resultSet) {
        try {
            test.setId(resultSet.getInt("id"));
            test.setTitle(resultSet.getString("title"));
            test.setDescription(resultSet.getString("description"));
            test.setSubjectId(resultSet.getInt("subject_id"));
            test.setIsRandom(resultSet.getBoolean("is_random"));
            test.setCreatedAt(resultSet.getObject("created_at", LocalDateTime.class));
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
            test.setDeadline(resultSet.getObject("deadline", LocalDateTime.class));
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
            preparedStatement.setObject(5, test.getCreatedAt());
            preparedStatement.setInt(6, test.getMaxPoints());
            preparedStatement.setInt(7, test.getCreatorId());
        } catch (SQLException e) {
            System.err.println("Error with fill tests query");
            e.printStackTrace();
        }
    }

    private void fillTestForGroupQueryFromObject(final Tests test, final PreparedStatement preparedStatement) {
        try {
            preparedStatement.setInt(1, test.getId());
            preparedStatement.setInt(2, test.getGroupId());
            preparedStatement.setBoolean(3, test.getIsNecessary());
            preparedStatement.setInt(4, test.getMaxAttempts());
            preparedStatement.setObject(5, test.getDeadline());
            preparedStatement.setInt(6, test.getTimeLimit());
        } catch (SQLException e) {
            System.err.println("Error with filling groups tests query");
            e.printStackTrace();
        }
    }
}
