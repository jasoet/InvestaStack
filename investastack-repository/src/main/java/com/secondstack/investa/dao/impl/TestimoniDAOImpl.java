package com.secondstack.investa.dao.impl;

import com.secondstack.investa.dao.TestimoniDAO;
import com.secondstack.investa.domain.Testimoni;
import org.springframework.stereotype.Repository;

@Repository("testimoniDAOImpl")
public class TestimoniDAOImpl extends GenericDAOImpl<Testimoni> implements TestimoniDAO {
/* --------------------------- Constructor ---------------------------*/

    public TestimoniDAOImpl() {
        super(Testimoni.class);
    }
}
