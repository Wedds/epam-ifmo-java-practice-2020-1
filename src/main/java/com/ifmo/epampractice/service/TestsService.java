package com.ifmo.epampractice.service;

import com.ifmo.epampractice.dao.TestsDAO;
import com.ifmo.epampractice.entity.Tests;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TestsService extends DatabaseService implements TestsDAO {

    Connection connection = getConnection();

    @Override
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

    @Override
    public List<Tests> getAll() {
        return null;
    }

    @Override
    public Tests getById(int id) {
        return null;
    }

    @Override
    public void update(Tests test) {

    }

    @Override
    public void remove(Tests test) {

    }
}
