package be.rdhaese.packetdelivery.back_end.application.internal_service.interfaces;

import be.rdhaese.packetdelivery.back_end.application.model.Packet;

/**
 * Created on 30/12/2015.
 *
 * @author Robin D'Haese
 */
public interface AddPacketInternalService {
    String savePacket(Packet packet);
}
