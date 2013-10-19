package com.secondstack.investa.util.provider;

import com.secondstack.investa.dao.BankDAO;
import com.secondstack.investa.dao.ConfigPropertyDAO;
import com.secondstack.investa.dao.LogSystemActivityDAO;
import com.secondstack.investa.dao.PaketDAO;
import com.secondstack.investa.dao.RoleDAO;
import com.secondstack.investa.dao.StaffDAO;
import com.secondstack.investa.domain.ConfigProperty;
import com.secondstack.investa.domain.LogSystemActivity;
import com.secondstack.investa.domain.Paket;
import com.secondstack.investa.domain.Role;
import com.secondstack.investa.domain.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class InitialDataProvider {
    /*------------------------------ Fields ------------------------------*/
    @Autowired
    private RoleDAO roleDAO;
    @Autowired
    private BankDAO bankDAO;
    @Autowired
    private StaffDAO staffDAO;
    @Autowired
    private PaketDAO paketDAO;
    @Autowired
    private LogSystemActivityDAO logSystemActivityDAO;
    @Autowired
    @Qualifier("passwordEncoder")
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ConfigPropertyDAO configPropertyDAO;

    public void provideInitData() {
        if (roleDAO != null && staffDAO != null && paketDAO != null && logSystemActivityDAO != null && passwordEncoder != null) {
            if (roleDAO.count() == 0) {
                if (roleDAO.get("ROLE_ADMIN") == null) {
                    roleDAO.save(new Role("ROLE_ADMIN"));
                    logSystemActivityDAO.save(new LogSystemActivity(InitialDataProvider.class.getName(), "Save ROLE_ADMIN for Initial Data", new Date()));
                }

                if (roleDAO.get("ROLE_FINANCE_MANDIRI") == null) {
                    roleDAO.save(new Role("ROLE_FINANCE_MANDIRI"));
                    logSystemActivityDAO.save(new LogSystemActivity(InitialDataProvider.class.getName(), "Save ROLE_FINANCE for Initial Data", new Date()));
                }
                if (roleDAO.get("ROLE_FINANCE_BCA") == null) {
                    roleDAO.save(new Role("ROLE_FINANCE_BCA"));
                    logSystemActivityDAO.save(new LogSystemActivity(InitialDataProvider.class.getName(), "Save ROLE_FINANCE for Initial Data", new Date()));
                }
                if (roleDAO.get("ROLE_FINANCE_BNI") == null) {
                    roleDAO.save(new Role("ROLE_FINANCE_BNI"));
                    logSystemActivityDAO.save(new LogSystemActivity(InitialDataProvider.class.getName(), "Save ROLE_FINANCE for Initial Data", new Date()));
                }
                if (roleDAO.get("ROLE_FINANCE_BRI") == null) {
                    roleDAO.save(new Role("ROLE_FINANCE_BRI"));
                    logSystemActivityDAO.save(new LogSystemActivity(InitialDataProvider.class.getName(), "Save ROLE_FINANCE for Initial Data", new Date()));
                }
                if (roleDAO.get("ROLE_FINANCE_LAIN") == null) {
                    roleDAO.save(new Role("ROLE_FINANCE_LAIN"));
                    logSystemActivityDAO.save(new LogSystemActivity(InitialDataProvider.class.getName(), "Save ROLE_FINANCE for Initial Data", new Date()));
                }

                if (roleDAO.get("ROLE_STAFF") == null) {
                    roleDAO.save(new Role("ROLE_STAFF"));
                    logSystemActivityDAO.save(new LogSystemActivity(InitialDataProvider.class.getName(), "Save ROLE_STAFF for Initial Data", new Date()));
                }

            }
            if (configPropertyDAO.count() == 0) {
                ConfigProperty cf = new ConfigProperty();
                cf.setKeyz("biayaTransfer");
                cf.setText("0");
                configPropertyDAO.save(cf);
            }
//            if (bankDAO.count() == 0) {
//                Bank b = new Bank();
//                b.setId(1l);
//                b.setNama("BCA");
//                bankDAO.save(b);
//
//                b = new Bank();
//                b.setId(2l);
//                b.setNama("BNI");
//                bankDAO.save(b);
//
//                b = new Bank();
//                b.setId(3l);
//                b.setNama("MANDIRI");
//
//                bankDAO.save(b);
//            }

            if (staffDAO.count() == 0) {
                Role roleAdmin = roleDAO.get("ROLE_ADMIN");

                Staff staff = new Staff();
                staff.setEnabled(true);
                staff.setUsername("admin");
                staff.setTelephone("");
                staff.setPassword("admin");

                List<Role> roles = new ArrayList<Role>();
                roles.add(roleAdmin);

                staff.setRoles(roles);

                String salt = new String(Hex.encode(staff.getUsername().getBytes()));
                staff.setSalt(salt);
                staff.setPassword(passwordEncoder.encode(staff.getPassword()));
                staffDAO.save(staff);

                logSystemActivityDAO.save(new LogSystemActivity(InitialDataProvider.class.getName(),
                        "Create Staff with ROLE_ADMIN Role for Initial Data",
                        new Date()));
            }

            if (paketDAO.count() == 0) {
                paketDAO.save(new Paket("Paket A", 300000, true));
                logSystemActivityDAO.save(new LogSystemActivity(InitialDataProvider.class.getName(),
                        "Create Paket, Nama : 'Paket A', Harga : 300000, Enabled: true for Initial Data",
                        new Date()));

                paketDAO.save(new Paket("Paket B", 1000000, true));
                logSystemActivityDAO.save(new LogSystemActivity(InitialDataProvider.class.getName(),
                        "Create Paket, Nama : 'Paket B', Harga : 1000000, Enabled: true for Initial Data",
                        new Date()));

                paketDAO.save(new Paket("Paket C", 5000000, true));
                logSystemActivityDAO.save(new LogSystemActivity(InitialDataProvider.class.getName(),
                        "Create Paket, Nama : 'Paket C', Harga : 5000000, Enabled: true for Initial Data",
                        new Date()));

                paketDAO.save(new Paket("Paket D", 10000000, true));
                logSystemActivityDAO.save(new LogSystemActivity(InitialDataProvider.class.getName(),
                        "Create Paket, Nama : 'Paket D', Harga : 10000000, Enabled: true for Initial Data",
                        new Date()));

                paketDAO.save(new Paket("Paket E", 20000000, true));
                logSystemActivityDAO.save(new LogSystemActivity(InitialDataProvider.class.getName(),
                        "Create Paket, Nama : 'Paket E', Harga : 20000000, Enabled: true for Initial Data",
                        new Date()));

                paketDAO.save(new Paket("Paket F", 30000000, true));
                logSystemActivityDAO.save(new LogSystemActivity(InitialDataProvider.class.getName(),
                        "Create Paket, Nama : 'Paket F', Harga : 30000000, Enabled: true for Initial Data",
                        new Date()));

                paketDAO.save(new Paket("Paket G", 50000000, true));
                logSystemActivityDAO.save(new LogSystemActivity(InitialDataProvider.class.getName(),
                        "Create Paket, Nama : 'Paket G', Harga : 50000000, Enabled: true for Initial Data",
                        new Date()));
            }
        }
    }
}
