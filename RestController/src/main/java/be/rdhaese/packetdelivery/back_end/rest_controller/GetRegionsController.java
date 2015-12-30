package be.rdhaese.packetdelivery.back_end.rest_controller;

import be.rdhaese.project.dto.RegionDTO;
import be.rdhaese.project.mapper.RegionMapper;
import be.rdhaese.project.service.RegionsService;
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
public class GetRegionsController {

    @Autowired
    private RegionsService regionsService;
    @Autowired
    private RegionMapper regionMapper;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Collection<RegionDTO> regions(){
        return regionMapper.mapToDto(regionsService.getRegions());
    }
}
