package com.epam.bookLibrary.repository;

import com.epam.bookLibrary.model.abstracts.AbstractEntity;

import java.util.List;

public interface BasicDAO<T extends AbstractEntity> {

    List<T> getAll();

    T getById(long id);

    long add(T entity);

    long set(long id, T entity);

    long delete(long id);
}
