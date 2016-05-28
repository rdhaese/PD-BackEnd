package be.rdhaese.packetdelivery.back_end.web_service.rest_implementation;

import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.LostPacketsInternalService;
import be.rdhaese.packetdelivery.back_end.mapper.interfaces.Mapper;
import be.rdhaese.packetdelivery.back_end.model.Packet;
import be.rdhaese.packetdelivery.back_end.web_service.interfaces.LostPacketsWebService;
import be.rdhaese.packetdelivery.dto.PacketDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 *
 * @author Robin D'Haese
 */
@RestController
@RequestMapping("/lost-packets")
public class LostPacketsRestWebService implements LostPacketsWebService {

    @Autowired
    private LostPacketsInternalService lostPacketsInternalService;

    @Autowired
    private Mapper<Packet, PacketDTO> packetMapper;

    @Override
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Collection<PacketDTO> getLostPackets() {
        return packetMapper.mapToDto(lostPacketsInternalService.getLostPackets());
    }

    @Override
    @RequestMapping(value = "/mark-as-found", method = RequestMethod.POST)
    public Boolean markAsFound(@RequestBody Collection<String> packetIds) {
        for (String packetId : packetIds) {
            lostPacketsInternalService.markAsFound(packetId);
        }
        return true;
    }

    @Override
    @RequestMapping(value = "/remove-from-system", method = RequestMethod.POST)
    public Boolean removeFromSystem(@RequestBody Collection<String> packetIds) {
        for (String packetId : packetIds) {
            lostPacketsInternalService.removeFromSystem(packetId);
        }
        return true;
    }
}
