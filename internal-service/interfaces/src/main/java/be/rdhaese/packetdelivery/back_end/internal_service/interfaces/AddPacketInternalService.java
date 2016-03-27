package be.rdhaese.packetdelivery.back_end.internal_service.interfaces;

import be.rdhaese.packetdelivery.back_end.model.Packet;

/**
 * Created on 30/12/2015.
 *
 * @author Robin D'Haese
 */
public interface AddPacketInternalService {
    String savePacket(Packet packet);
}
