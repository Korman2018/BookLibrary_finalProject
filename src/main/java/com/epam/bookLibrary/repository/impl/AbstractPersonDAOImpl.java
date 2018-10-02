package com.epam.bookLibrary.repository.impl;

import com.epam.bookLibrary.repository.BasicPersonDAO;
import com.epam.bookLibrary.model.abstracts.AbstractPersonContainer;
import com.epam.bookLibrary.model.Person;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public abstract class AbstractPersonDAOImpl<T extends AbstractPersonContainer> extends AbstractBasicDAOImpl<T> implements BasicPersonDAO<T> {

    AbstractPersonDAOImpl(RowMapper<T> rowMapper, String table) {
        super(rowMapper, table);
    }

    @Override
    public long add(T entity) {
        Person p = entity.getPerson();
        return getJdbcTemplate().update("INSERT INTO "
                        + getTable() + "(name, patronymic, surname) VALUES"
                        + "(?,?,?)"
                , p.getFirstName()
                , p.getPatronymic()
                , p.getSurname());
    }

    @Override
    public long set(long id, T entity) {
        Person p = entity.getPerson();
        return getJdbcTemplate().update(
                "UPDATE "
                        + getTable()
                        + " SET name = ?, patronymic = ?, surname = ?"
                        + getWhereId()
                , p.getFirstName()
                , p.getPatronymic()
                , p.getSurname()
                , id);
    }

    @Override
    public List<T> getByShortName(String name, String surname) {
        return getJdbcTemplate().query(getSelectFrom() + " WHERE name LIKE %?% AND surname LIKE %?%"
                , new Object[]{name, surname}, getRowMapper());
    }

    public List<T> getByPerson(Person person) {
        return getJdbcTemplate().query(getSelectFrom() + " WHERE name LIKE %?% AND patronymic LIKE %?% AND surname LIKE %?%"
                , new Object[]{
                        person.getFirstName()
                        , person.getPatronymic()
                        , person.getSurname()}
                , getRowMapper());
    }
}
