package com.epam.bookLibrary.repository.mappers;

import com.epam.bookLibrary.model.Author;
import com.epam.bookLibrary.model.Book;
import com.epam.bookLibrary.model.Genre;
import com.epam.bookLibrary.model.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Author author = new Author(new Person(
                rs.getString("name")
                , rs.getString("patronymic")
                , rs.getString("surname")));
        author.setId(rs.getInt("idAuthor"));
        Genre genre = new Genre(rs.getString("description"));
        genre.setId(rs.getInt("idGenre"));
        Book book = new Book(
                rs.getString("title")
                , author
                , genre);
        book.setId(rs.getInt("idBook"));
        return book;
    }
}
