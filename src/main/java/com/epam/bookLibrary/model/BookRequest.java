package com.epam.bookLibrary.model;

import com.epam.bookLibrary.model.abstracts.AbstractEntity;

import java.io.Serializable;

public class BookRequest extends AbstractEntity implements Serializable {
    private User reader;

    private Book book;

    private Status status;

    private User librarian;

    public BookRequest(User reader, Book book) {
        this.reader = reader;
        this.book = book;
        this.status = new Status();
        // если запрос еще не обработан то библиотекарь(обработчик) = читателю
        this.librarian = reader;
    }

    public User getReader() {
        return reader;
    }

    public void setReader(User reader) {
        this.reader = reader;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getLibrarian() {
        return librarian;
    }

    public void setLibrarian(User librarian) {
        this.librarian = librarian;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(getId()).append(". читатель:").append(getReader()).append(" книга:").append(getBook())
                .append(" статус:").append(getStatus()).append(" библиотекарь:").append(getLibrarian());
        return stringBuilder.toString();
    }
}
