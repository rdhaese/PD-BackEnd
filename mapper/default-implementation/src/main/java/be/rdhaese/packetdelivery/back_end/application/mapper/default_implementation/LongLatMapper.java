package be.rdhaese.packetdelivery.back_end.application.mapper.default_implementation;

import be.rdhaese.packetdelivery.back_end.application.mapper.interfaces.AbstractMapper;
import be.rdhaese.packetdelivery.back_end.application.model.LongLat;
import be.rdhaese.packetdelivery.dto.LongLatDTO;
import org.springframework.stereotype.Component;

/**
 * Created on 21/02/2016.
 *
 * @author Robin D'Haese
 */
@Component
public class LongLatMapper extends AbstractMapper<LongLat, LongLatDTO> {

    @Override
    public LongLat mapToBus(LongLatDTO dto) {
        LongLat longLat = new LongLat();
        longLat.setLatitude(dto.getLatitude());
        longLat.setLongitude(dto.getLongitude());
        return longLat;
    }

    @Override
    public LongLatDTO mapToDto(LongLat busObj) {
      LongLatDTO dto = new LongLatDTO();
        dto.setLatitude(busObj.getLatitude());
        dto.setLongitude(busObj.getLongitude());
        return dto;
    }
}
