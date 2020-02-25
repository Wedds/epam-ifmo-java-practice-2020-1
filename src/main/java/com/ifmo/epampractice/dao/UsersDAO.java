package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.Users;
import com.ifmo.epampractice.enums.Roles;
import com.ifmo.epampractice.service.DatabaseSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersDAO implements DAO<Users> {
    private static final String INSERT_QUERY = "INSERT INTO users (role_type, email, hash, salt, first_name, " +
            "last_name, middle_name, birth_date, work_title, created_at, avatar, group_id) " +
            "VALUES(?::roles,?,?,?,?,?,?,?,?,?,?,?) ";
    private static final String SELECT_ALL_QUERY = "SELECT id, role_type, email, hash, salt, first_name, " +
            "last_name, middle_name, birth_date, work_title, created_at, avatar, group_id FROM users";
    private static final String SELECT_BY_ID_QUERY = "SELECT id, role_type, email, hash, salt, first_name," +
            " last_name, middle_name, birth_date, work_title, created_at, avatar, group_id FROM users WHERE id=?";
    private static final String UPDATE_QUERY = "UPDATE users SET role_type=?::roles, email=?, hash=?, salt=?," +
            " first_name=?, last_name=?, middle_name=?, birth_date=?, work_title=?, created_at=?, avatar=?," +
            " group_id=? WHERE id=?";
    private static final String REMOVE_QUERY = "DELETE FROM users WHERE id=?";


    @Override
    public Users addObject(final Users user) {
        try (Connection connection = DatabaseSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)
        ) {
            this.convertObjectToFields(user, preparedStatement);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new IllegalArgumentException("Creating user failed, no rows affected.");
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                } else {
                    throw new IllegalArgumentException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<Users> getAll() {
        ArrayList<Users> usersArrayList = new ArrayList<>();
        try (Connection connection = DatabaseSource.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet rs = statement.executeQuery(SELECT_ALL_QUERY)) {
                while (rs.next()) {
                    usersArrayList.add(this.convertFieldsToObject(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Getting all users failed. Users are not presented.");
        }
        return usersArrayList;
    }

    @Override
    public Optional<Users> getById(final int id) {
        Users user = new Users();
        user.setId(id);
        try (Connection connection = DatabaseSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }
                user = this.convertFieldsToObject(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.of(user);
    }

    @Override
    public void updateByObject(final Users user) {
        try (Connection connection = DatabaseSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            this.convertObjectToFields(user, preparedStatement);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new IllegalArgumentException("Update user failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeById(final int id) {
        try (Connection connection = DatabaseSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_QUERY)) {
            preparedStatement.setInt(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new IllegalArgumentException("Remove user failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void convertObjectToFields(final Users user, final PreparedStatement ps) throws SQLException {
        String roleName = user.getRoleType().name().toLowerCase();
        final int avatarPosition = 11;
        final int groupIdPosition = 12;
        final int idPosition = 13;
        ps.setString(1, roleName);
        ps.setString(2, user.getEmail());
        ps.setString(3, user.getHash());
        ps.setString(4, user.getSalt());
        ps.setString(5, user.getFirstName());
        ps.setString(6, user.getLastName());
        ps.setString(7, user.getMiddleName());
        ps.setDate(8, user.getBirthDate());
        ps.setString(9, user.getWorkTitle());
        ps.setObject(10, user.getCreatedAt());
        ps.setString(avatarPosition, user.getAvatar());
        ps.setInt(groupIdPosition, user.getGroupId());

        if (user.getId() != 0) {
            ps.setInt(idPosition, user.getId());
        }
    }

    private Users convertFieldsToObject(final ResultSet rs) throws SQLException {
        Users user = new Users();
        user.setId(rs.getInt("id"));
        user.setRoleType(Roles.getRoleFromString(rs.getString("role_type")));
        user.setEmail(rs.getString("email"));
        user.setHash(rs.getString("hash"));
        user.setSalt(rs.getString("salt"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setMiddleName(rs.getString("middle_name"));
        user.setBirthDate(rs.getDate("birth_date"));
        user.setWorkTitle(rs.getString("work_title"));
        user.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
        user.setAvatar(rs.getString("avatar"));
        user.setGroupId(rs.getInt("group_id"));

        return user;
    }
}
