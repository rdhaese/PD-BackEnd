package be.rdhaese.packetdelivery.back_end.web_service.interfaces;

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
    List<PacketDTO> getPackets(Long roundId) throws Exception;
    Boolean markAsLost(Long roundId, PacketDTO packet);
    Boolean deliver(Long roundId, PacketDTO packetDTO);
    Boolean cannotDeliver(Long roundId, PacketDTO packetDTO, String reason);
    Boolean addRemark(Long roundId, String remark);
    Boolean addLocationUpdate(Long roundId, LongLatDTO longLatDTO);
    Boolean endRound(Long roundId);
    Boolean startRound(Long roundId);
}
