package be.rdhaese.packetdelivery.ack_end.persistence.jpa_repositories;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created on 4/05/2016.
 *
 * @author Robin D'Haese
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        DeliveryRoundJpaRepositoryTest.class,
        PacketJpaRepositoryTest.class,
        RegionJpaRepositoryTest.class
})
public class JpaRepositoriesTestSuite {
}
