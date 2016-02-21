package be.rdhaese.packetdelivery.back_end.service.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created on 21/02/2016.
 *
 * @author Robin D'Haese
 */
@Component
@ConfigurationProperties(locations = "classpath:long-lat-service.properties")
public class LongLatServiceProperties {
    private String apiKey;
    private Double defaultLongitude;
    private Double defaultLatitude;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Double getDefaultLongitude() {
        return defaultLongitude;
    }

    public void setDefaultLongitude(Double defaultLongitude) {
        this.defaultLongitude = defaultLongitude;
    }

    public Double getDefaultLatitude() {
        return defaultLatitude;
    }

    public void setDefaultLatitude(Double defaultLatitude) {
        this.defaultLatitude = defaultLatitude;
    }
}
