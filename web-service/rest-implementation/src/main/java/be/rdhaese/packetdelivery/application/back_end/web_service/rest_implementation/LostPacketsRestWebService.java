package be.rdhaese.packetdelivery.application.back_end.web_service.rest_implementation;

import be.rdhaese.packetdelivery.application.back_end.internal_service.interfaces.LostPacketsInternalService;
import be.rdhaese.packetdelivery.application.back_end.mapper.interfaces.Mapper;
import be.rdhaese.packetdelivery.application.back_end.model.Packet;
import be.rdhaese.packetdelivery.application.back_end.web_service.interfaces.LostPacketsWebService;
import be.rdhaese.packetdelivery.dto.PacketDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Created on 15/01/2016.
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
//        PacketDTO dto = new PacketDTO();
//        dto.setPacketId("TE-TE-21012015-12345");
//        dto.setStatusChangedOn(new Date());
//        dto.setClientName("bfdsdf");
//        dto.setClientPhone("osdfodfso");
//        dto.setClientEmail("esfposdfo");
//        dto.setDeliveryName("sdofisdofs");
//        dto.setDeliveryPhone("eosfposdfo");
//        dto.setDeliveryEmail("siofjiosdfsdf");
//
//        PacketDTO dto1 = new PacketDTO();
//        dto1.setPacketId("T0-T0-21072015-84968");
//        dto1.setStatusChangedOn(new Date());
//        dto1.setClientName("bfdsdfqsqdfsdf");
//        dto1.setClientPhone("osdfosdfqsdfdfso");
//        dto1.setClientEmail("esqsdffposdfo");
//        dto1.setDeliveryName("sdofisqsdfdofs");
//        dto1.setDeliveryPhone("eosfpsdfqosdfo");
//        dto1.setDeliveryEmail("siofjioqsdffsdfsdf");
//
//        List<PacketDTO> dtos = new ArrayList<>();
//        dtos.add(dto);
//        dtos.add(dto1);
//        return dtos;
    }

    @Override
    @RequestMapping(value = "/mark-as-found", method = RequestMethod.POST)
    public Boolean markAsFound(@RequestBody Collection<String> packetIds) {
        for (String packetId : packetIds){
            lostPacketsInternalService.markAsFound(packetId);
        }
        return true;
    }

    @Override
    @RequestMapping(value = "/remove-from-system", method = RequestMethod.POST)
    public Boolean removeFromSystem(@RequestBody Collection<String> packetIds) {
        for (String packetId : packetIds){
            lostPacketsInternalService.removeFromSystem(packetId);
        }
        return true;
    }
}
