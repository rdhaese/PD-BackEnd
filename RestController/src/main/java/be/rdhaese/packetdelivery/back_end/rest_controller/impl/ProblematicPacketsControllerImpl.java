package be.rdhaese.packetdelivery.back_end.rest_controller.impl;

import be.rdhaese.packetdelivery.back_end.mapper.Mapper;
import be.rdhaese.packetdelivery.back_end.model.Packet;
import be.rdhaese.packetdelivery.back_end.rest_controller.ProblematicPacketsController;
import be.rdhaese.packetdelivery.back_end.service.ProblematicPacketsService;
import be.rdhaese.packetdelivery.dto.PacketDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created on 16/01/2016.
 *
 * @author Robin D'Haese
 */
@RestController
@RequestMapping("/problematic-packets")
public class ProblematicPacketsControllerImpl implements ProblematicPacketsController {

    @Autowired
    private ProblematicPacketsService problematicPacketsService;

    @Autowired
    private Mapper<Packet, PacketDTO> packetMapper;

    @Override
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Collection<PacketDTO> getProblematicPackets() {
        //return packetMapper.mapToDto(problematicPacketsService.getProblematicPackets());


                PacketDTO dto = new PacketDTO();
        dto.setPacketId("TE-TE-21012015-12345");
        dto.setStatusChangedOn(new Date());
        dto.setClientName("bfdsdf");
        dto.setClientPhone("osdfodfso");
        dto.setClientEmail("esfposdfo");
        dto.setDeliveryName("sdofisdofs");
        dto.setDeliveryPhone("eosfposdfo");
        dto.setDeliveryEmail("siofjiosdfsdf");

        PacketDTO dto1 = new PacketDTO();
        dto1.setPacketId("T0-T0-21072015-84968");
        dto1.setStatusChangedOn(new Date());
        dto1.setClientName("bfdsdfqsqdfsdf");
        dto1.setClientPhone("osdfosdfqsdfdfso");
        dto1.setClientEmail("esqsdffposdfo");
        dto1.setDeliveryName("sdofisqsdfdofs");
        dto1.setDeliveryPhone("eosfpsdfqosdfo");
        dto1.setDeliveryEmail("siofjioqsdffsdfsdf");

        List<PacketDTO> dtos = new ArrayList<>();
        dtos.add(dto);
        dtos.add(dto1);
        return dtos;
    }

    @Override
    @RequestMapping(value = "/for-id", method = RequestMethod.GET)
    public PacketDTO getProblematicPacket( String packetId) {
        //return packetMapper.mapToDto(problematicPacketsService.getProblematicPacket(packetId));

        PacketDTO dto = new PacketDTO();
        dto.setPacketId("TE-TE-21012015-12345");
        dto.setStatusChangedOn(new Date());
        dto.setClientName("bfdsdf");
        dto.setClientPhone("osdfodfso");
        dto.setClientEmail("esfposdfo");
        dto.setDeliveryName("sdofisdofs");
        dto.setDeliveryPhone("eosfposdfo");
        dto.setDeliveryEmail("siofjiosdfsdf");
        return dto;
    }

    @Override
    public Boolean reSend(String packetId) {
        problematicPacketsService.reSend(packetId);
        return true;
    }

    @Override
    public Boolean returnToSend(String packetId) {
        problematicPacketsService.returnToSender(packetId);
        return true;
    }
}