package com.chern.libraryapp.service.util;

import com.chern.libraryapp.model.ReaderMessageInfo;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.stringtemplate.v4.ST;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ReturnMailer extends Mailer {

    private static Logger log = Logger.getLogger(DueMailer.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        readersToMailDueDate = readerService.getReadersToMailReturnDate();
        ST subject;
        ST text;
        try {
            for (ReaderMessageInfo info : readersToMailDueDate) {
                subject = new ST("Hello, <firstName> <secondName>! You forgot to return a book!");
                text = new ST("The lease period of '<title>' book with ISBN <isbn> is over one day ago.\n" +
                        "We ask you to return this book in the near future! Thanks for your attention!\n" +
                        "You can connect with at this address: lib.app.chern@gmail.com");
                message = new MimeMessage(session);
                message.setFrom(new InternetAddress(SENDER_EMAIL_ADDRESS));
//                clearMessageTextTemplate(subject, text);
                subject.add("firstName", info.getFirstName());
                subject.add("secondName", info.getLastName());
                text.add("title", info.getTitle());
                text.add("isbn", info.getIsbn());
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(info.getEmail()));
                message.setSubject(subject.render());
                message.setText(text.render());
                Transport.send(message);
                log.info("Send email to " + info.getEmail() + " about book " + info.getIsbn());
            }
        } catch (MessagingException e) {
            log.error(e.getMessage());
        }


    }
}

