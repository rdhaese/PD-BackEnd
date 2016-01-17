package be.rdhaese.packetdelivery.back_end.persistence;


import be.rdhaese.packetdelivery.back_end.model.Packet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created on 10/12/2015.
 *
 * @author Robin D'Haese
 */
@Repository
public interface PacketJpaRepository extends JpaRepository<Packet, Long> {
    @Query("SELECT p FROM Packet p WHERE p.packetId =?1")
    Packet getPacket(String packetId);

    @Query("SELECT p FROM Packet p WHERE p.packetStatus = 'NOT_FOUND'")
    Collection<Packet> getLostPackets();

    @Query("SELECT p FROM Packet p WHERE p.packetStatus = 'PROBLEMATIC'")
    Collection<Packet> getProblematicPackets();

    @Query("SELECT p FROM Packet p WHERE p.packetStatus = 'PROBLEMATIC' AND p.packetId =?1")
    Packet getProblematicPacket(String packetId);
}
