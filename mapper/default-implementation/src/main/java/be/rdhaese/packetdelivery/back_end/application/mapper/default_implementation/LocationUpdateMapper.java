package be.rdhaese.packetdelivery.back_end.application.mapper.default_implementation;

import be.rdhaese.packetdelivery.back_end.application.mapper.interfaces.AbstractMapper;
import be.rdhaese.packetdelivery.back_end.application.model.LocationUpdate;
import be.rdhaese.packetdelivery.back_end.application.model.LongLat;
import be.rdhaese.packetdelivery.dto.LocationUpdateDTO;
import org.springframework.stereotype.Component;

/**
 * Created on 19/04/2016.
 *
 * @author Robin D'Haese
 */
@Component
public class LocationUpdateMapper extends AbstractMapper<LocationUpdate, LocationUpdateDTO> {
    @Override
    public LocationUpdate mapToBus(LocationUpdateDTO dto) {
        if (dto == null){
            return null;
        }
        LongLat longLat= new LongLat();
        longLat.setLatitude(dto.getLatitude());
        longLat.setLongitude(dto.getLongitude());
        LocationUpdate locationUpdate = new LocationUpdate();
        locationUpdate.setLongLat(longLat);
        locationUpdate.setTimeCreated(dto.getTimeCreated());
        return locationUpdate;
    }

    @Override
    public LocationUpdateDTO mapToDto(LocationUpdate busObj) {
       if (busObj == null){
           return null;
       }
        LocationUpdateDTO dto = new LocationUpdateDTO();
        dto.setLatitude(busObj.getLongLat().getLatitude());
        dto.setLongitude(busObj.getLongLat().getLongitude());
        dto.setTimeCreated(busObj.getTimeCreated());
        return dto;
    }
}
