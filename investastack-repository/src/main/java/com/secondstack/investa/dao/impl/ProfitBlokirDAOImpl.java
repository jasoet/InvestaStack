package com.secondstack.investa.dao.impl;

import com.secondstack.investa.dao.ProfitBlokirDAO;
import com.secondstack.investa.domain.Bank;
import com.secondstack.investa.domain.ProfitBlokir;
import com.secondstack.investa.domain.User;
import com.secondstack.investa.domain.enums.Month;
import com.secondstack.investa.domain.enums.ProfitStatus;
import com.secondstack.investa.domain.enums.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Repository("profitBlokirDAOImpl")
public class ProfitBlokirDAOImpl extends GenericDAOImpl<ProfitBlokir> implements ProfitBlokirDAO {
/* --------------------------- Constructor ---------------------------*/

    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ProfitBlokirDAOImpl() {
        super(ProfitBlokir.class);
    }

    public void monthlyProfit(Month month, int year) throws DataAccessException {

        List<User> listUser = this.entityManager.createQuery("SELECT u FROM User u WHERE u.status = :statusKey ")
                .setParameter("statusKey", UserStatus.BLOCKED)
                .getResultList();

        for (User user : listUser) {
            Calendar fromDatabase = Calendar.getInstance();
            fromDatabase.setTime(user.getTanggalAktivasi());
            int selisihTahun = (year - fromDatabase.get(Calendar.YEAR)) * 12;
            int selisihBulan = (selisihTahun + month.ordinal()) - fromDatabase.get(Calendar.MONTH);

            int tanggalFromDatabase = fromDatabase.get(Calendar.DAY_OF_MONTH);
            if (tanggalFromDatabase >= 21) {
                selisihBulan = selisihBulan - 1;
            }

            if (selisihBulan == 0) {
                int nilaiDeposit = user.getNilaiDeposit();
                float jumlah = 0;

                if (tanggalFromDatabase >= 1 && tanggalFromDatabase <= 5) {
                    jumlah = (nilaiDeposit * 20) / 100;
                } else if (tanggalFromDatabase >= 6 && tanggalFromDatabase <= 10) {
                    jumlah = (nilaiDeposit * 15) / 100;
                } else if
                        (tanggalFromDatabase >= 11 && tanggalFromDatabase <= 20) {
                    jumlah = (nilaiDeposit * 10) / 100;
                } else if
                        (tanggalFromDatabase >= 21) {
                    jumlah = (nilaiDeposit * 20) / 100;
                }

                ProfitBlokir profit = new ProfitBlokir();
                profit.setUser(user);
                profit.setStatus(ProfitStatus.PENDING);
                profit.setBulan(month);
                profit.setTahun(year);
                profit.setKeterangan("Dalam Proses");
                profit.setJumlah((int) jumlah);

                this.entityManager.persist(profit);
            } else if (selisihBulan > 17) {
                user.setStatus(UserStatus.EXPIRED);
                this.entityManager.merge(user);

            } else if (selisihBulan > 0 && selisihBulan < 18) {

                int nilaiDeposit = user.getNilaiDeposit();
                float jumlah = (nilaiDeposit * 20) / 100;
                ProfitBlokir profit = new ProfitBlokir();
                profit.setUser(user);
                profit.setStatus(ProfitStatus.PENDING);
                profit.setBulan(month);
                profit.setTahun(year);
                profit.setKeterangan("Dalam Proses");
                profit.setJumlah((int) jumlah);

                this.entityManager.persist(profit);

                if (selisihBulan == 17) {
                    user.setStatus(UserStatus.EXPIRED);
                    this.entityManager.merge(user);
                }
            }
        }

    }

    public Long countBy(String searchKey, Bank bank, ProfitStatus status, Month month, int year) throws DataAccessException {
        return (Long) this.entityManager.createQuery("SELECT count(o) FROM ProfitBlokir o WHERE o.user.username LIKE :userKey " +
                "AND o.status = :statusKey AND o.bulan = :monthKey AND o.tahun = :yearKey AND o.user.bank =:bank ORDER BY o.bulan DESC")
                .setParameter("userKey", '%' + searchKey + "%")
                .setParameter("statusKey", status)
                .setParameter("monthKey", month)
                .setParameter("yearKey", year)
                .setParameter("bank", bank)
                .getSingleResult();
    }

