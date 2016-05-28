package be.rdhaese.packetdelivery.back_end.web_service.rest_implementation;

import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.RegionsInternalService;
import be.rdhaese.packetdelivery.back_end.mapper.interfaces.Mapper;
import be.rdhaese.packetdelivery.back_end.model.Region;
import be.rdhaese.packetdelivery.dto.RegionDTO;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 * @author Robin D'Haese
 */
public class RegionsRestWebServiceTest extends AbstractRestWebServiceTest {

    @Autowired //Mock, see TestConfig
    private RegionsInternalService regionsInternalService;

    @Autowired //Mock, see TestConfig
    private Mapper<Region, RegionDTO> regionMapper;

    @After
    public void tearDown() {
        reset(regionsInternalService, regionMapper);
    }

    @Test
    public void testRegions() throws Exception {
        Region region1 = new Region();
        region1.setRegionCode("CODE1");
        Region region2 = new Region();
        region2.setRegionCode("CODE2");
        List<Region> regions = Arrays.asList(region1, region2);
        RegionDTO regionDto1 = new RegionDTO();
        regionDto1.setCode("CODE1");
        RegionDTO regionDto2 = new RegionDTO();
        regionDto2.setCode("CODE2");
        List<RegionDTO> regionDtos = Arrays.asList(regionDto1, regionDto2);

        when(regionsInternalService.getRegions()).thenReturn(regions);
        when(regionMapper.mapToDto(regions)).thenReturn(regionDtos);

        mockMvc.perform(get("/regions/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.[0].code", is("CODE1")))
                .andExpect(jsonPath("$.[1].code", is("CODE2")));

        verify(regionsInternalService, times(1)).getRegions();
        verify(regionMapper, times(1)).mapToDto(regions);
    }
}
