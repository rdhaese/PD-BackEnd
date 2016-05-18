package be.rdhaese.packetdelivery.back_end.model.company_details;

import be.rdhaese.packetdelivery.back_end.model.AbstractModelTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createFaxEntry;

/**
 * Created on 4/05/2016.
 *
 * @author Robin D'Haese
 */
public class FaxEntryTest extends AbstractModelTest {


    private static final String FILE_NAME = "fax-entry.xml";
    private FaxEntry faxEntry;

    @Before
    public void setUp() {
        faxEntry = createFaxEntry("title", "number");
    }

    @After
    public void afterTestMethod() {
        removeFile(FILE_NAME);
    }

    @Test
    public void testCanPersistToXml() throws Exception {
        //Save to file
        persistToXml(FaxEntry.class, faxEntry, FILE_NAME);

        //Read from file
        FaxEntry newFaxEntry = readFromXml(FaxEntry.class, FILE_NAME);
        assertNotNull(newFaxEntry);
        assertEquals(faxEntry, newFaxEntry);
    }
}
