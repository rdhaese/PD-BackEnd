package be.rdhaese.packetdelivery.back_end.rest_controller;

import be.rdhaese.packetdelivery.dto.PacketDTO;

import java.util.List;

/**
 * Created on 4/01/2016.
 *
 * @author Robin D'Haese
 */
public interface DeliveryRoundController {
    Long newRound(int amountOfPackets);
    List<PacketDTO> getPackets(Long roundId);
}
