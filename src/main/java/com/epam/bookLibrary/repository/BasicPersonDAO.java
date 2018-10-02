package com.epam.bookLibrary.repository;

import com.epam.bookLibrary.model.abstracts.AbstractPersonContainer;
import com.epam.bookLibrary.model.Person;

import java.util.List;

public interface BasicPersonDAO<T extends AbstractPersonContainer> extends BasicDAO<T> {

    List<T> getByShortName(String name, String surname);

    List<T> getByPerson(Person person);
}
