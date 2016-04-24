package be.rdhaese.packetdelivery.back_end.web_service.interfaces;

import be.rdhaese.packetdelivery.dto.PacketDTO;

/**
 * Created on 4/01/2016.
 *
 * @author Robin D'Haese
 */
public interface AddPacketWebService {
    String addPacket(PacketDTO packetDTO);
}
