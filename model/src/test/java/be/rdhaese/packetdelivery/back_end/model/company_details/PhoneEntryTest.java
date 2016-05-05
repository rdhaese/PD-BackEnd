package be.rdhaese.packetdelivery.back_end.model.company_details;

import be.rdhaese.packetdelivery.back_end.model.AbstractModelTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createPhoneEntry;

/**
 * Created on 4/05/2016.
 *
 * @author Robin D'Haese
 */
public class PhoneEntryTest extends AbstractModelTest {

    private static final String FILE_NAME = "phone-entry.xml";
    private PhoneEntry phoneEntry;

    @Before
    public void setUp(){
        phoneEntry = createPhoneEntry("title", "number");
    }

    @After
    public void afterTestMethod(){
        removeFile(FILE_NAME);

    }

    @Test
    public void testCanPersistToXml() throws Exception{
        //Save to file
        persistToXml(PhoneEntry.class, phoneEntry, FILE_NAME);

        //Read from file
        PhoneEntry newPhoneEntry = readFromXml(PhoneEntry.class, FILE_NAME);
        assertNotNull(newPhoneEntry);
        assertEquals(phoneEntry, newPhoneEntry);
    }
}
