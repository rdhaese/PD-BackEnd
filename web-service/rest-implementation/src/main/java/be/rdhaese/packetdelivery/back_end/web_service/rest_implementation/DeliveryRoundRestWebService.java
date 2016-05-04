package be.rdhaese.packetdelivery.back_end.web_service.rest_implementation;


import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.AppInternalService;
import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.DeliveryRoundInternalService;
import be.rdhaese.packetdelivery.back_end.model.Address;
import be.rdhaese.packetdelivery.back_end.model.LongLat;
import be.rdhaese.packetdelivery.back_end.web_service.interfaces.DeliveryRoundWebService;
import be.rdhaese.packetdelivery.back_end.mapper.interfaces.Mapper;
import be.rdhaese.packetdelivery.back_end.model.Packet;
import be.rdhaese.packetdelivery.dto.AddressDTO;
import be.rdhaese.packetdelivery.dto.LongLatDTO;
import be.rdhaese.packetdelivery.dto.PacketDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created on 28/12/2015.
 *
 * @author Robin D'Haese
 */
@RestController
@RequestMapping("/round")
public class DeliveryRoundRestWebService implements DeliveryRoundWebService {

    @Autowired
    private DeliveryRoundInternalService roundService;

    @Autowired
    private Mapper<Packet, PacketDTO> packetMapper;

    @Autowired
    private Mapper<LongLat, LongLatDTO> longLatMapper;

    @Autowired
    private Mapper<Address, AddressDTO> addressMapper;

    @Override
    @RequestMapping(value = "/new/{amountOfPackets}", method = RequestMethod.GET)
    public Long newRound(@PathVariable int amountOfPackets) {
        return roundService.createNewRound(amountOfPackets);
    }

    @Override
    @RequestMapping(value = "/packets/{roundId}", method = RequestMethod.GET)
    public List<PacketDTO> getPackets(@PathVariable Long roundId) throws Exception{
        return (List<PacketDTO>) packetMapper.mapToDto(roundService.getPackets(roundId));
    }

    @Override
    @RequestMapping(value = "/mark-as-lost/{roundId}", method = RequestMethod.POST)
    public Boolean markAsLost(@PathVariable Long roundId, @RequestBody PacketDTO packet) throws Exception {
        return roundService.markAsLost(roundId, packetMapper.mapToBus(packet));
    }

    @Override
    @RequestMapping(value = "deliver/{roundId}", method = RequestMethod.POST)
    public Boolean deliver(@PathVariable Long roundId, @RequestBody PacketDTO packetDTO) throws Exception {
        return roundService.deliver(roundId, packetMapper.mapToBus(packetDTO));
    }

    @Override
    @RequestMapping(value = "/cannot-deliver/{roundId}/{reason}", method = RequestMethod.POST)
    public Boolean cannotDeliver(@PathVariable Long roundId, @PathVariable String reason, @RequestBody PacketDTO packetDTO) throws Exception {
        Packet packet = packetMapper.mapToBus(packetDTO);
        return roundService.cannotDeliver(roundId, packet, reason);
    }

    @Override
    @RequestMapping(value = "/add-remark/{roundId}/{remark}", method = RequestMethod.GET)
    public Boolean addRemark(@PathVariable Long roundId, @PathVariable String remark) {
       return roundService.addRemark(roundId, remark);
    }

    @Override
    @RequestMapping(value = "/add-location-update/{roundId}", method = RequestMethod.POST)
    public Boolean addLocationUpdate(@PathVariable Long roundId, @RequestBody LongLatDTO longLatDTO) {
        return roundService.addLocationUpdate(roundId, longLatMapper.mapToBus(longLatDTO));
    }

    @Override
    @RequestMapping(value = "/end/{roundId}", method = RequestMethod.GET)
    public Boolean endRound(@PathVariable Long roundId) {
        return roundService.endRound(roundId);
    }

    @Override
    @RequestMapping(value = "/start/{roundId}", method = RequestMethod.GET)
    public Boolean startRound(@PathVariable Long roundId) throws Exception {
        return roundService.startRound(roundId);
    }

    @Override
    @RequestMapping(value = "/company-address", method = RequestMethod.GET)
    public AddressDTO getCompanyAddress() throws Exception {
        return addressMapper.mapToDto(roundService.getCompanyAddress());
    }
}
