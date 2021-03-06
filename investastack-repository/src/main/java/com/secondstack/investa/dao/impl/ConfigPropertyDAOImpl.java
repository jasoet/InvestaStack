package com.secondstack.investa.dao.impl;

import com.secondstack.investa.dao.ConfigPropertyDAO;
import com.secondstack.investa.domain.ConfigProperty;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;

@Repository("configPropertyDAOImpl")
public class ConfigPropertyDAOImpl extends GenericDAOImpl<ConfigProperty> implements ConfigPropertyDAO {
/* --------------------------- Constructor ---------------------------*/

    public ConfigPropertyDAOImpl() {
        super(ConfigProperty.class);
    }

    @Override
    public ConfigProperty findByKeyz(String keyz) throws DataAccessException,EntityNotFoundException {
        return (ConfigProperty) this.entityManager.createQuery("SELECT o FROM ConfigProperty o WHERE lower(o.keyz)=:keyz")
                .setParameter("keyz", keyz.toLowerCase())
                .getSingleResult();
    }
}
