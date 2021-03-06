package be.rdhaese.packetdelivery.back_end.web_service.rest_implementation;

import be.rdhaese.packetdelivery.back_end.internal_service.interfaces.LongLatInternalService;
import be.rdhaese.packetdelivery.back_end.mapper.interfaces.Mapper;
import be.rdhaese.packetdelivery.back_end.model.Address;
import be.rdhaese.packetdelivery.back_end.model.LongLat;
import be.rdhaese.packetdelivery.dto.AddressDTO;
import be.rdhaese.packetdelivery.dto.LongLatDTO;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createAddress;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author Robin D'Haese
 */
public class LongLatRestWebServiceTest extends AbstractRestWebServiceTest {

    @Autowired //Mock, see TestConfig
    private LongLatInternalService longLatInternalService;

    @Autowired //Mock, see TestConfig
    private Mapper<LongLat, LongLatDTO> longLatMapper;

    @Autowired //Mock, see TestConfig
    private Mapper<Address, AddressDTO> addressMapper;

    @After
    public void tearDown() {
        reset(longLatInternalService, longLatMapper, addressMapper);
    }

    @Test
    public void testGetForAddress() throws Exception {
        Address address = createAddress("street", "number", null, "postalCode", "city");
        AddressDTO addressDTO = new AddressDTO("street", "number", null, "city", "postalCode");
        LongLat longLat = new LongLat(2D, 3D);
        LongLatDTO longLatDTO = new LongLatDTO(3D, 2D);

        when(addressMapper.mapToBus(addressDTO)).thenReturn(address);
        when(longLatInternalService.getForAddress(address)).thenReturn(longLat);
        when(longLatMapper.mapToDto(longLat)).thenReturn(longLatDTO);

        mockMvc.perform(post("/long-lat/for-address")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(AbstractRestWebServiceTest.convertObjectToJsonBytes(addressDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().bytes(AbstractRestWebServiceTest.convertObjectToJsonBytes(longLatDTO)));

        verify(addressMapper, times(1)).mapToBus(addressDTO);
        verify(longLatInternalService, times(1)).getForAddress(address);
        verify(longLatMapper, times(1)).mapToDto(longLat);
    }
}
