package be.rdhaese.packetdelivery.back_end.service;

import be.rdhaese.packetdelivery.back_end.model.Packet;

import java.util.Collection;

/**
 * Created on 16/01/2016.
 *
 * @author Robin D'Haese
 */
public interface ProblematicPacketsService {
     Collection<Packet> getProblematicPackets();

     Packet getProblematicPacket(String packetId);

     void reSend(String packetId);

     void returnToSender(String packetId);
}
