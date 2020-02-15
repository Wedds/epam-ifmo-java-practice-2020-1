package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.Groups;
import com.ifmo.epampractice.entity.GroupsTests;
import com.ifmo.epampractice.service.IDAO;
import com.ifmo.epampractice.service.DatabaseSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class GroupsTestsDAO extends DatabaseSource implements IDAO<GroupsTests> {
    private static final String addQuery = "INSERT INTO groups_tests(group_id, test_id, is_neccesarry, " +
            "max_attemps, deadline, time_limit) VALUES(?,?,?,?,?,?)";
    private static final String getAllQuery = "SELECT group_id, test_id, is_neccessary, max_attemps, " +
            "time_limit, deadline FROM groups";
    private static final String getByGroupIdAndTestIdQuery = "SELECT id, is_neccessary, max_attempts, " +
            "time_limit, deadline FROM groups_tests WHERE group_id=? AND test_id=?";
    private static final String updateQuery = "UPDATE groups_tests SET is_neccessary=?, " +
            "max_attempts=?, time_limit=?, deadline=? WHERE group_id=?, test_id=?";
    private static final String removeQuery = "DELETE FROM groups_tests WHERE group_id=?, test_id=?";

    @Override
    public void add(GroupsTests groupTest) throws SQLException {

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(addQuery)) {
            preparedStatement.setInt(1, groupTest.getGroupId());
            preparedStatement.setInt(2, groupTest.getTestId());
            preparedStatement.setBoolean(3, groupTest.getIsNecessary());
            preparedStatement.setInt(4, groupTest.getMaxAttempts());
            preparedStatement.setDate(5, groupTest.getDeadline());
            preparedStatement.setInt(6, groupTest.getTimeLimit());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<GroupsTests> getAll()throws SQLException {
        List<GroupsTests> groupsTestsList = new ArrayList<>();

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(getAllQuery);

            while (resultSet.next()) {
                GroupsTests groupTest = new GroupsTests();

                groupTest.setGroupId(resultSet.getInt("group_id"));
                groupTest.setTestId(resultSet.getInt("test_id"));
                groupTest.setIsNecessary(resultSet.getBoolean("is_neccesary"));
                groupTest.setMaxAttempts(resultSet.getInt("max_attempts"));
                groupTest.setTimeLimit(resultSet.getInt("time_limit"));
                groupTest.setDeadline(resultSet.getDate("deadline"));
                groupsTestsList.add(groupTest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groupsTestsList;
    }

    public GroupsTests getByGroupAndTestId(int groupId, int testId) throws SQLException{
        GroupsTests groupTest = new GroupsTests();

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(getByGroupIdAndTestIdQuery)) {
            preparedStatement.setInt(1, groupId);
            preparedStatement.setInt(2, testId);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            groupTest.setGroupId(groupId);
            groupTest.setTestId(testId);
            groupTest.setIsNecessary(resultSet.getBoolean("is_neccesary"));
            groupTest.setMaxAttempts(resultSet.getInt("max_attempts"));
            groupTest.setTimeLimit(resultSet.getInt("time_limit"));
            groupTest.setDeadline(resultSet.getDate("deadline"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groupTest;

    }

    @Override
    public void update(GroupsTests groupTest) throws SQLException {
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setBoolean(1, groupTest.getIsNecessary());
            preparedStatement.setInt(2, groupTest.getMaxAttempts());
            preparedStatement.setInt(3, groupTest.getTimeLimit());
            preparedStatement.setDate(4, groupTest.getDeadline());
            preparedStatement.setInt(5, groupTest.getGroupId());
            preparedStatement.setInt(6, groupTest.getTestId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(GroupsTests groupTest) throws SQLException {

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(removeQuery)) {
            preparedStatement.setInt(1, groupTest.getGroupId());
            preparedStatement.setInt(2, groupTest.getTestId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
