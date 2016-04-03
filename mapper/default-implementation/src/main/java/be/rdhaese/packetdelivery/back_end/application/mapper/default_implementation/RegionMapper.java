package be.rdhaese.packetdelivery.back_end.application.mapper.default_implementation;


import be.rdhaese.packetdelivery.back_end.application.internal_service.interfaces.RegionsInternalService;
import be.rdhaese.packetdelivery.back_end.application.mapper.interfaces.AbstractMapper;
import be.rdhaese.packetdelivery.back_end.application.model.Region;
import be.rdhaese.packetdelivery.dto.RegionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created on 25/12/2015.
 *
 * @author Robin D'Haese
 */
@Component
public class RegionMapper extends AbstractMapper<Region, RegionDTO> {
    @Autowired
    private RegionsInternalService regionsInternalService;

    @Override
    public Region mapToBus(RegionDTO dto) {
        if (dto == null){
            return null;
        }
        return regionsInternalService.getRegionFor(dto.getCode());
    }

    @Override
    public RegionDTO mapToDto(Region busObj) {
        if (busObj == null){
            return null;
        }
        RegionDTO regionDTO = new RegionDTO();
        regionDTO.setName(busObj.getName());
        regionDTO.setCode(busObj.getRegionCode());
        return regionDTO;
    }
}
