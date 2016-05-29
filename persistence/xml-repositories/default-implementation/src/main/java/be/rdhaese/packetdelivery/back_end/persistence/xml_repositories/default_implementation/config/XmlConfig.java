package be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.default_implementation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;

/**
 * Created on 28/05/2016.
 *
 * @author Robin D'Haese
 */
@Configuration
public class XmlConfig {
    public static final String DATA_LOCATION = String.format("%s%spacket-delivery-system%sdata%s",
            System.getProperty("user.home"), File.separator, File.separator, File.separator);
    public static final String APP_STATES_XML = "app-states.xml";
    public static final String COMPANY_CONTACT_DETAILS_XML = "company-contact-details.xml";
    public static final String OPTIONS_XML = "options.xml";
    @Autowired
    private ApplicationContext context;

    @Bean(name = "appStatesResource")
    public Resource appStatesResource(){
        createDataFolderIfNecessary();
        return context.getResource(String.format("file:%s%s", DATA_LOCATION, APP_STATES_XML));
    }

    @Bean(name = "companyContactDetailsResource")
    public Resource companyContactDetailsResource() throws IOException {
        createDataFolderIfNecessary();
        return  context.getResource(String.format("file:%s%s", DATA_LOCATION, COMPANY_CONTACT_DETAILS_XML));
    }

    @Bean(name = "optionsResource")
    public Resource optionsResource(){
        createDataFolderIfNecessary();
        return context.getResource(String.format("file:%s%s", DATA_LOCATION, OPTIONS_XML));
    }

    private void createDataFolderIfNecessary(){
        File dataLocation = new File(DATA_LOCATION);
        if (!dataLocation.exists()){
            dataLocation.mkdirs();
        }
    }
}
