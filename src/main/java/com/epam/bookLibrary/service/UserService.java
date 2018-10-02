package com.epam.bookLibrary.service;

import com.epam.bookLibrary.model.User;

import java.util.List;

public interface UserService {

    boolean checkAndAddUser(User user);

    long deleteUser(long id);

    List<User> getUsers();

    User getUserById(long id);

    long setUser(long id, User user);

    User getUserByLogin(String login);

    boolean checkAndUpdateUser(User user);

    User checkLoginAndPasswordAndGetUser(String login, String password);
}
