package com.epam.bookLibrary.repository;

import com.epam.bookLibrary.model.User;

import java.util.List;

public interface UserDAO extends BasicPersonDAO<User> {
    User getByLogin(String login);

    List<User> getLibrarians();

    List<User> getReaders();
}
