package be.rdhaese.packetdelivery.back_end.model;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;

import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createContactDetails;

/**
 * Created on 3/05/2016.
 *
 * @author Robin D'Haese
 */
public class ContactDetailsTest extends AbstractModelTest {

    private ContactDetails contactDetails;

    @Before
    public void setUp() {
        contactDetails = createContactDetails(
                "name",
                Arrays.asList("phonenumber1", "phoneNumber2"),
                Arrays.asList("email1", "email2")
        );
    }

    @Test
    public void testCanPersist() {
        //Check if id is null on creation
        assertNull(contactDetails.getId());

        //Persist
        persistFlushAndClear(contactDetails);

        //Check if id is assigned
        assertNotNull(contactDetails.getId());

        //Check if contact details can be found on assigned id
        ContactDetails newContactDetails = getEntityManager().find(ContactDetails.class, contactDetails.getId());
        assertNotNull(newContactDetails);
        assertEquals(contactDetails.getName(), newContactDetails.getName());
        assertEquals(2, newContactDetails.getPhoneNumbers().size());
        assertEquals(2, newContactDetails.getEmails().size());
    }

    @Test(expected = ConstraintViolationException.class)
    public void testNameCannotBeNull() {
        contactDetails.setName(null);

        persistFlushAndClear(contactDetails);
    }
}
