package com.epam.bookLibrary.service.impl;

import com.epam.bookLibrary.repository.StatusDAO;
import com.epam.bookLibrary.model.Status;
import com.epam.bookLibrary.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusDAO statusDAO;

    @Override
    public List<Status> getStatuses() {
        return statusDAO.getAll();
    }
}
