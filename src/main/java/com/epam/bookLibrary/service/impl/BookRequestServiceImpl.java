package com.epam.bookLibrary.service.impl;

import com.epam.bookLibrary.repository.BookRequestDAO;
import com.epam.bookLibrary.model.BookRequest;
import com.epam.bookLibrary.model.Status;
import com.epam.bookLibrary.service.BookRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookRequestServiceImpl implements BookRequestService {
    @Autowired
    private BookRequestDAO bookRequestDAO;

    @Override
    public long addBookRequest(long idReader, long idBook) {
        Status status = new Status();
        return bookRequestDAO.add(idReader, idBook, status.getId(), idReader);
    }

    @Override
    public List<BookRequest> getBookRequests() {
        return bookRequestDAO.getAll();
    }

    @Override
    public List<BookRequest> getRequested() {
        return bookRequestDAO.getAll()
                .stream()
                .filter(x -> x.getStatus().getId() == 1)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookRequest> getIssued() {
        return bookRequestDAO.getAll()
                .stream()
                .filter(x -> x.getStatus().getId() == 3)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookRequest> getProcessed() {
        return bookRequestDAO.getAll()
                .stream()
                .filter(x -> x.getStatus().getId() == 2)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookRequest> getBookRequestsById(String requestType, long id) {
        return getBookRequests(requestType)
                .stream()
                .filter(x -> x.getReader().getId() == id)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookRequest> getBookRequests(String requestType) {
        switch (requestType) {
            case "requested":
                return getRequested();
            case "processed":
                return getProcessed();
            case "issued":
                return getIssued();
            default:
                return getBookRequests();
        }
    }

    @Override
    public long updateRequestStatus(long id, long idLibrarian, long idStatus) {
        if (idStatus == 4) {
            return bookRequestDAO.delete(id);
        }
        return bookRequestDAO.updateStatus(id, idLibrarian, idStatus);
    }

    @Override
    public boolean isBookRequestExist(long id) {
        return bookRequestDAO.getById(id) != null;
    }
}
