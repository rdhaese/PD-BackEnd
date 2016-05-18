package be.rdhaese.packetdelivery.back_end.model.company_details;

import be.rdhaese.packetdelivery.back_end.model.AbstractModelTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createEmailEntry;

/**
 * Created on 3/05/2016.
 *
 * @author Robin D'Haese
 */
public class EmailEntryTest extends AbstractModelTest {

    private static final String FILE_NAME = "email-entry.xml";
    private EmailEntry emailEntry;

    @Before
    public void setUp() {
        emailEntry = createEmailEntry("title", "address");
    }

    @After
    public void afterTestMethod() {
        removeFile(FILE_NAME);
    }

    @Test
    public void testCanPersistToXml() throws Exception {
        //Save to file
        persistToXml(EmailEntry.class, emailEntry, FILE_NAME);

        //Read from file
        EmailEntry newEmailEntry = readFromXml(EmailEntry.class, FILE_NAME);
        assertNotNull(newEmailEntry);
        assertEquals(emailEntry, newEmailEntry);
    }
}
