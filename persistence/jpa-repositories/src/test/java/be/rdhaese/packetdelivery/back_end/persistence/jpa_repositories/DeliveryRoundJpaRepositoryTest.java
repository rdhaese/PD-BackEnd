package be.rdhaese.packetdelivery.back_end.persistence.jpa_repositories;

import be.rdhaese.packetdelivery.back_end.model.DeliveryRound;
import be.rdhaese.packetdelivery.back_end.model.RoundStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Robin D'Haese
 */
public class DeliveryRoundJpaRepositoryTest extends AbstractJpaRepositoryTest {

    @Autowired
    private DeliveryRoundJpaRepository deliveryRoundJpaRepository;

    @Before
    public void setUp() {
        DeliveryRound deliveryRound = new DeliveryRound();
        deliveryRound.setRoundStatus(RoundStatus.NOT_STARTED);
        deliveryRoundJpaRepository.save(deliveryRound);
        deliveryRound = new DeliveryRound();
        deliveryRound.setRoundStatus(RoundStatus.STARTED);
        deliveryRoundJpaRepository.save(deliveryRound);
        deliveryRound = new DeliveryRound();
        deliveryRound.setRoundStatus(RoundStatus.STARTED);
        deliveryRoundJpaRepository.save(deliveryRound);
        deliveryRound = new DeliveryRound();
        deliveryRound.setRoundStatus(RoundStatus.STARTED);
        deliveryRoundJpaRepository.save(deliveryRound);
        deliveryRoundJpaRepository.flush();
    }

    @After
    public void afterTestMethod() {
        deliveryRoundJpaRepository.deleteAll();
    }

    @Test
    public void testOngoingRound() {
        assertEquals(3, deliveryRoundJpaRepository.getOngoingRounds().size());
    }
}
