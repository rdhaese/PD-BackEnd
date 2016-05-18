package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation;

import be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.util.PacketIdGenerator;
import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.AddPacketInternalService;
import be.rdhaese.packetdelivery.back_end.model.Packet;
import be.rdhaese.packetdelivery.back_end.persistence.jpa_repositories.PacketJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created on 10/12/2015.
 *
 * @author Robin D'Haese
 */
@Service
public class AddPacketInternalServiceImpl implements AddPacketInternalService {

    @Autowired
    private PacketJpaRepository packetRepository;

    @Autowired
    private PacketIdGenerator packetIdGenerator;

    @Transactional
    public String savePacket(Packet packet) throws Exception {
        String packetId = packetIdGenerator.generatePacketId(packet);
        if (packetId != null) {
            packet.setPacketId(packetId);
            packetRepository.save(packet);
        }

        return packetId;
    }


}