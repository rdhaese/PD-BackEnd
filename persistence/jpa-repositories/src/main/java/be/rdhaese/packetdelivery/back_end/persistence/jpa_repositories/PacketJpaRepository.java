package be.rdhaese.packetdelivery.back_end.persistence.jpa_repositories;


import be.rdhaese.packetdelivery.back_end.model.Packet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
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

    @Query("SELECT p FROM Packet p WHERE p.packetStatus = 'NORMAL' AND p.deliveryInfo.region.id =?1")
    Collection<Packet> getForRegion(Long regionId);
}
