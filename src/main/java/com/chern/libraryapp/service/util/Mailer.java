package com.chern.libraryapp.service.util;

import com.chern.libraryapp.model.ReaderMessageInfo;
import com.chern.libraryapp.service.ReaderService;
import com.chern.libraryapp.service.impl.ReaderServiceImpl;
import org.quartz.Job;
import org.stringtemplate.v4.ST;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public abstract class Mailer implements Job {

    protected final String SENDER_EMAIL_ADDRESS = "lib.app.chern@gmail.com";
    protected final String SENDER_EMAIL_PASSWORD = "libACC123";
    protected final String SENDER_HOST = "smtp.gmail.com";
    protected final String SENDER_PORT = "587";
    protected final ReaderService readerService = ReaderServiceImpl.getInstance();
    protected final Properties properties;
    protected final Session session;
    protected List<ReaderMessageInfo> readersToMailDueDate = new ArrayList<>();
    protected MimeMessage message;


    public Mailer() {
        properties = new Properties();
        properties.put("mail.smtp.host", SENDER_HOST);
        properties.put("mail.smtp.port", SENDER_PORT);
        properties.put("mail.from", SENDER_EMAIL_ADDRESS);
        properties.put("mail.smtp.password", SENDER_EMAIL_PASSWORD);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(SENDER_EMAIL_ADDRESS, SENDER_EMAIL_PASSWORD);
                    }
                });
    }

    protected void clearMessageTextTemplate(ST header, ST text){
        header.getAttributes().clear();
        header.getAttributes().forEach((k,v) -> {
        });
        text.getAttributes().clear();
    }
}