    public List<ProfitBlokir> findByLimit(String searchKey, Bank bank, ProfitStatus status, Month month, int year, int start, int size) throws DataAccessException {

        return this.entityManager.createQuery("SELECT o FROM ProfitBlokir o WHERE o.user.username LIKE :userKey " +
                "AND o.status = :statusKey AND o.bulan = :monthKey AND o.tahun = :yearKey AND o.user.bank =:bank ORDER BY o.bulan DESC")
                .setParameter("userKey", '%' + searchKey + "%")
                .setParameter("statusKey", status)
                .setParameter("monthKey", month)
                .setParameter("yearKey", year)
                .setParameter("bank", bank)
                .setFirstResult(start)
                .setMaxResults(size)
                .getResultList();
    }

    public List<ProfitBlokir> findByUsername(String username) throws DataAccessException {
        return this.entityManager.createQuery("SELECT o FROM ProfitBlokir o WHERE o.user.username = :username AND o.status = :status ORDER BY o.id DESC")
                .setParameter("username", username)
                .setParameter("status", ProfitStatus.COMPLETED)
                .getResultList();
    }

    public List<ProfitBlokir> findAllByUsername(String username) throws DataAccessException {
        return this.entityManager.createQuery("SELECT o FROM ProfitBlokir o WHERE o.user.username = :username ORDER BY o.id DESC")
                .setParameter("username", username)
                .getResultList();
    }

    public List<ProfitBlokir> findByBank(Bank bank) throws DataAccessException {
        return this.entityManager.createQuery("SELECT o FROM ProfitBlokir o WHERE o.user.bank = :bank AND o.status = :status ORDER BY o.id DESC")
                .setParameter("bank", bank)
                .setParameter("status", ProfitStatus.COMPLETED)
                .getResultList();
    }

    public void editMasal(Bank bank, Month month, int year, ProfitStatus status) throws DataAccessException {
        this.entityManager.clear();

        /* entityManager.createQuery("UPDATE Profit o  SET o.status = :statusKey WHERE o.userOwner.bank=:bank AND o.bulan = :monthKey AND o.tahun = :yearKey ")
        .setParameter("statusKey", status)
        .setParameter("monthKey", month)
        .setParameter("bank", bank)
        .setParameter("yearKey", year)
        .executeUpdate();*/

        entityManager.createNativeQuery("UPDATE profit_blokir o INNER JOIN user_ u ON (o.user_id = u.id) SET o.status =:statusKey WHERE u.bank_id=:bank AND o.bulan =:monthKey AND o.tahun=:yearKey")
                .setParameter("statusKey", status.ordinal())
                .setParameter("monthKey", month.ordinal())
                .setParameter("bank", bank.getId())
                .setParameter("yearKey", year).executeUpdate();
    }

    public Long countByMonth(Bank bank, Month month, int year) throws DataAccessException {
        return (Long) this.entityManager.createQuery("SELECT count(o) FROM ProfitBlokir o WHERE o.bulan = :monthKey AND o.tahun = :yearKey AND o.user.bank=:bank ORDER BY o.bulan DESC")
                .setParameter("monthKey", month)
                .setParameter("yearKey", year)
                .setParameter("bank", bank)
                .getSingleResult();
    }

    public List<ProfitBlokir> findByMonth(Bank bank, Month month, int year, int start, int size) throws DataAccessException {
        return this.entityManager.createQuery("SELECT o FROM ProfitBlokir o WHERE o.bulan = :monthKey AND o.tahun = :yearKey AND o.user.bank=:bank ORDER BY o.bulan DESC")
                .setParameter("monthKey", month)
                .setParameter("yearKey", year)
                .setParameter("bank", bank)
                .setFirstResult(start).setMaxResults(size).getResultList();
    }


    public Long countByMonthRekening(Bank bank, Month month, int year) throws DataAccessException {
        return (Long) this.entityManager.createQuery("SELECT count(o) FROM ProfitBlokir o WHERE o.bulan = :monthKey AND o.tahun = :yearKey AND o.user.bank=:bank GROUP BY o.user.nomorRekening ORDER BY o.bulan DESC")
                .setParameter("monthKey", month)
                .setParameter("yearKey", year)
                .setParameter("bank", bank)
                .getSingleResult();
    }

    public List<Map<String, Object>> findByMonthRekening(Bank bank, Month month, int year, int start, int size) throws DataAccessException {
        return jdbcTemplate.queryForList("SELECT u.nomorRekening,u.namaRekening,SUM(p.jumlah) as jumlah,b.nama as namaBank FROM profit_blokir p INNER JOIN user_ u ON (p.user_id=u.id) INNER JOIN bank b ON (b.id = u.bank_id) WHERE p.bulan=? AND p.tahun=? AND u.bank_id=? GROUP BY u.nomorRekening", month.ordinal(), year, bank.getId());
    }
}
