package be.rdhaese.packetdelivery.back_end.model;

import junit.framework.TestResult;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.List;

import static be.rdhaese.packetdelivery.back_end.testing.TestUtil.createAddress;
import static be.rdhaese.packetdelivery.back_end.testing.TestUtil.createContactDetails;
import static be.rdhaese.packetdelivery.back_end.testing.TestUtil.createClientInfo;

/**
 * Created on 3/05/2016.
 *
 * @author Robin D'Haese
 */
public class ClientInfoTest extends AbstractModelTest {

    private ClientInfo clientInfo;

    @Before
    public void setUp() {
        clientInfo = createClientInfo(
                createContactDetails(
                        "name",
                        Arrays.asList(new String[]{"phonenumber1", "phoneNumber2"}),
                        Arrays.asList(new String[]{"email1", "email2"})
                ),
                createAddress("Ezelberg", "2", "12", "9500", "Geraardsbergen")
        );
    }

    @Test
    public void testCanPersist(){
        //Check if id is null on creation
        assertNull(clientInfo.getId());

        //Persist
        persistFlushAndClear(clientInfo);

        //Check if id is assigned
        assertNotNull(clientInfo.getId());

        //Check if client info can be found on assigned id
        ClientInfo newClientInfo = getEntityManager().find(ClientInfo.class, clientInfo.getId());
        assertNotNull(newClientInfo);
        assertEquals(clientInfo.getContactDetails(), newClientInfo.getContactDetails());
        assertEquals(clientInfo.getAddress(), newClientInfo.getAddress());
    }

    @Test
    public void testAddressRemovedWithClientInfo(){
        //Persist
        persistFlushAndClear(clientInfo);

        //Remove client info
        clientInfo = getEntityManager().find(ClientInfo.class, clientInfo.getId());
        getEntityManager().remove(clientInfo);

        //Check if address is also removed
        List<Address> addressesInDatabase = getEntityManager().createQuery("SELECT a FROM Address a").getResultList();
        assertEquals(0, addressesInDatabase.size());
    }

    @Test
    public void testContactDetailsRemovedWithClientInfo(){
        //Persist
        persistFlushAndClear(clientInfo);

        //Remove client info
        clientInfo = getEntityManager().find(ClientInfo.class, clientInfo.getId());
        getEntityManager().remove(clientInfo);

        //Check if contact details is also removed
        List<ContactDetails> contactDetailsInDatabase = getEntityManager().createQuery("SELECT c FROM ContactDetails c").getResultList();
        assertEquals(0, contactDetailsInDatabase.size());
    }

    @Test(expected = ConstraintViolationException.class)
    public void testContactDetailsCannotBeNull(){
        clientInfo.setContactDetails(null);

        persistFlushAndClear(clientInfo);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testAddressCannotBeNull(){
        clientInfo.setAddress(null);

        persistFlushAndClear(clientInfo);
    }
}
