package be.rdhaese.packetdelivery.back_end.application.internal_service.interfaces;

import be.rdhaese.packetdelivery.back_end.application.model.Region;
import be.rdhaese.packetdelivery.back_end.application.model.Address;
import be.rdhaese.packetdelivery.back_end.application.model.Packet;

import java.util.Collection;

/**
 * Created on 16/01/2016.
 *
 * @author Robin D'Haese
 */
public interface ProblematicPacketsInternalService {
     Collection<Packet> getProblematicPackets();

     Packet getProblematicPacket(String packetId);

     void reSend(String packetId);

     void returnToSender(String packetId);

     Address getProblematicPacketAddress(String packetId);

     Region getProblematicPacketRegion(String packetId);

     void saveDeliveryAddress(String packetId, Address address, Region region);
}
