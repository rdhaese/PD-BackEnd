package be.rdhaese.packetdelivery.back_end.internal_service.util;

import be.rdhaese.packetdelivery.back_end.internal_service.properties.InternalServiceProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created on 13/04/2016.
 *
 * @author Robin D'Haese
 */
@Component
public class MailerImpl implements Mailer{

    private Logger logger = LoggerFactory.getLogger(MailerImpl.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private InternalServiceProperties properties;

    @Override
    public void send(String toAddress, String subject, String message) {
        new Thread(new Runnable() {
            public void run()
            {
                MimeMessage mail = mailSender.createMimeMessage();
                try {
                    mail.setContent(message, "text/html");
                    MimeMessageHelper helper = new MimeMessageHelper(mail, false);
                    helper.setTo(toAddress);
                    helper.setReplyTo(properties.getReplyToAddress());
                    helper.setFrom(properties.getFromAddress());
                    helper.setSubject(subject);
                } catch (MessagingException e) {
                    logger.warn("Could not send email", e);
                }
                mailSender.send(mail);
            }}).start();
    }
}
