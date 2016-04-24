package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation;

import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.LongLatInternalService;
import be.rdhaese.packetdelivery.back_end.internal_service.properties.InternalServiceProperties;
import be.rdhaese.packetdelivery.back_end.internal_service.util.AddressToGoogleApiStringConverter;
import be.rdhaese.packetdelivery.back_end.model.Address;
import be.rdhaese.packetdelivery.back_end.model.LongLat;
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
    private InternalServiceProperties internalServiceProperties;

    @Autowired
    private AddressToGoogleApiStringConverter addressConverter;

    @Autowired
    private GeoApiContext geoApiContext;

    @Override
    public LongLat getForAddress(Address address) throws Exception {
        //TODO log maps api requests in seperate file
        String addressString = addressConverter.convert(address);
        GeocodingResult[] results =  GeocodingApi.geocode(geoApiContext,
                addressString).await();
        LongLat longLat;
        if (results.length < 1){
            //Default
            longLat = new LongLat(internalServiceProperties.getDefaultLongitude(), internalServiceProperties.getDefaultLatitude());
        } else {
            //returned by google
            longLat = new LongLat(results[0].geometry.location.lng, results[0].geometry.location.lat);
        }
        return longLat;
    }
}
