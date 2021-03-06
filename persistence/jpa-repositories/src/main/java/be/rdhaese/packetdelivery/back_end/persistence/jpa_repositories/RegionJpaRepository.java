package be.rdhaese.packetdelivery.back_end.persistence.jpa_repositories;


import be.rdhaese.packetdelivery.back_end.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Robin D'Haese
 */
@Repository
public interface RegionJpaRepository extends JpaRepository<Region, Long> {

    @Query("SELECT r FROM Region r WHERE r.regionCode = ?1")
    Region getRegionFor(String regionCode);
}
