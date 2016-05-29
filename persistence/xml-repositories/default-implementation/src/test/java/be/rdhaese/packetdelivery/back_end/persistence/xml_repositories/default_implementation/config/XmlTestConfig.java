package be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.default_implementation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

/**
 *
 * @author Robin D'Haese
 */
@Configuration
@ComponentScan(basePackages = "be.rdhaese.packetdelivery.back_end")
public class XmlTestConfig {

    public static final String APP_STATES_XML = "file:app-states.xml";
    public static final String COMPANY_CONTACT_DETAILS_XML = "file:company-contact-details.xml";
    public static final String OPTIONS_XML = "file:options.xml";

    @Autowired
    private ApplicationContext applicationContext;

    @Bean(name = "appStatesResource")
    public Resource appStatesResource(){
        return applicationContext.getResource(APP_STATES_XML);
    }

    @Bean(name = "companyContactDetailsResource")
    public Resource companyContactDetailsResource(){
        return applicationContext.getResource(COMPANY_CONTACT_DETAILS_XML);
    }

    @Bean(name = "optionsResource")
    public Resource optionsResource(){
        return applicationContext.getResource(OPTIONS_XML);
    }
}
