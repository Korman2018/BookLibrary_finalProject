package com.epam.bookLibrary.model;

import com.epam.bookLibrary.model.abstracts.AbstractEnum;

import java.io.Serializable;

public class Status extends AbstractEnum implements Serializable {
    public Status(String description) {
        super(description);
    }

    // по умолчанию статус = запрос
    public Status() {
        super("запрос");
        setId(1);
    }

    @Override
    public String toString() {
        return getId() + ". " + getDescription();
    }
}
