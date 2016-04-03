package be.rdhaese.packetdelivery.back_end.application.internal_service.default_implementation;

import be.rdhaese.packetdelivery.back_end.application.internal_service.interfaces.LongLatInternalService;
import be.rdhaese.packetdelivery.back_end.application.internal_service.properties.LongLatServiceProperties;
import be.rdhaese.packetdelivery.back_end.application.model.Address;
import be.rdhaese.packetdelivery.back_end.application.model.LongLat;
;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 21/02/2016.
 *
 * @author Robin D'Haese
 */
@Service
public class LongLatInternalServiceImpl implements LongLatInternalService {

    @Autowired
    private LongLatServiceProperties longLatServiceProperties;

    @Override
    public LongLat getForAddress(Address address) throws Exception {
        //TODO log maps api requests in seperate file
        String addressString = mapToMapsSearchString(address);
        GeoApiContext context = new GeoApiContext().setApiKey(longLatServiceProperties.getApiKey());
        GeocodingResult[] results =  GeocodingApi.geocode(context,
                addressString).await();
        LongLat longLat;
        if (results.length < 1){
            //Default
            longLat = new LongLat(longLatServiceProperties.getDefaultLongitude(), longLatServiceProperties.getDefaultLatitude());
        } else {
            //returned by google
            longLat = new LongLat(results[0].geometry.location.lng, results[0].geometry.location.lat);
        }
        return longLat;
    }

    private String mapToMapsSearchString(Address address) {
        return String.format("%s %s, %s %s", address.getStreet(), address.getNumber(), address.getPostalCode(), address.getCity());
    }
}
