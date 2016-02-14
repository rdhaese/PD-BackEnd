package be.rdhaese.packetdelivery.back_end.rest_controller.impl;


import be.rdhaese.packetdelivery.back_end.mapper.Mapper;
import be.rdhaese.packetdelivery.back_end.mapper.impl.RegionMapper;
import be.rdhaese.packetdelivery.back_end.model.Region;
import be.rdhaese.packetdelivery.back_end.rest_controller.GetRegionsController;
import be.rdhaese.packetdelivery.back_end.service.RegionsService;
import be.rdhaese.packetdelivery.back_end.service.impl.RegionsServiceImpl;
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
public class GetRegionsControllerImpl implements GetRegionsController {

    @Autowired
    private RegionsService regionsService;
    @Autowired
    private Mapper<Region,RegionDTO> regionMapper;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Collection<RegionDTO> regions() {
        return regionMapper.mapToDto(regionsService.getRegions());
    }
}
