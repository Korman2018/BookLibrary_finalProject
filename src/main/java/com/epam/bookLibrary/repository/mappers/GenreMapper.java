package com.epam.bookLibrary.repository.mappers;

import com.epam.bookLibrary.model.Genre;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreMapper implements RowMapper<Genre> {
    @Override
    public Genre mapRow(ResultSet rs, int i) throws SQLException {
        Genre genre = new Genre(rs.getString("description"));
        genre.setId(rs.getInt("idGenre"));
        return genre;
    }
}
