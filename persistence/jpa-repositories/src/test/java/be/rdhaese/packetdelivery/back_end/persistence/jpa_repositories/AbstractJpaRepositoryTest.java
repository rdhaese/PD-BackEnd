package be.rdhaese.packetdelivery.back_end.persistence.jpa_repositories;

import be.rdhaese.packetdelivery.back_end.persistence.jpa_repositories.config.JpaTestConfig;
import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

/**
 *
 * @author Robin D'Haese
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JpaTestConfig.class)
@Transactional
public abstract class AbstractJpaRepositoryTest extends TestCase {
}
