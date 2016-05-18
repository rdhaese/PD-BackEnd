package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation;

import be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.properties.InternalServiceProperties;
import be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.util.AddressToGoogleApiStringConverter;
import be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.util.GeoCoder;
import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.LongLatInternalService;
import be.rdhaese.packetdelivery.back_end.model.Address;
import be.rdhaese.packetdelivery.back_end.model.LongLat;
import com.google.maps.model.Geometry;
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
    private GeoCoder geoCoder;

    @Override
    public LongLat getForAddress(Address address) throws Exception {
        String addressString = addressConverter.convert(address);
        Geometry geometry = geoCoder.getGeometry(addressString);
        LongLat longLat;
        if (geometry == null) {
            //Default
            longLat = new LongLat(internalServiceProperties.getDefaultLongitude(), internalServiceProperties.getDefaultLatitude());
        } else {
            //returned by google
            longLat = new LongLat(geometry.location.lng, geometry.location.lat);
        }
        return longLat;
    }
}
