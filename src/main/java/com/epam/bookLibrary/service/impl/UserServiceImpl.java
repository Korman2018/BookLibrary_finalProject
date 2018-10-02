package com.epam.bookLibrary.service.impl;

import com.epam.bookLibrary.model.User;
import com.epam.bookLibrary.repository.UserDAO;
import com.epam.bookLibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public boolean checkAndAddUser(User user) {
        User findUser = getUserByLogin(user.getLogin());
        if (findUser != null) {
            return false;
        }
        userDAO.add(user);
        return true;
    }

    @Override
    public long deleteUser(long id) {
        return userDAO.delete(id);
    }

    @Override
    public List<User> getUsers() {
        return userDAO.getAll();
    }

    @Override
    public User getUserById(long id) {
        return userDAO.getById(id);
    }

    @Override
    public long setUser(long id, User user) {
        return userDAO.set(id, user);
    }

    @Override
    public User getUserByLogin(String login) {
        if (isEmpty(login)) {
            return null;
        }
        return userDAO.getByLogin(login);
    }

    @Override
    public boolean checkAndUpdateUser(User user) {
        User findUser = getUserByLogin(user.getLogin());
        if (findUser != null && findUser.getId() != user.getId()) {
            return false;
        }
        setUser(user.getId(), user);
        return true;
    }

    @Override
    public User checkLoginAndPasswordAndGetUser(String login, String password) {
        User user = getUserByLogin(login);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
