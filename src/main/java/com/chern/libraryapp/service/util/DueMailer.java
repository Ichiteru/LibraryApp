package com.chern.libraryapp.service.util;

import com.chern.libraryapp.model.ReaderMessageInfo;
import com.chern.libraryapp.service.ReaderService;
import com.chern.libraryapp.service.impl.ReaderServiceImpl;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.stringtemplate.v4.ST;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

public class DueMailer extends Mailer {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("Due fucking works!");
        readersToMailDueDate = readerService.getReadersToMailDueDate();
        ST subject;
        ST text;
        try {
            for (ReaderMessageInfo info : readersToMailDueDate) {
                subject = new ST("Hello, <firstName> <secondName>! Due date is coming!");
                text = new ST("The lease period of '<title>' book with ISBN <isbn> expires in <days> day(-s).\n" +
                        "We ask you to return this book on time! Thanks for your attention!\n" +
                        "You can connect with at this address: lib.app.chern@gmail.com");
                message = new MimeMessage(session);
                message.setFrom(new InternetAddress(SENDER_EMAIL_ADDRESS));
//                clearMessageTextTemplate(subject, text);
                subject.add("firstName", info.getFirstName());
                subject.add("secondName", info.getLastName());
                text.add("title", info.getTitle());
                text.add("isbn", info.getIsbn());
                text.add("days", info.getDaysToDue());
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(info.getEmail()));
                message.setSubject(subject.render());
                message.setText(text.render());
                Transport.send(message);
            }
        } catch (MessagingException e) {
            e.printStackTrace();
            // TODO: 13.11.2021 log
        }


    }

}
