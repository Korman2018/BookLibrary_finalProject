package com.epam.bookLibrary.model;

import com.epam.bookLibrary.model.abstracts.AbstractPersonContainer;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class User extends AbstractPersonContainer implements Serializable {
    @NotEmpty
    @Size(min = 3, max = 10)
    private String login;

    @NotEmpty
    @Size(min = 3, max = 40)
    private String password;

    private boolean isLibrarian;

    private User() {
    }

    public User(Person person, String login, String password, boolean isLibrarian) {
        super(person);
        this.login = login;
        this.password = password;
        this.isLibrarian = isLibrarian;
    }

    public User(Person person, String login, String password) {
        this(person, login, password, false);
    }

    public boolean isLibrarian() {
        return isLibrarian;
    }

    public void setLibrarian(boolean librarian) {
        isLibrarian = librarian;
    }

    public boolean getIsLibrarian() {
        return isLibrarian;
    }

    public void setIsLibrarian(boolean librarian) {
        isLibrarian = librarian;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return getId() + ". " + getPerson() + "  логин: " + login + (isLibrarian ? " (библиотекарь)" : "(читатель))");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            User user = (User) obj;
            return login != null && login.equals(user.getLogin());
        }
        return false;
    }
}
