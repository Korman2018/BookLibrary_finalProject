package com.epam.bookLibrary.repository.impl;

import com.epam.bookLibrary.repository.BookRequestDAO;
import com.epam.bookLibrary.repository.mappers.BookRequestMapper;
import com.epam.bookLibrary.model.BookRequest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRequestDAOImpl extends AbstractBasicDAOImpl<BookRequest> implements BookRequestDAO {

    private static final String SELECT_BOOK_REQ = "SELECT \n" +
            "\tidBookRequest\n" +
            "    , br.idReader idReader\n" +
            "    , br.idBook idBook\n" +
            "    , br.idLibrarian idLibrarian\n" +
            "    , br.idStatus idStatus\n" +
            "    , b.idAuthor idAuthor\n" +
            "    , b.idGenre idGenre\n" +
            "    , r.name readerName\n" +
            "    , r.patronymic readerPatronymic\n" +
            "    , r.surname readerSurname\n" +
            "    , r.login readerLogin\n" +
            "    , r.password readerPassword\n" +
            "    , r.isLibrarian readerIsLibrarian\n" +
            "    , l.name librarianName\n" +
            "    , l.patronymic librarianPatronymic\n" +
            "    , l.surname librarianSurname\n" +
            "    , l.login librarianLogin\n" +
            "    , l.password librarianPassword\n" +
            "    , l.isLibrarian librarianIsLibrarian\n" +
            "    , s.description statusDescription\n" +
            "    , title\n" +
            "    , a.name authorName\n" +
            "    , a.patronymic authorPatronymic\n" +
            "    , a.surname authorSurname\n" +
            "    , g.description genreDescription\n" +
            "FROM bookrequest br\n" +
            "LEFT JOIN book b\n" +
            "ON (br.idBook = b.idBook)\n" +
            "LEFT JOIN status s\n" +
            "ON (br.idStatus = s.idStatus)   \n" +
            "LEFT JOIN user r\n" +
            "ON (br.idReader = r.idUser)  \n" +
            "LEFT JOIN user l\n" +
            "ON (br.idLibrarian = l.idUser)\n" +
            "LEFT JOIN author a\n" +
            "ON (b.idAuthor = a.idAuthor)  \n" +
            "LEFT JOIN genre g\n" +
            "ON (b.idGenre = g.idGenre) ";

    BookRequestDAOImpl() {
        super(new BookRequestMapper(), "bookrequest");
    }

    @Override
    public long updateStatus(long id, long idLibrarian, long idStatus) {
        return getJdbcTemplate().update(
                "UPDATE " + getTable() + " SET idStatus = ? , idLibrarian = ?" + getWhereId()
                , idStatus
                , idLibrarian
                , id);
    }

    @Override
    public List<BookRequest> getAll() {
        return getJdbcTemplate().query(SELECT_BOOK_REQ, getRowMapper());
    }

    @Override
    public BookRequest getById(long id) {
        try {
            return getJdbcTemplate().queryForObject(SELECT_BOOK_REQ + getWhereId(), new Object[]{id}, getRowMapper());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public long add(BookRequest entity) {
        return getJdbcTemplate().update("INSERT INTO "
                        + getTable() + "(idReader, idBook, idStatus, idLibrarian) VALUES (?,?,?,?)"
                , entity.getReader().getId()
                , entity.getBook().getId()
                , entity.getStatus().getId()
                , entity.getLibrarian().getId());
    }

    @Override
    public long set(long id, BookRequest entity) {
        return getJdbcTemplate().update(
                "UPDATE " + getTable() + " SET idReader = ?, idBook = ?, idStatus = ? , idLibrarian = ? "
                        + getWhereId()
                , entity.getReader().getId()
                , entity.getBook().getId()
                , entity.getStatus().getId()
                , entity.getLibrarian().getId()
                , id);
    }

    @Override
    public long add(long idReader, long idBook, long idStatus, long idLibrarian) {
        return getJdbcTemplate().update("INSERT INTO "
                        + getTable() + "(idReader, idBook, idStatus, idLibrarian) VALUES (?,?,?,?)"
                , idReader
                , idBook
                , idStatus
                , idLibrarian);
    }

    @Override
    public long set(long idBookRequest, long idReader, long idBook, long idStatus, long idLibrarian) {
        return getJdbcTemplate().update(
                "UPDATE " + getTable() + " SET idReader = ?, idBook = ?, idStatus = ? , idLibrarian = ? "
                        + getWhereId()
                , idReader
                , idBook
                , idStatus
                , idLibrarian
                , idBookRequest);
    }
}
