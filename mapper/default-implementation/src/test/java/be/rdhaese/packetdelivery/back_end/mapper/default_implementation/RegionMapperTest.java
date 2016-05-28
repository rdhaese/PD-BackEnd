package be.rdhaese.packetdelivery.back_end.mapper.default_implementation;

import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.RegionsInternalService;
import be.rdhaese.packetdelivery.back_end.model.Region;
import be.rdhaese.packetdelivery.dto.RegionDTO;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;

import java.util.Calendar;
import java.util.Date;

import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createRegion;
import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createRegionName;
import static org.mockito.Mockito.*;

/**
 *
 * @author Robin D'Haese
 */
@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration
public class RegionMapperTest extends TestCase {

    @InjectMocks
    private RegionMapper mapper;

    @Mock
    private RegionsInternalService regionsInternalService;

    private final Date date = Calendar.getInstance().getTime();
    private Region region;
    private RegionDTO regionDto;

    @Before
    public void setUp() {
        region = createRegion(
                createRegionName("nameNl", "nameFr", "nameDe", "nameEn"),
                "CODE"
        );
        regionDto = new RegionDTO("nameNl", "nameFr", "nameDe", "nameEn", "CODE");

        when(regionsInternalService.getRegionFor("CODE")).thenReturn(region);
    }

    @Test
    public void testMapToBus() {
        TestCase.assertEquals(region, mapper.mapToBus(regionDto));
        verify(regionsInternalService, times(1)).getRegionFor(anyString());
    }

    @Test
    public void testMapToDto() {
        verifyNoMoreInteractions(regionsInternalService);
        TestCase.assertEquals(regionDto, mapper.mapToDto(region));
    }
}
