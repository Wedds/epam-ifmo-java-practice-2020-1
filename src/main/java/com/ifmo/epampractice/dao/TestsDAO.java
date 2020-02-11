package com.ifmo.epampractice.dao;
import com.ifmo.epampractice.entity.Tests;
import com.ifmo.epampractice.service.DatabaseService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TestsDAO extends DatabaseService   {

    Connection connection = getConnection();

    public void add(Tests test) throws SQLException {
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO TESTS(title, subject_id, is_random, creator_id) VALUES(?,?,?,?)";
        try{
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, test.getTitle());
            preparedStatement.setInt(2, test.getSubject().getId());
            preparedStatement.setBoolean(3, test.isRandom());
            preparedStatement.setInt(4, test.getCreator().getId());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (preparedStatement != null){
                preparedStatement.close();
            }
            if (connection != null){
                connection.close();
            }
        }
    }

    public List<Tests> getAll() {
        return null;
    }

    public Tests getById(int id) {
        return null;
    }

    public void update(Tests test) {

    }

    public void remove(Tests test) {

    }
}