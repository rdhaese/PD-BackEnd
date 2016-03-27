package be.rdhaese.packetdelivery.back_end.web_service.interfaces;

import be.rdhaese.packetdelivery.dto.DeliveryAddressDTO;
import be.rdhaese.packetdelivery.dto.PacketDTO;

import java.util.Collection;

/**
 * Created on 16/01/2016.
 *
 * @author Robin D'Haese
 */
public interface ProblematicPacketsWebService {
    Collection<PacketDTO> getProblematicPackets();
    PacketDTO getProblematicPacket(String packetId);
    Boolean reSend(String packetId);
    Boolean returnToSend(String packetId);
    DeliveryAddressDTO getDeliveryAddress(String packetId);
    Boolean saveDeliveryAddress(DeliveryAddressDTO deliveryAddressDTO);
}
