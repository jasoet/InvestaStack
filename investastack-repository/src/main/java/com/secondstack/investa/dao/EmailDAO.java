package com.secondstack.investa.dao;

import com.secondstack.investa.domain.Email;
import org.springframework.dao.DataAccessException;

public interface EmailDAO extends GenericDAO<Email> {
    public Email getFirstPriorityEmail() throws DataAccessException;
}
