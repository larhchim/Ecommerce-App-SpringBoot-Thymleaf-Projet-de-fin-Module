package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Random;
@Component
public class IEmailImpl implements IEmailDAO {
    @Autowired
    private JavaMailSender javaMailSender;
    @Override
    public int sendEmailToUser(String emailUser,String name) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(emailUser);

        msg.setSubject("KOULCHI MALL EMAIL VERIFICATION");
        double random = Math.random() * 9999;
        if(random<1000){
            random = random + 1000;
        }
        int verifyCode = (int)random;
        msg.setText("Hello "+name+",\n" +
                "\n" +
                "You registered an account on KOULCHI MALL, before being able to use your account you need to verify that this is your email address by use this PIN:\n" +
                "\n" +
                "Your PIN : "+verifyCode+"\n," +
                "Kind Regards, MALL");

        javaMailSender.send(msg);
        return verifyCode;


    }
}
