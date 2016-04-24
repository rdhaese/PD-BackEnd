package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation;

import be.rdhaese.packetdelivery.back_end.persistence.jpa_repositories.PacketJpaRepository;
import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.LostPacketsInternalService;
import be.rdhaese.packetdelivery.back_end.model.Packet;
import be.rdhaese.packetdelivery.back_end.model.PacketStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

/**
 * Created on 15/01/2016.
 *
 * @author Robin D'Haese
 */
@Service
public class LostPacketsInternalServiceImpl implements LostPacketsInternalService {

    @Autowired
    private PacketJpaRepository packetJpaRepository;

    @Override
    public Collection<Packet> getLostPackets() {
       return packetJpaRepository.getLostPackets();
    }

    @Override
    public void markAsFound(String packetId) {
        Packet foundPacket = packetJpaRepository.getPacket(packetId);
        foundPacket.setPacketStatus(PacketStatus.NORMAL);
        foundPacket.setStatusChangedOn(new Date());
        packetJpaRepository.save(foundPacket);
    }

    @Override
    public void removeFromSystem(String packetId) {
        packetJpaRepository.delete(packetJpaRepository.getPacket(packetId));
    }
}
