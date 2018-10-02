package com.epam.bookLibrary.repository.impl;

import com.epam.bookLibrary.repository.GenreDAO;
import com.epam.bookLibrary.repository.mappers.GenreMapper;
import com.epam.bookLibrary.model.Genre;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GenreDAOImpl extends AbstractEnumDAOImpl<Genre> implements GenreDAO {
    GenreDAOImpl() {
        super(new GenreMapper(), "genre");
    }

    @Override
    public List<Genre> getAll() {
        return getJdbcTemplate().query("SELECT DISTINCT * FROM genre", getRowMapper());
    }
}
