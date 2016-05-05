package be.rdhaese.packetdelivery.ack_end.persistence.jpa_repositories;

import be.rdhaese.packetdelivery.back_end.model.Region;
import be.rdhaese.packetdelivery.back_end.persistence.jpa_repositories.RegionJpaRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createRegion;
import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createRegionName;

/**
 * Created on 4/05/2016.
 *
 * @author Robin D'Haese
 */
public class RegionJpaRepositoryTest extends AbstractJpaRepositoryTest {

    @Autowired
    private RegionJpaRepository regionJpaRepository;

    @Before
    public void setUp(){
        Region region = createRegion(createRegionName("nl", "fr", "de", "en"), "CODE");
        Region adjacentRegion1 = createRegion(createRegionName("nl1", "fr1", "de1", "en1"), "CODE1");
        Region adjacentRegion2 = createRegion(createRegionName("nl2", "fr2", "de2", "en2"), "CODE2");
        Region adjacentRegion3 = createRegion(createRegionName("nl3", "fr3", "de3", "en3"), "CODE3");
        adjacentRegion1.getAdjacentRegions().add(adjacentRegion2);
        adjacentRegion1.getAdjacentRegions().add(adjacentRegion3);
        region.getAdjacentRegions().add(adjacentRegion1);
        regionJpaRepository.saveAndFlush(region);
    }

    @After
    public void afterTestMethod(){
        regionJpaRepository.deleteAll();
    }

    @Test
    public void testGetRegionForCode(){
        assertNotNull(regionJpaRepository.getRegionFor("CODE"));
        assertNotNull(regionJpaRepository.getRegionFor("CODE3"));
        assertNull(regionJpaRepository.getRegionFor("UNKNOWN_CODE"));
    }
}