package be.rdhaese.packetdelivery.back_end.persistence.jpa_repositories;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
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
