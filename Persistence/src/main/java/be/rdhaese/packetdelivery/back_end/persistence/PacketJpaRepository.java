package be.rdhaese.packetdelivery.back_end.persistence;

import be.rdhaese.project.model.Packet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created on 10/12/2015.
 *
 * @author Robin D'Haese
 */
@Repository
public interface PacketJpaRepository extends JpaRepository<Packet, Long> {
}
