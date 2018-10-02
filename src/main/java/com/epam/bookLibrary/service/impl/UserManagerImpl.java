package com.epam.bookLibrary.service.impl;

import com.epam.bookLibrary.model.User;
import com.epam.bookLibrary.service.UserManager;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Service
public class UserManagerImpl implements UserManager {

    private User user;

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }
}
