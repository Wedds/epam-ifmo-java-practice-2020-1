package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.Groups;
import com.ifmo.epampractice.entity.GroupsTests;
import com.ifmo.epampractice.service.DatabaseService;
import com.ifmo.epampractice.service.DatabaseSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupsTestsDAO extends DatabaseSource implements DatabaseService<GroupsTests> {

    private static final String addQuery = "INSERT INTO groups_tests(group_id, test_id, is_neccesarry, " +
            "max_attemps, time_limit, deadline) VALUES(?,?,?,?,?,?)";
    private static final String getAllQuery = "SELECT id, group_id, test_id, is_neccessary, max_attemps, " +
            "time_limit, deadline FROM groups";
    private static final String getByIdQuery = "SELECT group_id, test_id, is_neccessary, max_attemps, " +
            "time_limit, deadline FROM groups_tests WHERE id=?";
    private static final String updateQuery = "UPDATE groups_tests SET group_id=?, test_id=?, is_neccessary=?, " +
            "max_attempts=?, time_limit=?, deadline=? WHERE id=?";
    private static final String removeQuery = "DELETE FROM groups_tests WHERE id=?";
    private static final String getByGroupIdAndTestIdQuery = "SELECT id, is_neccessary, max_attempts, " +
            "time_limit, deadline FROM groups_tests WHERE group_id=? AND test_id=?";

    @Override
    public void add(GroupsTests groupTest) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(addQuery);
            preparedStatement.setInt(1, groupTest.getGroup_id());
            preparedStatement.setInt(2, groupTest.getTest_id());
            preparedStatement.setBoolean(3, groupTest.isIsNeccessary());
            preparedStatement.setInt(4, groupTest.getMaxAttemps());
            preparedStatement.setInt(5, groupTest.getTimeLimit());
            preparedStatement.setDate(6, groupTest.getDeadline());

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
    public List<GroupsTests> getAll()throws SQLException {
        Connection connection = getConnection();

        List<GroupsTests> groupsTestsList = new ArrayList<>();

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getAllQuery);

            while (resultSet.next()) {
                GroupsTests groupTest = new GroupsTests();
                groupTest.setId(resultSet.getInt("id"));
                groupTest.setGroup_id(resultSet.getInt("group_id"));
                groupTest.setTest_id(resultSet.getInt("test_id"));
                groupTest.setIsNeccessary(resultSet.getBoolean("is_neccesary"));
                groupTest.setMaxAttemps(resultSet.getInt("max_attempts"));
                groupTest.setTimeLimit(resultSet.getInt("time_limit"));
                groupTest.setDeadline(resultSet.getDate("deadline"));
                groupsTestsList.add(groupTest);
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
        return groupsTestsList;
    }

    @Override
    public GroupsTests getById(int id) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;

        GroupsTests groupTest = new GroupsTests();
        try {
            preparedStatement = connection.prepareStatement(getByIdQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            groupTest.setId(id);
            groupTest.setGroup_id(resultSet.getInt("group_id"));
            groupTest.setTest_id(resultSet.getInt("test_id"));
            groupTest.setIsNeccessary(resultSet.getBoolean("is_neccesary"));
            groupTest.setMaxAttemps(resultSet.getInt("max_attempts"));
            groupTest.setTimeLimit(resultSet.getInt("time_limit"));
            groupTest.setDeadline(resultSet.getDate("deadline"));
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
        return groupTest;
    }

    @Override
    public void update(GroupsTests groupTest) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(updateQuery);

            preparedStatement.setInt(1, groupTest.getGroup_id());
            preparedStatement.setInt(2, groupTest.getTest_id());
            preparedStatement.setBoolean(3, groupTest.isIsNeccessary());
            preparedStatement.setInt(4, groupTest.getMaxAttemps());
            preparedStatement.setInt(5, groupTest.getTimeLimit());
            preparedStatement.setDate(6, groupTest.getDeadline());
            preparedStatement.setInt(7, groupTest.getId());

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
    public void remove(GroupsTests groupTest) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(removeQuery);
            preparedStatement.setInt(1, groupTest.getId());
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

    public GroupsTests getByGroupAndTestId(int groupId, int testId) throws SQLException{
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;

        GroupsTests groupTest = new GroupsTests();
        try {
            preparedStatement = connection.prepareStatement(getByGroupIdAndTestIdQuery);
            preparedStatement.setInt(1, groupId);
            preparedStatement.setInt(2, testId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            groupTest.setId(resultSet.getInt("id"));
            groupTest.setGroup_id(groupId);
            groupTest.setTest_id(testId);
            groupTest.setIsNeccessary(resultSet.getBoolean("is_neccesary"));
            groupTest.setMaxAttemps(resultSet.getInt("max_attempts"));
            groupTest.setTimeLimit(resultSet.getInt("time_limit"));
            groupTest.setDeadline(resultSet.getDate("deadline"));
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
        return groupTest;

    }

}
