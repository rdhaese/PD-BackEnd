package be.rdhaese.packetdelivery.back_end.internal_service.interfaces;

import be.rdhaese.packetdelivery.back_end.model.Packet;

/**
 *
 * @author Robin D'Haese
 */
public interface AddPacketInternalService {
    String savePacket(Packet packet) throws Exception;
}
