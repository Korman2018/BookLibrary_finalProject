package com.epam.bookLibrary.repository.mappers;

import com.epam.bookLibrary.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException, EmptyResultDataAccessException {
        User user = new User(
                PersonMapper.mapRow(rs, rowNum)
                , rs.getString("login")
                , rs.getString("password")
                , rs.getBoolean("isLibrarian"));
        user.setId(rs.getInt("idUser"));
        return user;
    }
}
