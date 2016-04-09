package be.rdhaese.packetdelivery.back_end.application.internal_service.interfaces;

import be.rdhaese.packetdelivery.back_end.application.model.Packet;

import java.util.List;

/**
 * Created on 26/02/2016.
 *
 * @author Robin D'Haese
 */
public interface DeliveryRoundInternalService {
    Long createNewRound(int amountOfPackets);

    List<Packet> getPackets(Long roundId);

    Boolean markAsLost(Long roundId, Packet packet);

    Boolean endRound(Long roundId);

    Boolean startRound(Long roundId);

    Boolean addRemark(Long roundId, String remark);

    Boolean cannotDeliver(Long roundId, Packet packet, String reason);
}
