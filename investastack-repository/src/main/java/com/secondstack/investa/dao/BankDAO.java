package com.secondstack.investa.dao;

import com.secondstack.investa.domain.Bank;
import org.springframework.dao.DataAccessException;

import javax.persistence.EntityNotFoundException;

public interface BankDAO extends GenericDAO<Bank> {
    public Bank findByNama(String nama) throws DataAccessException,EntityNotFoundException;
}
