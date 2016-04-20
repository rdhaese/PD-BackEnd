package be.rdhaese.packetdelivery.application.back_end.internal_service.interfaces;

import be.rdhaese.packetdelivery.application.back_end.model.Packet;

import java.util.Collection;

/**
 * Created on 15/01/2016.
 *
 * @author Robin D'Haese
 */
public interface LostPacketsInternalService {
    Collection<Packet> getLostPackets();

    void markAsFound(String packetId);

    void removeFromSystem(String packetId);
}
