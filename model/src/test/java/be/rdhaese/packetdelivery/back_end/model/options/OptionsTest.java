package be.rdhaese.packetdelivery.back_end.model.options;

import be.rdhaese.packetdelivery.back_end.model.AbstractModelTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createOptions;

/**
 * Created on 3/05/2016.
 *
 * @author Robin D'Haese
 */
public class OptionsTest extends AbstractModelTest {

    private static final String FILE_NAME = "options.xml";
    private Options options;

    @Before
    public void setUp() {
        options = createOptions("username", "english", 1, true);
    }

    @After
    public void afterTestMethod() {
        removeFile(FILE_NAME);
    }

    @Test
    public void testCanPersistToXml() throws Exception {
        //Save to file
        persistToXml(Options.class, options, FILE_NAME);

        //Read from file
        Options newOptions = readFromXml(Options.class, FILE_NAME);
        assertNotNull(newOptions);
        assertEquals(options, newOptions);
    }
}
