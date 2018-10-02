package com.epam.bookLibrary.exception;

public class FailedToSetGenreException extends RuntimeException {
    public FailedToSetGenreException(String description) {
        super("Ошибка! Жанра с названием: " + description + " нет в базе!");
    }
}
