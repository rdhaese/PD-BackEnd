package be.rdhaese.packetdelivery.back_end.service.impl;

import be.rdhaese.packetdelivery.back_end.model.Packet;
import be.rdhaese.packetdelivery.back_end.model.PacketStatus;
import be.rdhaese.packetdelivery.back_end.persistence.PacketJpaRepository;
import be.rdhaese.packetdelivery.back_end.service.LostPacketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created on 15/01/2016.
 *
 * @author Robin D'Haese
 */
@Service
public class LostPacketsServiceImpl implements LostPacketsService{

    @Autowired
    private PacketJpaRepository packetJpaRepository;

    @Override
    public List<Packet> getLostPackets() {
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
