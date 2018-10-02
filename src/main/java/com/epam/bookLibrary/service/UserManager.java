package com.epam.bookLibrary.service;

import com.epam.bookLibrary.model.User;

public interface UserManager {
    void setUser(User user);

    User getUser();
}
