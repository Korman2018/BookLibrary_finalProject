package com.epam.bookLibrary.exception;

public class GenreExistException extends RuntimeException {
    public GenreExistException(String genre) {
        super("Ошибка! Жанр с названием: " + genre + " существует!");
    }
}
