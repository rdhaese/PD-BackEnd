package be.rdhaese.packetdelivery.back_end.web_service.interfaces;

import be.rdhaese.packetdelivery.dto.AddressDTO;
import be.rdhaese.packetdelivery.dto.LongLatDTO;
import be.rdhaese.packetdelivery.dto.PacketDTO;

import java.util.List;

/**
 *
 * @author Robin D'Haese
 */
public interface DeliveryRoundWebService {
    Long newRound(int amountOfPackets);

    List<PacketDTO> getPackets(Long roundId) throws Exception;

    Boolean markAsLost(Long roundId, PacketDTO packet) throws Exception;

    Boolean deliver(Long roundId, PacketDTO packetDTO) throws Exception;

    Boolean cannotDeliver(Long roundId, String reason, PacketDTO packetDTO) throws Exception;

    Boolean addRemark(Long roundId, String remark);

    Boolean addLocationUpdate(Long roundId, LongLatDTO longLatDTO);

    Boolean endRound(Long roundId);

    Boolean startRound(Long roundId) throws Exception;

    AddressDTO getCompanyAddress() throws Exception;
}
