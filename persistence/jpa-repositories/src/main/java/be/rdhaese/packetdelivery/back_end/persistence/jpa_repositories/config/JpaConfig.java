package be.rdhaese.packetdelivery.back_end.persistence.jpa_repositories.config;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 * @author Robin D'Haese
 */
@Configuration
@EnableJpaRepositories(basePackages = "be.rdhaese.packetdelivery.back_end.persistence.jpa_repositories")
@EntityScan(basePackages = "be.rdhaese.packetdelivery.back_end.model")
class JpaConfig {
}
