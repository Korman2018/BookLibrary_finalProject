package com.epam.bookLibrary.service;

import com.epam.bookLibrary.model.*;

import java.util.List;

public interface BookService {

    long addBook(String title, long authorId, long genreId);

    long deleteBook(long id);

    List<Book> getBooks();

    List<Book> getBooksBy(String type, String search);

    List<Book> getNotRequested();

    boolean isAuthorUsed(long authorId);

    boolean isGenreUsed(long genreId);

    List<Book> getNotRequestedBooks(String type, String searchString);

    Book getById(long id);

    long setBook(long id, String title, long authorId, long genreId);

    void setBookAndCheck(long id, String title, long authorId, long genreId);

    boolean isBookRequested(long bookId);
}
