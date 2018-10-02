package com.epam.bookLibrary.repository.impl;

import com.epam.bookLibrary.repository.BookDAO;
import com.epam.bookLibrary.repository.mappers.BookMapper;
import com.epam.bookLibrary.model.Book;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDAOImpl extends AbstractBasicDAOImpl<Book> implements BookDAO {
    private static String selectBooks = "SELECT \n" +
            "\tb.idBook idBook\n" +
            "    , title\n" +
            "    , b.idAuthor idAuthor\n" +
            "    , name\n" +
            "    , patronymic\n" +
            "    , surname\n" +
            "    , b.idGenre idGenre\n" +
            "    , description\n" +
            "FROM book b\n" +
            "LEFT JOIN author a \n" +
            "ON  (b.idAuthor = a.idAuthor)\n" +
            "LEFT JOIN genre g\n" +
            "ON (b.idGenre = g.idGenre) ";

    private static String notIn = " idBook NOT IN (SELECT idBook idbr FROM bookrequest)";


    public BookDAOImpl() {
        super(new BookMapper(), "book");
    }

    @Override
    public List<Book> getByTitle(String title) {
        return getJdbcTemplate().query(selectBooks + " WHERE title LIKE ?"
                , new Object[]{"%" + title + "%"}, getRowMapper());
    }

    @Override
    public List<Book> getByGenre(String desc) {
        return getJdbcTemplate().query(selectBooks + " WHERE description LIKE ?"
                , new Object[]{"%" + desc + "%"}, getRowMapper());
    }

    @Override
    public List<Book> getByAuthor(String name) {
        String converted = "%" + name + "%";
        return getJdbcTemplate().query(selectBooks
                        + " WHERE name LIKE ? OR patronymic LIKE ? OR surname LIKE ?"
                , new Object[]{converted, converted, converted}, getRowMapper());
    }

    @Override
    public List<Book> getByAuthorId(long authorId) {
        return getJdbcTemplate().query(selectBooks
                        + " WHERE b.idAuthor = ?"
                , new Object[]{authorId}, getRowMapper());
    }

    @Override
    public List<Book> getByGenreId(long genreId) {
        return getJdbcTemplate().query(selectBooks
                        + " WHERE b.idGenre = ?"
                , new Object[]{genreId}, getRowMapper());
    }

    @Override
    public List<Book> getNotRequestedByTitle(String title) {
        return getJdbcTemplate().query(selectBooks + " WHERE title LIKE ? AND" + notIn
                , new Object[]{"%" + title + "%"}, getRowMapper());
    }

    @Override
    public List<Book> getNotRequestedByGenre(String desc) {
        return getJdbcTemplate().query(selectBooks + " WHERE description LIKE ? AND" + notIn
                , new Object[]{"%" + desc + "%"}, getRowMapper());
    }

    @Override
    public List<Book> getNotRequestedByAuthor(String name) {
        String converted = "%" + name + "%";
        return getJdbcTemplate().query(selectBooks
                        + " WHERE (name LIKE ? OR patronymic LIKE ? OR surname LIKE ?) AND" + notIn
                , new Object[]{converted, converted, converted}, getRowMapper());
    }

    @Override
    public List<Book> getNotRequested() {
        return getJdbcTemplate().query(selectBooks
                + " WHERE" + notIn, getRowMapper());
    }

    @Override
    public Book getById(long id) {
        try {
            return getJdbcTemplate().queryForObject(selectBooks + "WHERE idBook = ?", new Object[]{id}, getRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Book> getAll() {
        return getJdbcTemplate().query(selectBooks, getRowMapper());
    }

    @Override
    public long add(Book entity) {
        return getJdbcTemplate().update("INSERT INTO "
                        + getTable() + "(title, idAuthor, idGenre) VALUES"
                        + "(?,?,?)"
                , entity.getTitle()
                , entity.getAuthor().getId()
                , entity.getGenre().getId());
    }

    @Override
    public long set(long id, Book entity) {
        return getJdbcTemplate().update(
                "UPDATE "
                        + getTable()
                        + " SET title = ?, idAuthor = ?, idGenre = ?"
                        + getWhereId()
                , entity.getTitle()
                , entity.getAuthor().getId()
                , entity.getGenre().getId()
                , id);
    }
}
