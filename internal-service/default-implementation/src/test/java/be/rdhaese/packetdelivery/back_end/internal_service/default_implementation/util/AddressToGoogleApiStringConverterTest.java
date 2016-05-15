package be.rdhaese.packetdelivery.back_end.internal_service.default_implementation.util;

import be.rdhaese.packetdelivery.back_end.model.Address;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Created on 5/05/2016.
 *
 * @author Robin D'Haese
 */
public class AddressToGoogleApiStringConverterTest extends TestCase{

    AddressToGoogleApiStringConverter addressToGoogleApiStringConverter;

    private Address address;

    @Before
    public void setUp(){
        addressToGoogleApiStringConverter = new AddressToGoogleApiStringConverter();
        address = new Address();
        address.setStreet("Ezelberg");
        address.setNumber("2");
        address.setMailbox("12");
        address.setPostalCode("9500");
        address.setCity("Schendelbeke");
    }

    @Test
    public void testConvert(){
        String expectedString = "Ezelberg 2, 9500 Schendelbeke";
        String actualString = addressToGoogleApiStringConverter.convert(address);
        assertEquals(expectedString, actualString);

        assertNull(addressToGoogleApiStringConverter.convert(null));
    }
}
