package be.rdhaese.packetdelivery.back_end.rest_controller.impl;

import be.rdhaese.packetdelivery.back_end.mapper.Mapper;
import be.rdhaese.packetdelivery.back_end.model.Packet;
import be.rdhaese.packetdelivery.back_end.rest_controller.ProblematicPacketsController;
import be.rdhaese.packetdelivery.back_end.service.ProblematicPacketsService;
import be.rdhaese.packetdelivery.dto.PacketDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;

/**
 * Created on 16/01/2016.
 *
 * @author Robin D'Haese
 */
@Controller
@RequestMapping("problematic-packets")
public class ProblematicPacketsControllerImpl implements ProblematicPacketsController {

    @Autowired
    private ProblematicPacketsService problematicPacketsService;

    @Autowired
    private Mapper<Packet, PacketDTO> packetMapper;

    @Override
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Collection<PacketDTO> getProblematicPackets() {
        return packetMapper.mapToDto(problematicPacketsService.getProblematicPackets());
    }
}
