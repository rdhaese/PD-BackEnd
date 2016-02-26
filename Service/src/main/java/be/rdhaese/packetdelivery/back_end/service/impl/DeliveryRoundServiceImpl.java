package be.rdhaese.packetdelivery.back_end.service.impl;

import be.rdhaese.packetdelivery.back_end.model.DeliveryRound;
import be.rdhaese.packetdelivery.back_end.model.Packet;
import be.rdhaese.packetdelivery.back_end.persistence.PacketJpaRepository;
import be.rdhaese.packetdelivery.back_end.persistence.DeliveryRoundJpaRepository;
import be.rdhaese.packetdelivery.back_end.service.DeliveryRoundService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created on 26/02/2016.
 *
 * @author Robin D'Haese
 */
public class DeliveryRoundServiceImpl implements DeliveryRoundService {

    @Autowired
    private DeliveryRoundJpaRepository roundRepository;

    @Autowired
    private PacketJpaRepository packetRepository;

    @Override
    public Long createNewRound(int amountOfPackets) {
        DeliveryRound deliveryRound = new DeliveryRound();
        //TODO get packets based on criteria
        List<Packet> packets = packetRepository.findAll();
        deliveryRound.setPackets(packets);
        roundRepository.save(deliveryRound);
        return deliveryRound.getId();
    }

    @Override
    public List<Packet> getPackets(Long roundId) {
        return roundRepository.getOne(roundId).getPackets();
    }
}
