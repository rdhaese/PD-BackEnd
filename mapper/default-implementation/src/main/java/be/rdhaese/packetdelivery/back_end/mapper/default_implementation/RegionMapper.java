package be.rdhaese.packetdelivery.back_end.mapper.default_implementation;


import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.RegionsInternalService;
import be.rdhaese.packetdelivery.back_end.mapper.interfaces.AbstractMapper;
import be.rdhaese.packetdelivery.back_end.model.Region;
import be.rdhaese.packetdelivery.dto.RegionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Robin D'Haese
 */
@Component
public class RegionMapper extends AbstractMapper<Region, RegionDTO> {

    @Autowired
    private RegionsInternalService regionsInternalService;

    @Override
    public Region mapToBus(RegionDTO dto) {
        return regionsInternalService.getRegionFor(dto.getCode());
    }


    @Override
    public RegionDTO mapToDto(Region busObj) {
        RegionDTO regionDTO = new RegionDTO();
        regionDTO.setNameNl(busObj.getName().getNl());
        regionDTO.setNameFr(busObj.getName().getFr());
        regionDTO.setNameDe(busObj.getName().getDe());
        regionDTO.setNameEn(busObj.getName().getEn());
        regionDTO.setCode(busObj.getRegionCode());
        return regionDTO;
    }
}
