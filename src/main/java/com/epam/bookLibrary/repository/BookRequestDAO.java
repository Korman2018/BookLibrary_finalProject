package com.epam.bookLibrary.repository;

import com.epam.bookLibrary.model.BookRequest;

public interface BookRequestDAO extends BasicDAO<BookRequest> {
    long updateStatus(long id, long idLibrarian, long idStatus);

    long add(long idReader, long idBook, long idStatus, long idLibrarian);

    long set(long idBookRequest, long idReader, long idBook, long idStatus, long idLibrarian);
}
