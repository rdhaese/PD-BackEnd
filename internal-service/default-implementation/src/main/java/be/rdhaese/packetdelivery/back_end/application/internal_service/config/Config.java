package be.rdhaese.packetdelivery.back_end.application.internal_service.config;

import be.rdhaese.packetdelivery.back_end.application.internal_service.properties.InternalServiceProperties;
import com.google.maps.GeoApiContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.naming.ldap.LdapContext;

/**
 * Created on 10/04/2016.
 *
 * @author Robin D'Haese
 */
@Configuration
public class Config {

    @Autowired
    private InternalServiceProperties internalServiceProperties;
    @Bean
public GeoApiContext geoApiContext(){
        return new GeoApiContext().setApiKey(internalServiceProperties.getApiKey());
    }

    @Bean
    @ConfigurationProperties(prefix="ldap.contextSource")
    public LdapContextSource ldapContext(){
        return new LdapContextSource();
    }

    @Bean
    public LdapTemplate ldapTemplate(ContextSource contextSource) {
        System.out.println(((LdapContextSource)contextSource).getBaseLdapPathAsString());
        for (String s : ((LdapContextSource)contextSource).getUrls()){
            System.out.println(s);
        }
        return new LdapTemplate(contextSource);
    }
}
