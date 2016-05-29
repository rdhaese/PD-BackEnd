package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author Robin D'Haese
 */
@Component
public class MailContentLoader {

    @Autowired
    @Qualifier("deliveredMail")
    private Resource deliveredMail;

    @Autowired
    @Qualifier("departedMail")
    private Resource departedMail;

    @Autowired
    @Qualifier("lostMail")
    private Resource lostMail;

    @Autowired
    @Qualifier("notDeliveredMail")
    private Resource notDeliveredMail;

    public String getDeliveredMail() throws IOException {
       return readContect(deliveredMail.getInputStream());
    }

    public String getDepartedMail() throws IOException {
        return readContect(departedMail.getInputStream());
    }

    public String getLostMail() throws IOException {
        return readContect(lostMail.getInputStream());
    }

    public String getNotDeliveredMail() throws IOException {
        return readContect(notDeliveredMail.getInputStream());
    }

    private String readContect(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder out = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
        }
        reader.close();
        return out.toString();
    }
}
