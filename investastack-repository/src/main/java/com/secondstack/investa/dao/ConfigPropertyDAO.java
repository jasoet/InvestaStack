package com.secondstack.investa.dao;

import com.secondstack.investa.domain.ConfigProperty;
import org.springframework.dao.DataAccessException;

import javax.persistence.EntityNotFoundException;

public interface ConfigPropertyDAO extends GenericDAO<ConfigProperty> {
    public ConfigProperty findByKeyz(String keyz) throws DataAccessException,EntityNotFoundException;
}
