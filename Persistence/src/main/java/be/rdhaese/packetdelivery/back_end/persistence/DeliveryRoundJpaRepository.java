package be.rdhaese.packetdelivery.back_end.persistence;

import be.rdhaese.packetdelivery.back_end.model.DeliveryRound;
import be.rdhaese.packetdelivery.back_end.model.Packet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created on 26/02/2016.
 *
 * @author Robin D'Haese
 */
@Repository
public interface DeliveryRoundJpaRepository extends JpaRepository<DeliveryRound, Long> {


}
