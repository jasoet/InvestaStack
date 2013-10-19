package com.secondstack.investa.dao.impl;

import com.secondstack.investa.dao.BankDAO;
import com.secondstack.investa.domain.Bank;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;

@Repository("bankDAOImpl")
public class BankDAOImpl extends GenericDAOImpl<Bank> implements BankDAO {
/* --------------------------- Constructor ---------------------------*/

    public BankDAOImpl() {
        super(Bank.class);
    }

    @Override
    public Bank findByNama(String nama) throws DataAccessException,EntityNotFoundException {
        return (Bank) this.entityManager.createQuery("SELECT o FROM Bank o WHERE lower(o.nama)=:nama").setParameter("nama", nama.toLowerCase()).getSingleResult();
    }
}
