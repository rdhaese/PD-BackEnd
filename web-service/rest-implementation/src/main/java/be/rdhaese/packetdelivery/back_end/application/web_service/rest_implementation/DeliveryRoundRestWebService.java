package be.rdhaese.packetdelivery.back_end.application.web_service.rest_implementation;


import be.rdhaese.packetdelivery.back_end.application.internal_service.interfaces.DeliveryRoundInternalService;
import be.rdhaese.packetdelivery.back_end.application.mapper.interfaces.Mapper;
import be.rdhaese.packetdelivery.back_end.application.model.Packet;
import be.rdhaese.packetdelivery.back_end.application.web_service.interfaces.DeliveryRoundWebService;
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
    @RequestMapping(value = "/mark-as-lost/{roundId}", method = RequestMethod.POST)
    public Boolean markAsLost(@PathVariable("roundId") Long roundId, @RequestBody PacketDTO packet) {
        return roundService.markAsLost(roundId, packetMapper.mapToBus(packet));
    }

    @Override
    @RequestMapping(value = "deliver/{roundId}", method = RequestMethod.POST)
    public Boolean deliver(@PathVariable Long roundId, @RequestBody PacketDTO packetDTO) {
        return roundService.deliver(roundId, packetMapper.mapToBus(packetDTO));
    }

    @Override
    @RequestMapping(value = "/cannot-deliver/{roundId}", method = RequestMethod.POST)
    public Boolean cannotDeliver(@PathVariable Long roundId, @RequestBody PacketDTO packetDTO, @RequestParam String reason) {
        Packet packet = packetMapper.mapToBus(packetDTO);
        return roundService.cannotDeliver(roundId, packet, reason);
    }

    @Override
    @RequestMapping(value = "/add-remark/{roundId}", method = RequestMethod.GET)
    public Boolean addRemark(@PathVariable("roundId") Long roundId, @RequestParam String remark) {
       return roundService.addRemark(roundId, remark);
    }

    @Override
    public Boolean addLocationUpdate(Long roundId, LongLatDTO longLatDTO) {
//TODO
        return null;
    }

    @Override
    @RequestMapping(value = "/end", method = RequestMethod.GET)
    public Boolean endRound(@RequestParam Long roundId) {
        return roundService.endRound(roundId);
    }

    @Override
    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public Boolean startRound(@RequestParam Long roundId) {
        return roundService.startRound(roundId);
    }
}
