package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.util;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.Geometry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created on 9/05/2016.
 *
 * @author Robin D'Haese
 */
@Component
public class GeoCoder {

    @Autowired
    private GeoApiContext geoApiContext;

    public Geometry getGeometry(String addressString) throws Exception {
        GeocodingResult[] results =  GeocodingApi.geocode(geoApiContext,
                addressString).await();
        if (results.length > 0){
            return results[0].geometry;
        }
        return null;
    }
}
