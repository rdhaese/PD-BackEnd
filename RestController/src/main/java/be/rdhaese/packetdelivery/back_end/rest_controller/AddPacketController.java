package be.rdhaese.packetdelivery.back_end.rest_controller;

import be.rdhaese.project.dto.PacketDTO;
import be.rdhaese.project.mapper.PacketMapper;
import be.rdhaese.project.service.AddPacketService;
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
public class AddPacketController {

    @Autowired
    private AddPacketService addPacketService;
    @Autowired
    private PacketMapper packetMapper;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPacket(@RequestBody PacketDTO packetDTO){
        return addPacketService.savePacket(packetMapper.mapToBus(packetDTO));
    }
}
