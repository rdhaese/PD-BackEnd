package be.rdhaese.packetdelivery.back_end.service.impl;

import be.rdhaese.packetdelivery.back_end.model.Packet;
import be.rdhaese.packetdelivery.back_end.persistence.PacketJpaRepository;
import be.rdhaese.packetdelivery.back_end.service.ProblematicPacketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created on 16/01/2016.
 *
 * @author Robin D'Haese
 */
@Service
public class ProblematicPacketsServiceImpl implements ProblematicPacketsService{

    @Autowired
    private PacketJpaRepository packetJpaRepository;

    @Override
    public Collection<Packet> getProblematicPackets() {
        return packetJpaRepository.getProblematicPackets();
    }
}
