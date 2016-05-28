package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.util;

import be.rdhaese.packetdelivery.back_end.model.Address;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNull;

/**
 *
 * @author Robin D'Haese
 */
public class AddressToGoogleApiStringConverterTest {

    private AddressToGoogleApiStringConverter addressToGoogleApiStringConverter;

    private Address address;

    @Before
    public void setUp() {
        addressToGoogleApiStringConverter = new AddressToGoogleApiStringConverter();
        address = new Address();
        address.setStreet("Ezelberg");
        address.setNumber("2");
        address.setMailbox("12");
        address.setPostalCode("9500");
        address.setCity("Schendelbeke");
    }

    @Test
    public void testConvert() {
        String expectedString = "Ezelberg 2, 9500 Schendelbeke";
        String actualString = addressToGoogleApiStringConverter.convert(address);
        assertEquals(expectedString, actualString);

        assertNull(addressToGoogleApiStringConverter.convert(null));
    }
}
