package be.rdhaese.packetdelivery.back_end.mapper.default_implementation;

import be.rdhaese.packetdelivery.back_end.mapper.interfaces.Mapper;
import be.rdhaese.packetdelivery.back_end.model.Address;
import be.rdhaese.packetdelivery.back_end.model.Region;
import be.rdhaese.packetdelivery.dto.DeliveryAddressDTO;
import be.rdhaese.packetdelivery.dto.RegionDTO;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;

import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.*;
import static org.mockito.Mockito.*;

/**
 * Created on 13/05/2016.
 *
 * @author Robin D'Haese
 */
@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration
public class DeliveryAddressMapperImplTest extends TestCase {

    @InjectMocks
    private DeliveryAddressMapperImpl deliveryAddressMapper;

    @Mock
    private Mapper<Region, RegionDTO> regionMapper;

    private RegionDTO regionDto;
    private Region region;
    private Address address;
    private String packetId;
    private DeliveryAddressDTO deliveryAddressDto;


    @Before
    public void setUp() {
        region = createRegion(createRegionName("nameNl", "nameFr", "nameDe", "nameEn"), "CODE");
        when(regionMapper.mapToBus(any(RegionDTO.class))).thenReturn(region);

        address = createAddress("Street", "Number", null, "postalCode", "city");
        packetId = "packetId";

        deliveryAddressDto = new DeliveryAddressDTO();
        deliveryAddressDto.setPacketId(packetId);
        deliveryAddressDto.setStreet("Street");
        deliveryAddressDto.setNumber("Number");
        deliveryAddressDto.setMailbox(null);
        deliveryAddressDto.setPostalCode("postalCode");
        deliveryAddressDto.setCity("city");
        deliveryAddressDto.setRegionNameNl("nameNl");
        deliveryAddressDto.setRegionNameFr("nameFr");
        deliveryAddressDto.setRegionNameDe("nameDe");
        deliveryAddressDto.setRegionNameEn("nameEn");
        deliveryAddressDto.setRegionCode("CODE");
    }

    @Test
    public void testMapToBus() {
        Object[] busObjs = deliveryAddressMapper.mapToBus(deliveryAddressDto);

        TestCase.assertEquals(3, busObjs.length);
        TestCase.assertEquals(packetId, busObjs[0]);
        TestCase.assertEquals(address, busObjs[1]);
        TestCase.assertEquals(region, busObjs[2]);

        verify(regionMapper, times(1)).mapToBus(any(RegionDTO.class));
    }

    @Test
    public void testMapToDto() {
        TestCase.assertEquals(deliveryAddressDto, deliveryAddressMapper.mapToDto(address, region, packetId));
    }
}
