package be.rdhaese.packetdelivery.back_end.application.web_service.rest_implementation;


import be.rdhaese.packetdelivery.back_end.application.internal_service.interfaces.DeliveryRoundInternalService;
import be.rdhaese.packetdelivery.back_end.application.mapper.interfaces.Mapper;
import be.rdhaese.packetdelivery.back_end.application.model.Packet;
import be.rdhaese.packetdelivery.back_end.application.web_service.interfaces.DeliveryRoundWebService;
import be.rdhaese.packetdelivery.dto.LongLatDTO;
import be.rdhaese.packetdelivery.dto.PacketDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @Override
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public Long newRound(@RequestParam int amountOfPackets) {
        return roundService.createNewRound(amountOfPackets);
    }

    @Override
    @RequestMapping(value = "/packets", method = RequestMethod.GET)
    public List<PacketDTO> getPackets(@RequestParam Long roundId) {
        return (List<PacketDTO>) packetMapper.mapToDto(roundService.getPackets(roundId));
    }

    @Override
    public void markAsLost(Long roundId, PacketDTO packet) {
        //TODO
    }

    @Override
    public void deliver(Long roundId, PacketDTO packetDTO) {
//TODO
    }

    @Override
    public void cannotDeliver(Long roundId, PacketDTO packetDTO, String reason) {
//TODO
    }

    @Override
    public void addRemark(Long roundId, String remark) {
//TODO
    }

    @Override
    public void addLocationUpdate(Long roundId, LongLatDTO longLatDTO) {
//TODO
    }
}
