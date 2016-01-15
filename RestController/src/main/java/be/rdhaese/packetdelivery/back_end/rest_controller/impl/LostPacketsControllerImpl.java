package be.rdhaese.packetdelivery.back_end.rest_controller.impl;

import be.rdhaese.packetdelivery.back_end.mapper.Mapper;
import be.rdhaese.packetdelivery.back_end.mapper.impl.PacketMapper;
import be.rdhaese.packetdelivery.back_end.model.Packet;
import be.rdhaese.packetdelivery.back_end.rest_controller.LostPacketsController;
import be.rdhaese.packetdelivery.back_end.service.LostPacketsService;
import be.rdhaese.packetdelivery.dto.PacketDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created on 15/01/2016.
 *
 * @author Robin D'Haese
 */
@RestController
@RequestMapping("/lost-packets")
public class LostPacketsControllerImpl implements LostPacketsController {

    @Autowired
    private LostPacketsService lostPacketsService;

    @Autowired
    private Mapper<Packet, PacketDTO> packetMapper;

    @Override
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Collection<PacketDTO> getLostPackets() {
            return packetMapper.mapToDto(lostPacketsService.getLostPackets());
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
            lostPacketsService.markAsFound(packetId);
        }
        return true;
    }

    @Override
    @RequestMapping(value = "/remove-from-system", method = RequestMethod.POST)
    public Boolean removeFromSystem(@RequestBody Collection<String> packetIds) {
        for (String packetId : packetIds){
            lostPacketsService.removeFromSystem(packetId);
        }
        return true;
    }
}
