package com.epam.bookLibrary.service;

import com.epam.bookLibrary.model.*;

import java.util.List;

public interface BookRequestService {

    long addBookRequest(long idReader, long idBook);

    List<BookRequest> getBookRequests();

    List<BookRequest> getRequested();

    List<BookRequest> getIssued();

    List<BookRequest> getProcessed();

    List<BookRequest> getBookRequestsById(String requestType, long id);

    List<BookRequest> getBookRequests(String requestType);

    long updateRequestStatus(long id, long idLibrarian, long idStatus);

    boolean isBookRequestExist(long id);
}
