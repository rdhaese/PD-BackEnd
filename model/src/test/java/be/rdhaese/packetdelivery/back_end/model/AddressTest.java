package be.rdhaese.packetdelivery.back_end.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;

import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createAddress;

/**
 *
 * @author Robin D'Haese
 */
public class AddressTest extends AbstractModelTest {

    private static final String FILE_NAME = "address.xml";
    private Address address;

    @Before
    public void setUp() {
        address = createAddress("Ezelberg", "2", "12", "9500", "Geraardsbergen");
    }

    @After
    public void afterTestMethod() {
        removeFile(FILE_NAME);
    }

    @Test
    public void testCanPersist() {
        //Check if id is null on creation
        assertNull(address.getId());

        //Persist
        persistFlushAndClear(address);

        //Check if id is assigned
        assertNotNull(address.getId());

        //Check if address can be found on assigned id
        Address newAddress = getEntityManager().find(Address.class, address.getId());
        assertNotNull(newAddress);
        assertEquals(address.getStreet(), newAddress.getStreet());
    }

    @Test(expected = ConstraintViolationException.class)
    public void testStreetCannotBeNull() {
        address.setStreet(null);

        persistFlushAndClear(address);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testNumberCannotBeNull() {
        address.setNumber(null);

        persistFlushAndClear(address);
    }

    @Test
    public void testMailboxCanBeNull() {
        address.setMailbox(null);

        persistFlushAndClear(address);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testPostalCodeCannotBeNull() {
        address.setPostalCode(null);

        persistFlushAndClear(address);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testCityCannotBeNull() {
        address.setCity(null);

        persistFlushAndClear(address);
    }

    @Test
    public void testCanPersistToXml() throws Exception {
        //Save to file
        persistToXml(Address.class, address, FILE_NAME);

        //Read from file
        Address newAddress = readFromXml(Address.class, FILE_NAME);
        assertNotNull(newAddress);
        assertEquals(address.getStreet(), newAddress.getStreet());
    }
}
