package com.secondstack.investa.dao.impl;

import com.secondstack.investa.dao.LogAdminActivityDAO;
import com.secondstack.investa.domain.LogAdminActivity;
import org.springframework.stereotype.Repository;

@Repository("logAdminActivityDAOImpl")
public class LogAdminActivityDAOImpl extends GenericDAOImpl<LogAdminActivity> implements LogAdminActivityDAO {
/* --------------------------- Constructor ---------------------------*/

    public LogAdminActivityDAOImpl() {
        super(LogAdminActivity.class);
    }
}
