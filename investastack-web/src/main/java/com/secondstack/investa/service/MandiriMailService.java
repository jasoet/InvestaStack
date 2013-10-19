package com.secondstack.investa.service;

import com.secondstack.investa.dao.EmailDAO;
import com.secondstack.investa.domain.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * User: Deny Prasetyo, S.T.
 * http://www.jasoet.com
 */

@Component
public class MandiriMailService {

    private EmailDAO emailDAO;

    public EmailDAO getEmailDAO() {
        return emailDAO;
    }

    @Autowired
    public void setEmailDAO(EmailDAO emailDAO) {
        this.emailDAO = emailDAO;
    }

    public void sendEmail(String to,String[] bcc, String subject, String text) {

        Email email = new Email();
        email.setTo(to);
        email.setSubject(subject);
        email.setText(text);

        emailDAO.save(email);
    }

}
