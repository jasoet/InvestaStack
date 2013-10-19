package com.secondstack.investa.service;


import com.secondstack.investa.dao.StaffDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AdminSecurityService implements UserDetailsService {
    /*------------------------------ Fields ------------------------------*/
    @Autowired
    private StaffDAO staffDAO;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException, DataAccessException {
        try {
            UserDetails o = staffDAO.findByUsername(s);
            if (o != null) {
                return o;
            } else {
                throw new UsernameNotFoundException("User Not Found");
            }
        } catch (Exception x) {
            throw new UsernameNotFoundException(x.getMessage());
        }
    }
}
