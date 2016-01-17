package be.rdhaese.packetdelivery.back_end.rest_controller;

import be.rdhaese.packetdelivery.dto.DeliveryAddressDTO;
import be.rdhaese.packetdelivery.dto.PacketDTO;
import be.rdhaese.packetdelivery.dto.RegionDTO;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;

/**
 * Created on 16/01/2016.
 *
 * @author Robin D'Haese
 */
public interface ProblematicPacketsController {
    Collection<PacketDTO> getProblematicPackets();
    PacketDTO getProblematicPacket(String packetId);
    Boolean reSend(String packetId);
    Boolean returnToSend(String packetId);
    DeliveryAddressDTO getDeliveryAddress(String packetId);
    Boolean saveDeliveryAddress(DeliveryAddressDTO deliveryAddressDTO);
}
