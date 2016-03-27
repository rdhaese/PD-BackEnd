package be.rdhaese.packetdelivery.back_end.internal_service.interfaces;

import be.rdhaese.packetdelivery.back_end.model.Packet;

import java.util.List;

/**
 * Created on 26/02/2016.
 *
 * @author Robin D'Haese
 */
public interface DeliveryRoundInternalService {
    Long createNewRound(int amountOfPackets);

    List<Packet> getPackets(Long roundId);
}
