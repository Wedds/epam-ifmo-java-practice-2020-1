package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.Tests;
import com.ifmo.epampractice.service.DatabaseService;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestsDAO extends DatabaseService {

    //private Connection connection = getConnection();

    public void add(Tests test) throws SQLException {

        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO TESTS(title, subject_id, is_random, creator_id) VALUES(?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, test.getTitle());
            preparedStatement.setInt(2, test.getSubjectId());
            preparedStatement.setBoolean(3, test.isRandom());
            preparedStatement.setInt(4, test.getCreatorId());

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

    public List<Tests> getAll() throws SQLException {

        Connection connection = getConnection();

        List<Tests> testsList = new ArrayList<>();
        String query = "SELECT id, title, subject_id, is_random, creator_id FROM TESTS";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Tests test = new Tests();
                test.setId(resultSet.getInt("id"));
                test.setTitle(resultSet.getString("title"));
                test.setSubjectId(resultSet.getInt("subject_id"));
                test.setRandom(resultSet.getBoolean("is_random"));
                test.setCreatorId(resultSet.getInt("creator_id"));
                testsList.add(test);
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

        return testsList;
    }

    public Tests getById(int id) throws SQLException {

        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;
        String query = "SELECT title, subject_id, is_random, creator_id FROM TESTS WHERE id=?";
        Tests test = new Tests();
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            test.setId(id);
            test.setTitle(resultSet.getString("title"));
            test.setSubjectId(resultSet.getInt("subject_id"));
            test.setRandom(resultSet.getBoolean("is_random"));
            test.setCreatorId(resultSet.getInt("creator_id"));
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
        return test;
    }

    public void update(Tests test) throws SQLException {

        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        String query = "UPDATE TESTS SET title=?, subject_id=?, is_random=?, creator_id=? WHERE id=?";
        try {
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, test.getTitle());
            preparedStatement.setInt(2, test.getSubjectId());
            preparedStatement.setBoolean(3, test.isRandom());
            preparedStatement.setInt(4, test.getCreatorId());
            preparedStatement.setInt(5, test.getId());

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

    public void remove(Tests test) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM TESTS WHERE id=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, test.getId());
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