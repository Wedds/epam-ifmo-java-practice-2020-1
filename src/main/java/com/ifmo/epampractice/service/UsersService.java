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
    private static final UsersDAO USERS_DAO = new UsersDAO();
    private static final int DEFAULT_GROUP = 1;
    private static final String DEFAULT_LAST_NAME = "Фамилия";
    private static final String DEFAULT_FIRST_NAME = "Имя";
    private static final String DEFAULT_BIRTH_DATE = "1900-01-01";

    public Users getById(final int id) {
        Optional<Users> userOptional = USERS_DAO.getById(id);
        if (!userOptional.isPresent()){
            throw new IllegalStateException("User not found!");
        }
        return userOptional.get();
    }

    public Users getByEmail(final String email){
        Optional<Users> userOptional = USERS_DAO.getByEmail(email);
        if (!userOptional.isPresent()){
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

    public Users createUser(final String email, final String password) {
        Users user = new Users();
        String salt = SecurityUtilities.generateSalt();

        user.setEmail(email);
        user.setSalt(salt);
        user.setHash(SecurityUtilities.generateHash(password, salt));
        user.setRoleType(Roles.STUDENT);
        user.setFirstName(DEFAULT_FIRST_NAME);
        user.setLastName(DEFAULT_LAST_NAME);
        user.setGroupId(DEFAULT_GROUP);
        user.setCreatedAt(LocalDateTime.now());
        user.setBirthDate(Date.valueOf(DEFAULT_BIRTH_DATE));

        return addObject(user);
    }

    public boolean login(final String email, final String password) {
        Users user = getByEmail(email);
        String md5Password = SecurityUtilities.generateHash(password, user.getSalt());
        return user.getHash().equals(md5Password);
    }

}
