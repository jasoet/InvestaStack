package com.secondstack.investa.dao.impl;

import com.secondstack.investa.dao.LogUserActivityDAO;
import com.secondstack.investa.domain.LogUserActivity;
import org.springframework.stereotype.Repository;

@Repository("logUserActivityDAOImpl")
public class LogUserActivityDAOImpl extends GenericDAOImpl<LogUserActivity> implements LogUserActivityDAO {
/* --------------------------- Constructor ---------------------------*/

    public LogUserActivityDAOImpl() {
        super(LogUserActivity.class);
    }
}
