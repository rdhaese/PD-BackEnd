package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation;

import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.LostPacketsInternalService;
import be.rdhaese.packetdelivery.back_end.model.Packet;
import be.rdhaese.packetdelivery.back_end.model.PacketStatus;
import be.rdhaese.packetdelivery.back_end.persistence.jpa_repositories.PacketJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;

/**
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
    @Transactional
    public void markAsFound(String packetId) {
        Packet foundPacket = packetJpaRepository.getPacket(packetId);
        //Because the app can run on multiple clients, we need to check that request that come in still match the real state.
        if ((foundPacket != null) && (PacketStatus.NOT_FOUND.equals(foundPacket.getPacketStatus()))) {
            foundPacket.setPacketStatus(PacketStatus.NORMAL);
            foundPacket.setStatusChangedOn(new Date());
            packetJpaRepository.save(foundPacket);
        }
    }

    @Override
    @Transactional
    public void removeFromSystem(String packetId) {
        Packet packetToRemove = packetJpaRepository.getPacket(packetId);
        //Because the app can run on multiple clients, we need to check that request that come in still match the real state.
        if ((packetToRemove != null) && (PacketStatus.NOT_FOUND.equals(packetToRemove.getPacketStatus()))) {
            packetJpaRepository.delete(packetToRemove);
        }
    }
}
