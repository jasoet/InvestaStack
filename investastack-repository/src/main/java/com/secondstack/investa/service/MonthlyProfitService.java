package com.secondstack.investa.service;


import com.secondstack.investa.dao.ProfitBlokirDAO;
import com.secondstack.investa.dao.ProfitDAO;
import com.secondstack.investa.domain.enums.Month;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service("monthlyProfitService")
public class MonthlyProfitService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ProfitDAO profitDAO;
    @Autowired
    private ProfitBlokirDAO profitBlokirDAO;


    public void process() {
        Calendar cal = Calendar.getInstance();
        Month month = Month.valueOf(cal.get(Calendar.MONTH));
        int year = cal.get(Calendar.YEAR);

        profitDAO.monthlyProfit(month, year);
        profitBlokirDAO.monthlyProfit(month, year);
        logger.info("===================================== monthlyProfit " + month + " " + year + " =====================================");
    }
}
