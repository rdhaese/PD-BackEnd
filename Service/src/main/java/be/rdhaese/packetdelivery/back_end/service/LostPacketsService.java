package be.rdhaese.packetdelivery.back_end.service;

import be.rdhaese.packetdelivery.back_end.model.Packet;

import java.util.Collection;
import java.util.List;

/**
 * Created on 15/01/2016.
 *
 * @author Robin D'Haese
 */
public interface LostPacketsService {
    Collection<Packet> getLostPackets();

    void markAsFound(String packetId);

    void removeFromSystem(String packetId);
}
