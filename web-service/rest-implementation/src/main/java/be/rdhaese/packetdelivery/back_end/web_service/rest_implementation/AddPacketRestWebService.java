package be.rdhaese.packetdelivery.back_end.web_service.rest_implementation;



import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.AddPacketInternalService;
import be.rdhaese.packetdelivery.back_end.mapper.interfaces.Mapper;
import be.rdhaese.packetdelivery.back_end.web_service.interfaces.AddPacketWebService;
import be.rdhaese.packetdelivery.back_end.model.Packet;
import be.rdhaese.packetdelivery.dto.PacketDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 10/12/2015.
 *
 * @author Robin D'Haese
 */
@RestController
@RequestMapping("/packet")
public class AddPacketRestWebService implements AddPacketWebService {

    @Autowired
    private AddPacketInternalService addPacketInternalService;
    @Autowired
    private Mapper<Packet, PacketDTO> packetMapper;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPacket(@RequestBody PacketDTO packetDTO) throws Exception {
        return addPacketInternalService.savePacket(packetMapper.mapToBus(packetDTO));
    }
}
