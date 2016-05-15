package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation;


import be.rdhaese.packetdelivery.back_end.model.*;
import be.rdhaese.packetdelivery.back_end.persistence.jpa_repositories.PacketJpaRepository;
import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.ProblematicPacketsInternalService;
import be.rdhaese.packetdelivery.back_end.persistence.jpa_repositories.RegionJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created on 16/01/2016.
 *
 * @author Robin D'Haese
 */
@Service
public class ProblematicPacketsInternalServiceImpl implements ProblematicPacketsInternalService {

    @Autowired
    private PacketJpaRepository packetJpaRepository;

    @Autowired
    private RegionJpaRepository regionJpaRepository;

    @Override
    public Collection<Packet> getProblematicPackets() {
        return packetJpaRepository.getProblematicPackets();
    }

    @Override
    public Packet getProblematicPacket(String packetId) {
        return packetJpaRepository.getProblematicPacket(packetId);
    }

    @Override
    @Transactional
    public void reSend(String packetId) {
        Packet packet = packetJpaRepository.getPacket(packetId);
        if (packet != null) {
            packet.setPacketStatus(PacketStatus.NORMAL);
            packet.setStatusChangedOn(new Date());
            packetJpaRepository.save(packet);
        }
    }

    @Override
    @Transactional
    public void returnToSender(String packetId, Region region) {
        Packet packet = packetJpaRepository.getPacket(packetId);
        region = regionJpaRepository.getRegionFor(region.getRegionCode());
        packet.getDeliveryInfo().setRegion(region);
        ClientInfo newDeliveryInfo = packet.getClientInfo();
        ClientInfo oldDeliveryInfo = packet.getDeliveryInfo().getClientInfo();
        oldDeliveryInfo.getContactDetails().setName(newDeliveryInfo.getContactDetails().getName());
        oldDeliveryInfo.getContactDetails().setPhoneNumbers(new ArrayList<>());
        oldDeliveryInfo.getContactDetails().getPhoneNumbers().addAll(newDeliveryInfo.getContactDetails().getPhoneNumbers());
        oldDeliveryInfo.getContactDetails().setEmails(new ArrayList<>());
        oldDeliveryInfo.getContactDetails().getEmails().addAll(newDeliveryInfo.getContactDetails().getEmails());
        oldDeliveryInfo.getAddress().setStreet(newDeliveryInfo.getAddress().getStreet());
        oldDeliveryInfo.getAddress().setNumber(newDeliveryInfo.getAddress().getNumber());
        oldDeliveryInfo.getAddress().setMailbox(newDeliveryInfo.getAddress().getMailbox());
        oldDeliveryInfo.getAddress().setCity(newDeliveryInfo.getAddress().getCity());
        oldDeliveryInfo.getAddress().setPostalCode(newDeliveryInfo.getAddress().getPostalCode());
        packet.setPacketStatus(PacketStatus.NORMAL);
        packet.setStatusChangedOn(new Date());
        packetJpaRepository.save(packet);
    }



    @Override
    public Address getProblematicPacketAddress(String packetId) {
        return packetJpaRepository.getPacket(packetId).getDeliveryInfo().getClientInfo().getAddress();
    }

    @Override
    public Region getProblematicPacketRegion(String packetId) {
        return packetJpaRepository.getPacket(packetId).getDeliveryInfo().getRegion();
    }

    @Override
    @Transactional
    public void saveDeliveryAddress(String packetId, Address address, Region region) {
        Packet packet = packetJpaRepository.getPacket(packetId);
        packet.getDeliveryInfo().getClientInfo().getAddress().setStreet(address.getStreet());
        packet.getDeliveryInfo().getClientInfo().getAddress().setNumber(address.getNumber());
        packet.getDeliveryInfo().getClientInfo().getAddress().setMailbox(address.getMailbox());
        packet.getDeliveryInfo().getClientInfo().getAddress().setCity(address.getCity());
        packet.getDeliveryInfo().getClientInfo().getAddress().setPostalCode(address.getPostalCode());
        packet.getDeliveryInfo().setRegion(region);
        packetJpaRepository.save(packet);
    }
}
