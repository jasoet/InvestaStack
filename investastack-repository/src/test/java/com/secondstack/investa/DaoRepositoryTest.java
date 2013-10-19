package com.secondstack.investa;

import com.secondstack.investa.dao.BeritaDAO;
import com.secondstack.investa.domain.Berita;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:META-INF/spring/**/applicationContext*.xml")
@ActiveProfiles({"repository"})
public class DaoRepositoryTest {

    @Autowired
    private BeritaDAO beritaDAO;

    @Test
    public void insertData() {
        long now = System.currentTimeMillis();
        System.out.println("Test Start " + now);

        List<Berita> beritaList = beritaDAO.getAllOrderByTanggalDesc();

        System.out.println(" TEST Finished for " + (System.currentTimeMillis() - now));

        Assert.notNull(beritaList);
    }
}
