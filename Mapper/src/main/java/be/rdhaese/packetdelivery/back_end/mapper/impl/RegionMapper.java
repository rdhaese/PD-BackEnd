package be.rdhaese.packetdelivery.back_end.mapper.impl;


import be.rdhaese.packetdelivery.back_end.mapper.AbstractMapper;
import be.rdhaese.packetdelivery.back_end.model.Region;
import be.rdhaese.packetdelivery.back_end.service.impl.RegionsServiceImpl;
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
    private RegionsServiceImpl regionsService;

    @Override
    public Region mapToBus(RegionDTO dto) {
        if (dto == null){
            return null;
        }
        return regionsService.getRegionFor(dto.getCode());
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
