package be.rdhaese.packetdelivery.back_end.persistence.jpa_repositories;

import be.rdhaese.packetdelivery.back_end.model.DeliveryRound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created on 26/02/2016.
 *
 * @author Robin D'Haese
 */
@Repository
public interface DeliveryRoundJpaRepository extends JpaRepository<DeliveryRound, Long> {

    @Query("SELECT d from DeliveryRound d WHERE d.roundStatus = 'STARTED'")
    Collection<DeliveryRound> getOngoingRounds();

}
