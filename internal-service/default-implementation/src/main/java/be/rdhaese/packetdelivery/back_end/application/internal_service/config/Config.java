package be.rdhaese.packetdelivery.back_end.application.internal_service.config;

import be.rdhaese.packetdelivery.back_end.application.internal_service.properties.InternalServiceProperties;
import com.google.maps.GeoApiContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
