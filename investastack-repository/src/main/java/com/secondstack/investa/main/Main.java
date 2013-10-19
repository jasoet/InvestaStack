package com.secondstack.investa.main;

import com.secondstack.investa.dao.BeritaDAO;
import com.secondstack.investa.domain.Berita;
import com.secondstack.investa.util.provider.InitialDataProvider;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by Deny Prasetyo,S.T
 * Java(Script) and Rails Developer
 * Software Engineer | PT. NetXcel Systems Indonesia
 * jasoet87@gmail.com
 * dprasetyo@netxcel.com.sg | www.netxcel.com.sg
 * <p/>
 * http://github.com/jasoet
 * http://bitbucket.com/jasoet
 *
 * @jasoet
 */

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:META-INF/spring/**/applicationContext*.xml");
        applicationContext.getEnvironment().setActiveProfiles("repository");
        applicationContext.refresh();

        BeritaDAO beritaDAO = applicationContext.getBean("beritaDAOImpl",BeritaDAO.class);
        InitialDataProvider initialDataProvider = applicationContext.getBean(InitialDataProvider.class);

        long now = System.currentTimeMillis();
        System.out.println("Test Start " + now);

        initialDataProvider.provideInitData();
        List<Berita> beritaList = beritaDAO.getAllOrderByTanggalDesc();

        System.out.println(" TEST Finished for " + (System.currentTimeMillis() - now));

        Assert.notNull(beritaList);
    }
}
