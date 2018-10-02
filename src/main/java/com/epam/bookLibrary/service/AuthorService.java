package com.epam.bookLibrary.service;

import com.epam.bookLibrary.model.Author;
import com.epam.bookLibrary.model.Person;

import java.util.List;

public interface AuthorService {

    long addAuthor(Author author);

    long deleteAuthor(long id);

    List<Author> getAuthors();

    Author getAuthorById(long id);

    List<Author> getAuthorByShortName(String name, String surname);

    List<Author> getAuthorByPerson(Person person);

    long setAuthor(long id, Author author);
}
