package com.epam.bookLibrary.repository.impl;

import com.epam.bookLibrary.repository.StatusDAO;
import com.epam.bookLibrary.repository.mappers.StatusMapper;
import com.epam.bookLibrary.model.Status;
import org.springframework.stereotype.Repository;

@Repository
public class StatusDAOImpl extends AbstractEnumDAOImpl<Status> implements StatusDAO {
    StatusDAOImpl() {
        super(new StatusMapper(), "status");
    }
}
