package be.rdhaese.packetdelivery.back_end.mapper.default_implementation;

import be.rdhaese.packetdelivery.back_end.mapper.interfaces.AbstractMapper;
import be.rdhaese.packetdelivery.back_end.model.LocationUpdate;
import be.rdhaese.packetdelivery.back_end.model.LongLat;
import be.rdhaese.packetdelivery.dto.LocationUpdateDTO;
import org.springframework.stereotype.Component;

/**
 *
 * @author Robin D'Haese
 */
@Component
public class LocationUpdateMapper extends AbstractMapper<LocationUpdate, LocationUpdateDTO> {
    @Override
    public LocationUpdate mapToBus(LocationUpdateDTO dto) {
        LongLat longLat = new LongLat();
        longLat.setLatitude(dto.getLatitude());
        longLat.setLongitude(dto.getLongitude());
        LocationUpdate locationUpdate = new LocationUpdate();
        locationUpdate.setLongLat(longLat);
        locationUpdate.setTimeCreated(dto.getTimeCreated());
        return locationUpdate;
    }

    @Override
    public LocationUpdateDTO mapToDto(LocationUpdate busObj) {
        LocationUpdateDTO dto = new LocationUpdateDTO();
        dto.setLatitude(busObj.getLongLat().getLatitude());
        dto.setLongitude(busObj.getLongLat().getLongitude());
        dto.setTimeCreated(busObj.getTimeCreated());
        return dto;
    }
}
