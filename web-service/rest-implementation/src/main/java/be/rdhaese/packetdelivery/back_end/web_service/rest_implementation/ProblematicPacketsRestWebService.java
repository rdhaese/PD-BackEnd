package be.rdhaese.packetdelivery.back_end.web_service.rest_implementation;

import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.ProblematicPacketsInternalService;
import be.rdhaese.packetdelivery.back_end.mapper.interfaces.DeliveryAddressMapper;
import be.rdhaese.packetdelivery.back_end.mapper.interfaces.Mapper;
import be.rdhaese.packetdelivery.back_end.model.Address;
import be.rdhaese.packetdelivery.back_end.model.Packet;
import be.rdhaese.packetdelivery.back_end.model.Region;
import be.rdhaese.packetdelivery.back_end.web_service.interfaces.ProblematicPacketsWebService;
import be.rdhaese.packetdelivery.dto.DeliveryAddressDTO;
import be.rdhaese.packetdelivery.dto.PacketDTO;
import be.rdhaese.packetdelivery.dto.RegionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created on 16/01/2016.
 *
 * @author Robin D'Haese
 */
@RestController
@RequestMapping("/problematic-packets")
public class ProblematicPacketsRestWebService implements ProblematicPacketsWebService {

    @Autowired
    private ProblematicPacketsInternalService problematicPacketsInternalService;

    @Autowired
    private Mapper<Packet, PacketDTO> packetMapper;
    @Autowired
    private DeliveryAddressMapper deliveryAddressMapper;
    @Autowired
    private Mapper<Region, RegionDTO> regionMapper;

    @Override
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Collection<PacketDTO> getProblematicPackets() {
        return packetMapper.mapToDto(problematicPacketsInternalService.getProblematicPackets());
    }

    @Override
    @RequestMapping(value = "/for-id/{packetId}", method = RequestMethod.GET)
    public PacketDTO getProblematicPacket(@PathVariable String packetId) {
        return packetMapper.mapToDto(problematicPacketsInternalService.getProblematicPacket(packetId));
    }

    @Override
    @RequestMapping(value = "/re-send", method = RequestMethod.POST)
    public Boolean reSend(@RequestBody String packetId) {
        problematicPacketsInternalService.reSend(packetId);
        return true;
    }

    @Override
    @RequestMapping(value = "/return-to-sender/{packetId}", method = RequestMethod.POST)
    public Boolean returnToSender(@PathVariable String packetId, @RequestBody RegionDTO regionDTO) {
        problematicPacketsInternalService.returnToSender(packetId, regionMapper.mapToBus(regionDTO));
        return true;
    }

    @Override
    @RequestMapping(value = "/delivery-address/{packetId}", method = RequestMethod.GET)
    public DeliveryAddressDTO getDeliveryAddress(@PathVariable String packetId) {
        return deliveryAddressMapper.mapToDto(
                problematicPacketsInternalService.getProblematicPacketAddress(packetId),
                problematicPacketsInternalService.getProblematicPacketRegion(packetId),
                packetId);
    }


    @Override
    @RequestMapping(value = "/save-delivery-address", method = RequestMethod.POST)
    public Boolean saveDeliveryAddress(@RequestBody DeliveryAddressDTO deliveryAddressDTO) {
        Object[] objects = deliveryAddressMapper.mapToBus(deliveryAddressDTO);
        String packetId = (String) objects[0];
        Address address = (Address) objects[1];
        Region region = (Region) objects[2];
        problematicPacketsInternalService.saveDeliveryAddress(packetId, address, region);
        return true;
    }
}
