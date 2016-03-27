package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation;

import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.DeliveryRoundInternalService;
import be.rdhaese.packetdelivery.back_end.model.DeliveryRound;
import be.rdhaese.packetdelivery.back_end.model.Packet;

import be.rdhaese.packetdelivery.back_end.persistence.jpa_repositories.DeliveryRoundJpaRepository;
import be.rdhaese.packetdelivery.back_end.persistence.jpa_repositories.PacketJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 26/02/2016.
 *
 * @author Robin D'Haese
 */
@Service
public class DeliveryRoundInternalServiceImpl implements DeliveryRoundInternalService {

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
