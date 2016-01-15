package be.rdhaese.packetdelivery.back_end.rest_controller;

import be.rdhaese.packetdelivery.dto.PacketDTO;

import java.util.Collection;
import java.util.List;

/**
 * Created on 15/01/2016.
 *
 * @author Robin D'Haese
 */
public interface LostPacketsController {
    Collection<PacketDTO> getLostPackets();
    Boolean markAsFound(Collection<String> packetIds);
    Boolean removeFromSystem(Collection<String> packetIds);
}
