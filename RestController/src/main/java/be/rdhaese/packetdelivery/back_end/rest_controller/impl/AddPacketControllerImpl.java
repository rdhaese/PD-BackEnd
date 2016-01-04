package be.rdhaese.packetdelivery.back_end.rest_controller.impl;


import be.rdhaese.packetdelivery.back_end.mapper.impl.PacketMapper;
import be.rdhaese.packetdelivery.back_end.rest_controller.AddPacketController;
import be.rdhaese.packetdelivery.back_end.service.AddPacketService;
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
public class AddPacketControllerImpl implements AddPacketController {

    @Autowired
    private AddPacketService addPacketService;
    @Autowired
    private PacketMapper packetMapper;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPacket(@RequestBody PacketDTO packetDTO){
        return addPacketService.savePacket(packetMapper.mapToBus(packetDTO));
    }
}
