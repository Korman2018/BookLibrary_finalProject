package com.epam.bookLibrary.model;

import com.epam.bookLibrary.model.abstracts.AbstractEnum;

import java.io.Serializable;

public class Genre extends AbstractEnum implements Serializable {
    public Genre(String description) {
        super(description);
    }

    public Genre() {
        super();
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
