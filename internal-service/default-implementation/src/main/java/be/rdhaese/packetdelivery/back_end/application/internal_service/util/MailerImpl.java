package be.rdhaese.packetdelivery.back_end.application.internal_service.util;

import be.rdhaese.packetdelivery.back_end.application.internal_service.properties.InternalServiceProperties;
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
                    MimeMessageHelper helper = new MimeMessageHelper(mail, true);
                    helper.setTo(toAddress);
                    helper.setReplyTo(properties.getReplyToAddress());
                    helper.setFrom(properties.getFromAddress());
                    helper.setSubject(subject);
                    helper.setText(message);
                } catch (MessagingException e) {
                    e.printStackTrace(); //TODO handle this
                } finally {}
                mailSender.send(mail);
            }}).start();
    }
}
