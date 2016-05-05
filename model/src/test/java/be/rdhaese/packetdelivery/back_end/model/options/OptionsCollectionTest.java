package be.rdhaese.packetdelivery.back_end.model.options;

import be.rdhaese.packetdelivery.back_end.model.AbstractModelTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createOptions;
import static be.rdhaese.packetdelivery.back_end.model.util.CreateModelObjectUtil.createOptionsCollection;


/**
 * Created on 3/05/2016.
 *
 * @author Robin D'Haese
 */
public class OptionsCollectionTest extends AbstractModelTest {

    private static final String FILE_NAME = "options.xml";
    private OptionsCollection optionsCollection;

    @Before
    public void setUp() {
        List<Options> optionsList = Arrays.asList(new Options[]{
                createOptions("username", "english", 1, true),
                createOptions("username1", "nederlands", 0, false)
        });
        optionsCollection = createOptionsCollection(optionsList);
    }

    @After
    public void afterTestMethod() {
        removeFile(FILE_NAME);
    }

    @Test
    public void testCanPersistToXml() throws Exception {
        //Save to file
        persistToXml(OptionsCollection.class, optionsCollection, FILE_NAME);

        //Read from file
        OptionsCollection newOptionsCollection = readFromXml(OptionsCollection.class, FILE_NAME);
        assertNotNull(newOptionsCollection);
        assertEquals(2, newOptionsCollection.getOptions().size());
    }
}
