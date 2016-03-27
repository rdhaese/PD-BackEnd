package be.rdhaese.packetdelivery.back_end.web_service.rest_implementation;


import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.RegionsInternalService;
import be.rdhaese.packetdelivery.back_end.mapper.interfaces.Mapper;
import be.rdhaese.packetdelivery.back_end.model.Region;
import be.rdhaese.packetdelivery.back_end.web_service.interfaces.GetRegionsWebService;
import be.rdhaese.packetdelivery.dto.RegionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Created on 25/12/2015.
 *
 * @author Robin D'Haese
 */
@RestController
@RequestMapping("/regions")
public class GetRegionsRestWebService implements GetRegionsWebService {

    @Autowired
    private RegionsInternalService regionsInternalService;
    @Autowired
    private Mapper<Region,RegionDTO> regionMapper;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Collection<RegionDTO> regions() {
        return regionMapper.mapToDto(regionsInternalService.getRegions());
    }
}
