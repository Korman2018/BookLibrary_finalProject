package com.epam.bookLibrary.model;

import com.epam.bookLibrary.model.abstracts.AbstractPersonContainer;

import java.io.Serializable;

public class Author extends AbstractPersonContainer implements Serializable {

    public Author() {
        super();
        // id = -1 unknown Author
        setId(-1);
    }

    public Author(Person person) {
        super(person);
    }

    @Override
    public String toString() {
        return getPerson().toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Author) {
            Author author = (Author) obj;
            return getPerson().equals(author.getPerson());
        }
        return false;
    }
}
