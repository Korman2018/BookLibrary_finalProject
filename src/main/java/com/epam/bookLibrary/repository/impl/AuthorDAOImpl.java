package com.epam.bookLibrary.repository.impl;

import com.epam.bookLibrary.repository.AuthorDAO;
import com.epam.bookLibrary.repository.mappers.AuthorMapper;
import com.epam.bookLibrary.model.Author;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorDAOImpl extends AbstractPersonDAOImpl<Author> implements AuthorDAO {

    public AuthorDAOImpl() {
        super(new AuthorMapper(),"author");
    }
}
