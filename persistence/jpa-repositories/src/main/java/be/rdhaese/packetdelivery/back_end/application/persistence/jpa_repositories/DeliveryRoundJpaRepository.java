package be.rdhaese.packetdelivery.back_end.application.persistence.jpa_repositories;

import be.rdhaese.packetdelivery.back_end.application.model.DeliveryRound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created on 26/02/2016.
 *
 * @author Robin D'Haese
 */
@Repository
public interface DeliveryRoundJpaRepository extends JpaRepository<DeliveryRound, Long> {


}
