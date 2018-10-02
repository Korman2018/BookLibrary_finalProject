package com.epam.bookLibrary.exception;

public class AuthorIsAssignedToBookException extends RuntimeException {
    public AuthorIsAssignedToBookException(String author) {
        super("Ошибка! Есть книга c автором: " + author + ". Сначала удалите данную книгу.");
    }
}
