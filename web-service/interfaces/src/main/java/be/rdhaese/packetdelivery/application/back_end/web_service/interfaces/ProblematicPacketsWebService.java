package be.rdhaese.packetdelivery.application.back_end.web_service.interfaces;

import be.rdhaese.packetdelivery.dto.DeliveryAddressDTO;
import be.rdhaese.packetdelivery.dto.PacketDTO;
import be.rdhaese.packetdelivery.dto.RegionDTO;

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
    Boolean returnToSender(String packetId, RegionDTO regionDTO);
    DeliveryAddressDTO getDeliveryAddress(String packetId);
    Boolean saveDeliveryAddress(DeliveryAddressDTO deliveryAddressDTO);
}
