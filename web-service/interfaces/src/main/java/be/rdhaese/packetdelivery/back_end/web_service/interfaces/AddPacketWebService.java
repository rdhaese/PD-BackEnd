package be.rdhaese.packetdelivery.back_end.web_service.interfaces;

import be.rdhaese.packetdelivery.dto.PacketDTO;

/**
 *
 * @author Robin D'Haese
 */
public interface AddPacketWebService {
    String addPacket(PacketDTO packetDTO) throws Exception;
}
