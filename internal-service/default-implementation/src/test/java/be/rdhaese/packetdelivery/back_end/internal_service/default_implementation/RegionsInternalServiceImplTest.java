package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation;

import be.rdhaese.packetdelivery.back_end.model.Region;
import be.rdhaese.packetdelivery.back_end.model.RegionName;
import be.rdhaese.packetdelivery.back_end.persistence.jpa_repositories.RegionJpaRepository;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;

import java.util.ArrayList;
import java.util.List;

import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createRegion;
import static org.mockito.Mockito.*;

/**
 *
 * @author Robin D'Haese
 */
@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration
public class RegionsInternalServiceImplTest extends TestCase {

    @InjectMocks
    private RegionsInternalServiceImpl regionsInternalService;

    @Mock
    private RegionJpaRepository regionJpaRepository;

    @Before
    public void setUp() {
        Region mockRegion1 = createRegion(new RegionName(), "CODE");
        when(regionJpaRepository.getRegionFor("CODE")).thenReturn(mockRegion1);

        List<Region> mockRegions = new ArrayList<>();
        mockRegions.add(mockRegion1);
        mockRegions.add(createRegion(new RegionName(), "CODE2"));
        when(regionJpaRepository.findAll()).thenReturn(mockRegions);
    }

    @Test
    public void testGetRegionForRegionCode() {
        TestCase.assertNotNull(regionsInternalService.getRegionFor("CODE"));
        TestCase.assertNull(regionsInternalService.getRegionFor("UNKNOWN_CODE"));
        verify(regionJpaRepository, times(2)).getRegionFor(any());
    }

    @Test
    public void testGetRegions() {
        TestCase.assertEquals(2, regionsInternalService.getRegions().size());
        verify(regionJpaRepository, times(1)).findAll();
    }

    @Test
    public void testSaveRegion() {
        regionsInternalService.save(createRegion(new RegionName(), "CODE3"));
        verify(regionJpaRepository, times(1)).save(any(Region.class));
    }
}
