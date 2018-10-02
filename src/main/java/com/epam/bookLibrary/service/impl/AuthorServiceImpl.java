package com.epam.bookLibrary.service.impl;

import com.epam.bookLibrary.exception.AuthorIsAssignedToBookException;
import com.epam.bookLibrary.model.Author;
import com.epam.bookLibrary.model.Person;
import com.epam.bookLibrary.repository.AuthorDAO;
import com.epam.bookLibrary.service.AuthorService;
import com.epam.bookLibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorDAO authorDAO;

    @Autowired
    private BookService bookService;

    @Override
    public long addAuthor(Author author) {
        return authorDAO.add(author);
    }

    @Override
    public long deleteAuthor(long id) {
        if (bookService.isAuthorUsed(id)) {
            throw new AuthorIsAssignedToBookException(getAuthorById(id).getPerson().toString());
        }
        return authorDAO.delete(id);
    }

    @Override
    public List<Author> getAuthors() {
        return authorDAO.getAll();
    }

    @Override
    public Author getAuthorById(long id) {
        return authorDAO.getById(id);
    }

    @Override
    public List<Author> getAuthorByShortName(String name, String surname) {
        return authorDAO.getByShortName(name, surname);
    }

    @Override
    public List<Author> getAuthorByPerson(Person person) {
        return authorDAO.getByPerson(person);
    }


    @Override
    public long setAuthor(long id, Author author) {
        return authorDAO.set(id, author);
    }
}
