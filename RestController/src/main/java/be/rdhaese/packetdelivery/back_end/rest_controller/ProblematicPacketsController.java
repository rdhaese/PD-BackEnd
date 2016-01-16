package be.rdhaese.packetdelivery.back_end.rest_controller;

import be.rdhaese.packetdelivery.dto.PacketDTO;

import java.util.Collection;

/**
 * Created on 16/01/2016.
 *
 * @author Robin D'Haese
 */
public interface ProblematicPacketsController {
    Collection<PacketDTO> getProblematicPackets();
    PacketDTO getProblematicPacket(String packetId);
    Boolean reSend(String packetId);
    Boolean returnToSend(String packetId);
}
