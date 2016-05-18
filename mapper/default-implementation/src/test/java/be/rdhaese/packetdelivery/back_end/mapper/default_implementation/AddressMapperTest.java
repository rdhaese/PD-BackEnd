package be.rdhaese.packetdelivery.back_end.mapper.default_implementation;

import be.rdhaese.packetdelivery.back_end.mapper.interfaces.Mapper;
import be.rdhaese.packetdelivery.back_end.model.Address;
import be.rdhaese.packetdelivery.dto.AddressDTO;
import org.junit.Before;
import org.junit.Test;

import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createAddress;
import static junit.framework.TestCase.assertEquals;

/**
 * Created on 13/05/2016.
 *
 * @author Robin D'Haese
 */
public class AddressMapperTest {

    private Mapper<Address, AddressDTO> mapper;

    private Address address;
    private AddressDTO addressDTO;

    @Before
    public void setUp() {
        mapper = new AddressMapper();
        address = createAddress("Street", "Number", null, "postalCode", "city");
        addressDTO = new AddressDTO("Street", "Number", null, "city", "postalCode");
    }

    @Test
    public void testMapToBus() {
        assertEquals(address, mapper.mapToBus(addressDTO));
    }

    @Test
    public void testMapToDto() {
        assertEquals(addressDTO, mapper.mapToDto(address));
    }
}
