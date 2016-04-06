package be.rdhaese.packetdelivery.back_end.application.web_service.interfaces;

import be.rdhaese.packetdelivery.dto.LongLatDTO;
import be.rdhaese.packetdelivery.dto.PacketDTO;

import java.util.List;

/**
 * Created on 4/01/2016.
 *
 * @author Robin D'Haese
 */
public interface DeliveryRoundWebService {
    Long newRound(int amountOfPackets);
    List<PacketDTO> getPackets(Long roundId);
    void markAsLost(Long roundId, PacketDTO packet);
    void deliver(Long roundId, PacketDTO packetDTO);
    void cannotDeliver(Long roundId, PacketDTO packetDTO, String reason);
    void addRemark(Long roundId, String remark);
    void addLocationUpdate(Long roundId, LongLatDTO longLatDTO);
}
