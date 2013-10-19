package com.secondstack.investa.dao;

import com.secondstack.investa.domain.Berita;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BeritaDAO extends GenericDAO<Berita> {
    /* -------------------------- Other Methods -------------------------- */
    @Transactional(readOnly = true)
    public List<Berita> getEntriesOrderByTanggalDesc(int first, int max) throws DataAccessException;

    @Transactional(readOnly = true)
    public List<Berita> getAllOrderByTanggalDesc() throws DataAccessException;
}
