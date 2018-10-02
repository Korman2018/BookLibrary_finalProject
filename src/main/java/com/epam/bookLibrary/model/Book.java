package com.epam.bookLibrary.model;

import com.epam.bookLibrary.model.abstracts.AbstractEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;


public class Book extends AbstractEntity implements Serializable {

    @NotEmpty
    @Size(min = 2)
    private String title;

    private Author author;

    private Genre genre;


    private Book() {
    }

    public Book(String title, Author author, Genre genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public Book(String title, Author author) {
        this(title, author, new Genre());
    }

    public Book(String title) {
        this(title, new Author());
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(getId()).append(". ").append(title).append(" автор:").append(author)
                .append(" жанр:").append(genre);
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Book) {
            Book book = (Book) obj;
            if (book.getId() == getId()) {
                return true;
            }

            if (!(title != null && title.equals(book.title))) {
                return false;
            }
            return getAuthor() == book.getAuthor();
        }
        return false;
    }
}

