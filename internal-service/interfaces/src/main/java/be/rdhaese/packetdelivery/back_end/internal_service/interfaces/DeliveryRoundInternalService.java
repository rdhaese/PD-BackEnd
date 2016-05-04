package be.rdhaese.packetdelivery.back_end.internal_service.interfaces;

import be.rdhaese.packetdelivery.back_end.model.Address;
import be.rdhaese.packetdelivery.back_end.model.LongLat;
import be.rdhaese.packetdelivery.back_end.model.Packet;

import java.util.List;

/**
 * Created on 26/02/2016.
 *
 * @author Robin D'Haese
 */
public interface DeliveryRoundInternalService {
    Long createNewRound(int amountOfPackets);

    List<Packet> getPackets(Long roundId) throws Exception;

    Boolean markAsLost(Long roundId, Packet packet) throws Exception;

    Boolean endRound(Long roundId);

    Boolean startRound(Long roundId) throws Exception;

    Boolean addRemark(Long roundId, String remark);

    Boolean cannotDeliver(Long roundId, Packet packet, String reason) throws Exception;

    Boolean deliver(Long roundId, Packet packet) throws Exception;

    Boolean addLocationUpdate(Long roundId, LongLat longLat);

    Address getCompanyAddress() throws Exception;
}
