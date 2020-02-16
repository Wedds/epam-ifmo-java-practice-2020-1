package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.Roles;
import com.ifmo.epampractice.entity.Users;
import com.ifmo.epampractice.service.DatabaseSource;
import com.ifmo.epampractice.service.IDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO extends DatabaseSource implements IDAO<Users> {
    private static final String INSERT_QUERY = "INSERT INTO users (role_type, email, hash, salt, first_name, last_name, " +
            "middle_name, birth_date, work_title, created_at, avatar, group_id) VALUES(?::roles,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SELECT_ALL_QUERY = "SELECT id, role_type, email, hash, salt, first_name, last_name, " +
            "middle_name, birth_date, work_title, created_at, avatar, group_id FROM users";
    private static final String SELECT_BY_ID_QUERY = "SELECT id, role_type, email, hash, salt, first_name, last_name, " +
            "middle_name, birth_date, work_title, created_at, avatar, group_id FROM users WHERE id=?";
    private static final String UPDATE_QUERY = "UPDATE users SET role_type=?::roles, email=?, hash=?, salt=?, first_name=?, last_name=?, " +
            "middle_name=?, birth_date=?, work_title=?, created_at=?, avatar=?, group_id=? WHERE id=?";
    private static final String REMOVE_QUERY = "DELETE FROM users WHERE id=?";


    @Override
    public void add(Users user) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            try {
                this.convertObjectToFields(user, preparedStatement);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Users> getAll() throws SQLException {
        ArrayList<Users> usersArrayList = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            try {
                ResultSet rs = statement.executeQuery(SELECT_ALL_QUERY);
                while (rs.next()) {
                    usersArrayList.add(this.convertFieldsToObject(rs));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return usersArrayList;
    }

    @Override
    public Users getById(int id) throws SQLException {
        Users user = new Users();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            try {
                preparedStatement.setInt(1, id);
                ResultSet rs = preparedStatement.executeQuery();
                rs.next();
                user = this.convertFieldsToObject(rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public void update(Users user) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            try {
                this.convertObjectToFields(user, preparedStatement);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void remove(Users user) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_QUERY)) {
            try {
                preparedStatement.setInt(1, user.getId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void convertObjectToFields(Users user, PreparedStatement ps) throws SQLException {
        String roleName = user.getRoleType().name().toLowerCase();
        ps.setString(1, roleName);
        ps.setString(2, user.getEmail());
        ps.setString(3, user.getHash());
        ps.setString(4, user.getSalt());
        ps.setString(5, user.getFirstName());
        ps.setString(6, user.getLastName());
        ps.setString(7, user.getMiddleName());
        ps.setDate(8, user.getBirthDate());
        ps.setString(9, user.getWorkTitle());
        ps.setDate(10, user.getCreatedAt());
        ps.setString(11, user.getAvatar());
        ps.setInt(12, user.getGroupId());

        if (user.getId() != 0) {
            ps.setInt(13, user.getId());
        }
    }

    private Users convertFieldsToObject(ResultSet rs) throws SQLException {
        Users user = new Users();
        user.setId(rs.getInt("id"));
        switch (rs.getString("role_type")) {
            case "admin": {
                user.setRoleType(Roles.ADMIN);
                break;
            }
            case "moderator": {
                user.setRoleType(Roles.MODERATOR);
                break;
            }
            case "teacher": {
                user.setRoleType(Roles.TEACHER);
                break;
            }
            case "student": {
                user.setRoleType(Roles.STUDENT);
                break;
            }
        }

        user.setEmail(rs.getString("email"));
        user.setHash(rs.getString("hash"));
        user.setSalt(rs.getString("salt"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setMiddleName(rs.getString("middle_name"));
        user.setBirthDate(rs.getDate("birth_date"));
        user.setWorkTitle(rs.getString("work_title"));
        user.setCreatedAt(rs.getDate("created_at"));
        user.setAvatar(rs.getString("avatar"));
        user.setGroupId(rs.getInt("group_id"));

        return user;
    }
}
