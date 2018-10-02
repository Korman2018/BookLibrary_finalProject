package com.epam.bookLibrary.service;

import com.epam.bookLibrary.model.Genre;

import java.util.List;

public interface GenreService {

    long addGenre(String description);

    long deleteGenre(long id);

    List<Genre> getGenres();

    long setGenre(long id, String description);

    void setGenreAndCheck(long id, String description);
}
