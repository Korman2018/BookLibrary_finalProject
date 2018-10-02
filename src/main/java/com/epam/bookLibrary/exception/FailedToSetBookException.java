package com.epam.bookLibrary.exception;

public class FailedToSetBookException extends RuntimeException {
    public FailedToSetBookException() {
        super("Ошибка! Книга уже удалена из базы!");
    }
}
