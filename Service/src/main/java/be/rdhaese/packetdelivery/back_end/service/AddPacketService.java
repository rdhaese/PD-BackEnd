package be.rdhaese.packetdelivery.back_end.service;

import be.rdhaese.packetdelivery.back_end.model.Packet;

/**
 * Created on 30/12/2015.
 *
 * @author Robin D'Haese
 */
public interface AddPacketService {
    String savePacket(Packet packet);
}
