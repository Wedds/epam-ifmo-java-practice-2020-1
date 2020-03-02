package com.ifmo.epampractice.service;

import com.ifmo.epampractice.dao.UsersDAO;
import com.ifmo.epampractice.entity.Users;
import com.ifmo.epampractice.enums.Roles;
import com.ifmo.epampractice.utilities.SecurityUtilities;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class UsersService {
    private static UsersService instance;
    private static final UsersDAO USERS_DAO = new UsersDAO();
    private static final int DEFAULT_GROUP = 1;

    public static UsersService getInstance() {
        if (instance != null) {
            return instance;
        } else {
            instance = new UsersService();
        }
        return instance;
    }

    public Users getById(final int id) {
        Optional<Users> userOptional = USERS_DAO.getById(id);
        if (!userOptional.isPresent()) {
            throw new IllegalStateException("User not found!");
        }
        return userOptional.get();
    }

    public Users getByEmail(final String email) {
        Optional<Users> userOptional = USERS_DAO.getByEmail(email);
        if (!userOptional.isPresent()) {
            throw new IllegalStateException("User not found!");
        }
        return userOptional.get();
    }

    public Users addObject(final Users user) {
        return USERS_DAO.addObject(user);
    }

    public void updateByObject(final Users user) {
        USERS_DAO.updateByObject(user);
    }

    public List<Users> getAll() {
        return USERS_DAO.getAll();
    }

    public List<Users> getAllByGroupId(final int id) {
        return USERS_DAO.getAllByGroupId(id);
    }


    public Users createUser(final Users user, final String password) {
        if (user.getBirthDate() == null
                || user.getEmail() == null
                || user.getFirstName() == null
                || user.getLastName() == null
                || password == null
        ) {
            throw new IllegalArgumentException("The required fields are not filled in");
        }
        setDefaultValues(password, user);
        return USERS_DAO.addObject(user);
    }

    public Users signUp(
            final String email,
            final String password,
            final String firstName,
            final String lastName,
            final Date birthDate) {
        Users user = new Users();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setBirthDate(birthDate);
        setDefaultValues(password, user);
        return addObject(user);
    }

    public boolean login(final String email, final String password) {
        Users user = getByEmail(email);
        String md5Password = SecurityUtilities.generateHash(password, user.getSalt());
        return user.getHash().equals(md5Password);
    }

    private void setDefaultValues(final String password, final Users user) {
        String salt = SecurityUtilities.generateSalt();
        user.setSalt(salt);
        user.setHash(SecurityUtilities.generateHash(password, salt));
        user.setRoleType(Roles.STUDENT);
        user.setGroupId(DEFAULT_GROUP);
        user.setCreatedAt(LocalDateTime.now());
    }

    public Boolean ifUserObjectExist(final int id) {
        return (USERS_DAO.getById(id).isPresent());
    }
}
