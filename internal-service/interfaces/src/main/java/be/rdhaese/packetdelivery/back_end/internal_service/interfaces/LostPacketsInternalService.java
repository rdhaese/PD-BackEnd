package be.rdhaese.packetdelivery.back_end.internal_service.interfaces;

import be.rdhaese.packetdelivery.back_end.model.Packet;

import java.util.Collection;

/**
 *
 * @author Robin D'Haese
 */
public interface LostPacketsInternalService {
    Collection<Packet> getLostPackets();

    void markAsFound(String packetId);

    void removeFromSystem(String packetId);
}
