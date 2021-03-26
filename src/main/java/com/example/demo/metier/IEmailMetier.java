package com.example.demo.metier;

import com.example.demo.dao.IEmailDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IEmailMetier implements IEmail {

    @Autowired
    IEmailDAO pan;
    @Override
    public int sendEmailToUser(String emailUser,String name) {
        return pan.sendEmailToUser(emailUser,name);
    }
}
