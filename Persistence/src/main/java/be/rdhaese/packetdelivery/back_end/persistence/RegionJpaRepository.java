package be.rdhaese.packetdelivery.back_end.persistence;


import be.rdhaese.packetdelivery.back_end.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created on 25/12/2015.
 *
 * @author Robin D'Haese
 */
@Repository
public interface RegionJpaRepository extends JpaRepository<Region, Long> {

    @Query("SELECT r FROM Region r WHERE r.regionCode = ?1")
    Region getRegionFor(String regionCode);
}
