package be.rdhaese.packetdelivery.application.back_end.web_service.interfaces;

import be.rdhaese.packetdelivery.dto.PacketDTO;

import java.util.Collection;

/**
 * Created on 15/01/2016.
 *
 * @author Robin D'Haese
 */
public interface LostPacketsWebService {
    Collection<PacketDTO> getLostPackets();
    Boolean markAsFound(Collection<String> packetIds);
    Boolean removeFromSystem(Collection<String> packetIds);
}
