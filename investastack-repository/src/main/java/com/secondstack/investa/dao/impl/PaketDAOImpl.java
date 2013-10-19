package com.secondstack.investa.dao.impl;

import com.secondstack.investa.dao.PaketDAO;
import com.secondstack.investa.domain.Paket;
import org.springframework.stereotype.Repository;

@Repository("paketDAOImpl")
public class PaketDAOImpl extends GenericDAOImpl<Paket> implements PaketDAO {
/* --------------------------- Constructor ---------------------------*/

    public PaketDAOImpl() {
        super(Paket.class);
    }
}
