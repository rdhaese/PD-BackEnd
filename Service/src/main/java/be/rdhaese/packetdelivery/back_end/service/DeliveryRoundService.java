package be.rdhaese.packetdelivery.back_end.service;

import be.rdhaese.packetdelivery.back_end.model.Packet;

import java.util.Collection;
import java.util.List;

/**
 * Created on 26/02/2016.
 *
 * @author Robin D'Haese
 */
public interface DeliveryRoundService {
    Long createNewRound(int amountOfPackets);

    List<Packet> getPackets(Long roundId);
}
