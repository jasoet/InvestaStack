package com.secondstack.investa.service;

import com.secondstack.investa.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: Deny Prasetyo, S.T.
 * Email : jasoet87@gmail.com
 * Date: 4/16/11
 * Time: 11:48 AM
 */
@Service("userCheckerService")
public class UserCheckerService {
    @Autowired
    private UserDAO userDAO;

    public void check() {
        userDAO.findAndRemoveOverLimitActivation();
    }
}
