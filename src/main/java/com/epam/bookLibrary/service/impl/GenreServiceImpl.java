package com.epam.bookLibrary.service.impl;

import com.epam.bookLibrary.exception.FailedToSetGenreException;
import com.epam.bookLibrary.exception.GenreExistException;
import com.epam.bookLibrary.model.Genre;
import com.epam.bookLibrary.repository.GenreDAO;
import com.epam.bookLibrary.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreDAO genreDAO;

    @Override
    public long addGenre(String description) {
        try {
            return genreDAO.add(new Genre(description));
        } catch (DuplicateKeyException ex) {
            throw new GenreExistException(description);
        }
    }

    @Override
    public long deleteGenre(long id) {
        return genreDAO.delete(id);
    }

    @Override
    public List<Genre> getGenres() {
        return genreDAO.getAll();
    }

    @Override
    public long setGenre(long id, String description) {
        try {
            return genreDAO.set(id, new Genre(description));
        } catch (DuplicateKeyException ex) {
            throw new GenreExistException(description);
        }
    }

    @Override
    public void setGenreAndCheck(long id, String description) {
        if (setGenre(id, description) != 1) {
            throw new FailedToSetGenreException(description);
        }
    }
}
