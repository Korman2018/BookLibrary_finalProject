package com.epam.bookLibrary.repository;

import com.epam.bookLibrary.model.Book;

import java.util.List;

public interface BookDAO extends BasicDAO<Book> {
    List<Book> getByTitle(String title);

    List<Book> getByGenre(String desc);

    List<Book> getByAuthor(String name);

    List<Book> getByAuthorId(long authorId);

    List<Book> getByGenreId(long genreId);

    List<Book> getNotRequested();

    List<Book> getNotRequestedByTitle(String title);

    List<Book> getNotRequestedByGenre(String desc);

    List<Book> getNotRequestedByAuthor(String name);
}
