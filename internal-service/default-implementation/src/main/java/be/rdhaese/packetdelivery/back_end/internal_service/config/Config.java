package be.rdhaese.packetdelivery.back_end.internal_service.config;

import be.rdhaese.packetdelivery.back_end.internal_service.logging.InternalServiceLogger;
import be.rdhaese.packetdelivery.back_end.internal_service.properties.InternalServiceProperties;
import com.google.maps.GeoApiContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created on 10/04/2016.
 *
 * @author Robin D'Haese
 */
@Configuration
@EnableTransactionManagement
public class Config {

    @Autowired
    private InternalServiceProperties internalServiceProperties;

    @Autowired
    public DataSource dataSource;

    @Bean
    public PlatformTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dataSource);
    }

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
        return new LdapTemplate(contextSource);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(11);
    }

    @Bean(name = "internalServiceLogger")
    public Logger internalServiceLogger(){
        return LoggerFactory.getLogger(InternalServiceLogger.class);
    }
}
