package be.rdhaese.packetdelivery.back_end.web_service.rest_implementation;

import be.rdhaese.packetdelivery.back_end.model.LongLat;
import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.TrackerInternalService;
import be.rdhaese.packetdelivery.back_end.mapper.interfaces.Mapper;
import be.rdhaese.packetdelivery.back_end.model.LocationUpdate;
import be.rdhaese.packetdelivery.back_end.model.Remark;
import be.rdhaese.packetdelivery.back_end.web_service.interfaces.TrackerWebService;
import be.rdhaese.packetdelivery.dto.LocationUpdateDTO;
import be.rdhaese.packetdelivery.dto.LongLatDTO;
import be.rdhaese.packetdelivery.dto.RemarkDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created on 19/04/2016.
 *
 * @author Robin D'Haese
 */
@RestController
@RequestMapping("/tracker")
public class TrackerRestWebService implements TrackerWebService{

    @Autowired
    private TrackerInternalService trackerService;

    @Autowired
    private Mapper<LongLat, LongLatDTO> longLatMapper;
    @Autowired
    private Mapper<LocationUpdate, LocationUpdateDTO> locationUpdateMapper;
    @Autowired
    private Mapper<Remark, RemarkDTO> remarkMapper;

    @Override
    @RequestMapping(value = "/company-address", method = RequestMethod.GET)
    public @ResponseBody LongLatDTO getCompanyAddress() {
       return longLatMapper.mapToDto(trackerService.getCompanyAddress());
    }

    @Override
    @RequestMapping(value = "/packet-address/{packetId}", method = RequestMethod.GET)
    public @ResponseBody LongLatDTO getPacketAddress(@PathVariable String packetId) {
        return longLatMapper.mapToDto(trackerService.getPacketAddress(packetId));
    }

    @Override
    @RequestMapping(value = "/location-updates/{packetId}", method = RequestMethod.GET)
    public @ResponseBody Collection<LocationUpdateDTO> getLocationUpdates(@PathVariable String packetId) {
        return locationUpdateMapper.mapToDto(trackerService.getLocationsUpdates(packetId));
    }

    @Override
    @RequestMapping(value = "/remarks/{packetId}", method = RequestMethod.GET)
    public @ResponseBody Collection<RemarkDTO> getRemarks(@PathVariable String packetId) {
        return remarkMapper.mapToDto(trackerService.getRemarks(packetId));
    }
}
