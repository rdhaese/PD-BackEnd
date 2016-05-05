package be.rdhaese.packetdelivery.ack_end.persistence.jpa_repositories;

import be.rdhaese.packetdelivery.ack_end.persistence.jpa_repositories.config.JpaTestConfig;
import be.rdhaese.packetdelivery.back_end.persistence.jpa_repositories.config.JpaConfig;
import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

/**
 * Created on 4/05/2016.
 *
 * @author Robin D'Haese
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JpaTestConfig.class)
@Transactional
public abstract class AbstractJpaRepositoryTest extends TestCase {
}
