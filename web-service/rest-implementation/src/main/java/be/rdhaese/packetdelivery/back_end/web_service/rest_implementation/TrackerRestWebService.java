package be.rdhaese.packetdelivery.back_end.web_service.rest_implementation;

import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.TrackerInternalService;
import be.rdhaese.packetdelivery.back_end.mapper.interfaces.Mapper;
import be.rdhaese.packetdelivery.back_end.model.LocationUpdate;
import be.rdhaese.packetdelivery.back_end.model.LongLat;
import be.rdhaese.packetdelivery.back_end.model.Remark;
import be.rdhaese.packetdelivery.back_end.web_service.interfaces.TrackerWebService;
import be.rdhaese.packetdelivery.dto.LocationUpdateDTO;
import be.rdhaese.packetdelivery.dto.LongLatDTO;
import be.rdhaese.packetdelivery.dto.RemarkDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author Robin D'Haese
 */
@RestController
@RequestMapping("/tracker")
public class TrackerRestWebService implements TrackerWebService {

    @Autowired
    private TrackerInternalService trackerService;

    @Autowired
    private Mapper<LongLat, LongLatDTO> longLatMapper;
    @Autowired
    private Mapper<LocationUpdate, LocationUpdateDTO> locationUpdateMapper;
    @Autowired
    private Mapper<Remark, RemarkDTO> remarkMapper;

    @ResponseBody
    @RequestMapping(value = "/company-address", method = RequestMethod.GET)
    @Override
    public LongLatDTO getCompanyAddress() throws Exception {
        return longLatMapper.mapToDto(trackerService.getCompanyAddress());
    }

    @ResponseBody
    @RequestMapping(value = "/packet-address/{packetId}", method = RequestMethod.GET)
    @Override
    public LongLatDTO getPacketAddress(@PathVariable String packetId) throws Exception {
        return longLatMapper.mapToDto(trackerService.getPacketAddress(packetId));
    }

    @ResponseBody
    @RequestMapping(value = "/location-updates/{packetId}", method = RequestMethod.GET)
    @Override
    public Collection<LocationUpdateDTO> getLocationUpdates(@PathVariable String packetId) {
        return locationUpdateMapper.mapToDto(trackerService.getLocationsUpdates(packetId));
    }

    @ResponseBody
    @RequestMapping(value = "/remarks/{packetId}", method = RequestMethod.GET)
    @Override
    public Collection<RemarkDTO> getRemarks(@PathVariable String packetId) {
        return remarkMapper.mapToDto(trackerService.getRemarks(packetId));
    }

    @ResponseBody
    @RequestMapping(value = "/packets-left-before/{packetId}", method = RequestMethod.GET)
    @Override
    public Integer getAmountOfPacketsLeftBefore(@PathVariable String packetId) throws Exception {
        return trackerService.getAmountOfPacketsLeftBefore(packetId);
    }
}
