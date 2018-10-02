package com.epam.bookLibrary.repository.mappers;

import com.epam.bookLibrary.model.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRequestMapper implements RowMapper<BookRequest> {

    @Override
    public BookRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
        Author author = new Author(new Person(
                rs.getString("authorName")
                , rs.getString("authorPatronymic")
                , rs.getString("authorSurname"))
        );
        author.setId(rs.getInt("idAuthor"));

        Genre genre = new Genre(rs.getString("genreDescription"));
        genre.setId(rs.getInt("idGenre"));

        Book book = new Book(rs.getString("title"), author, genre);
        book.setId(rs.getInt("idBook"));

        User reader = new User(
                new Person(
                        rs.getString("readerName")
                        , rs.getString("readerPatronymic")
                        , rs.getString("readerSurname")
                )
                , rs.getString("readerLogin")
                , rs.getString("readerPassword")
                , rs.getBoolean("readerIsLibrarian")
        );
        reader.setId(rs.getInt("idReader"));

        User librarian = new User(
                new Person(
                        rs.getString("librarianName")
                        , rs.getString("librarianPatronymic")
                        , rs.getString("librarianSurname")
                )
                , rs.getString("librarianLogin")
                , rs.getString("librarianPassword")
                , rs.getBoolean("librarianIsLibrarian")
        );
        librarian.setId(rs.getInt("idLibrarian"));

        Status status = new Status(rs.getString("statusDescription"));
        status.setId(rs.getInt("idStatus"));

        BookRequest bookRequest = new BookRequest(reader, book);
        bookRequest.setId(rs.getInt("idBookRequest"));
        bookRequest.setLibrarian(librarian);
        bookRequest.setStatus(status);
        return bookRequest;
    }
}
