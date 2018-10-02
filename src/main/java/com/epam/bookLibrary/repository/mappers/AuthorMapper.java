package com.epam.bookLibrary.repository.mappers;

import com.epam.bookLibrary.model.Author;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorMapper implements RowMapper<Author> {

    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
        Author author = new Author(PersonMapper.mapRow(rs,rowNum));
        author.setId(rs.getInt("idAuthor"));
        return author;
    }
}
