package be.rdhaese.packetdelivery.back_end.rest_controller;

import be.rdhaese.packetdelivery.dto.PacketDTO;

/**
 * Created on 4/01/2016.
 *
 * @author Robin D'Haese
 */
public interface AddPacketController {
    String addPacket(PacketDTO packetDTO);
}
