package com.epam.bookLibrary.service.impl;

import com.epam.bookLibrary.exception.FailedToSetBookException;
import com.epam.bookLibrary.repository.BookDAO;
import com.epam.bookLibrary.model.Author;
import com.epam.bookLibrary.model.Book;
import com.epam.bookLibrary.model.Genre;
import com.epam.bookLibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDAO bookDAO;

    @Override
    public long addBook(String title, long authorId, long genreId) {
        Author author = new Author();
        Genre genre = new Genre();
        author.setId(authorId);
        genre.setId(genreId);
        Book book = new Book(title, author, genre);
        return bookDAO.add(book);
    }

    @Override
    public long deleteBook(long id) {
        return bookDAO.delete(id);
    }

    @Override
    public List<Book> getBooks() {
        return bookDAO.getAll();
    }

    @Override
    public List<Book> getBooksBy(String type, String searchString) {
        switch (type) {
            case "author":
                return bookDAO.getByAuthor(searchString);
            case "title":
                return bookDAO.getByTitle(searchString);
            case "genre":
                return bookDAO.getByGenre(searchString);
            default:
                return getBooks();
        }
    }

    @Override
    public List<Book> getNotRequested() {
        return bookDAO.getNotRequested();
    }

    @Override
    public boolean isAuthorUsed(long authorId) {
        return !bookDAO.getByAuthorId(authorId).isEmpty();
    }

    @Override
    public boolean isGenreUsed(long genreId) {
        return !bookDAO.getByGenreId(genreId).isEmpty();
    }

    @Override
    public List<Book> getNotRequestedBooks(String type, String searchString) {
        switch (type) {
            case "author":
                return bookDAO.getNotRequestedByAuthor(searchString);
            case "title":
                return bookDAO.getNotRequestedByTitle(searchString);
            case "genre":
                return bookDAO.getNotRequestedByGenre(searchString);
            default:
                return getNotRequested();
        }
    }

    @Override
    public Book getById(long id) {
        return bookDAO.getById(id);
    }

    @Override
    public long setBook(long id, String title, long authorId, long genreId) {
        Author author = new Author();
        Genre genre = new Genre();
        author.setId(authorId);
        genre.setId(genreId);
        Book book = new Book(title, author, genre);
        return bookDAO.set(id, book);
    }

    @Override
    public void setBookAndCheck(long id, String title, long authorId, long genreId) {
        if (setBook(id,title,authorId,genreId) != 1) {
            throw new FailedToSetBookException();
        }
    }

    @Override
    public boolean isBookRequested(long bookId) {
        return getNotRequested()
                .stream()
                .filter(x -> x.getId() == bookId)
                .collect(Collectors.toList()).isEmpty();
    }
}
