package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.util;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 *
 * @author Robin D'Haese
 */
@SuppressWarnings("ConstantConditions") //Files are hardcoded
@Component
public class MailContentLoader {

    private static final File PATH_DELIVERED = new File(MailContentLoader.class.getClassLoader().getResource("mails/delivered.html").getFile());
    private static final File PATH_DEPARTED = new File(MailContentLoader.class.getClassLoader().getResource("mails/departed.html").getFile());
    private static final File PATH_LOST = new File(MailContentLoader.class.getClassLoader().getResource("mails/lost.html").getFile());
    private static final File PATH_NOT_DELIVERED = new File(MailContentLoader.class.getClassLoader().getResource("mails/not_delivered.html").getFile());

    public String getDeliveredMail() throws IOException {
        return new String(Files.readAllBytes(PATH_DELIVERED.toPath()));
    }

    public String getDepartedMail() throws IOException {
        return new String(Files.readAllBytes(PATH_DEPARTED.toPath()));
    }

    public String getLostMail() throws IOException {
        return new String(Files.readAllBytes(PATH_LOST.toPath()));
    }

    public String getNotDeliveredMail() throws IOException {
        return new String(Files.readAllBytes(PATH_NOT_DELIVERED.toPath()));
    }
}
