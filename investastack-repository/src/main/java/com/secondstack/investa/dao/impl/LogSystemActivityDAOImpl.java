package com.secondstack.investa.dao.impl;

import com.secondstack.investa.dao.LogSystemActivityDAO;
import com.secondstack.investa.domain.LogSystemActivity;
import org.springframework.stereotype.Repository;

@Repository("logAutomaticSystemActivityDAOImpl")
public class LogSystemActivityDAOImpl extends GenericDAOImpl<LogSystemActivity> implements LogSystemActivityDAO {
/* --------------------------- Constructor ---------------------------*/

    public LogSystemActivityDAOImpl() {
        super(LogSystemActivity.class);
    }
}
