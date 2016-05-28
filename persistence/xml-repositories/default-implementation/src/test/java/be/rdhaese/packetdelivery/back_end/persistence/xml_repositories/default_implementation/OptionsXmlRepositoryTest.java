package be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.default_implementation;

import be.rdhaese.packetdelivery.back_end.model.options.Options;
import be.rdhaese.packetdelivery.back_end.model.options.OptionsCollection;
import be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.interfaces.OptionsRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 *
 * @author Robin D'Haese
 */
public class OptionsXmlRepositoryTest extends AbstractXmlRepositoryTest {

    @Autowired
    private OptionsRepository optionsRepository;

    @Before
    public void setUp() throws Exception {
        Options options1 = new Options("user1", "language1", 0, true);
        Options options2 = new Options("user2", "language2", 0, true);
        OptionsCollection optionsCollection = new OptionsCollection();
        optionsCollection.addOptions(options1);
        optionsCollection.addOptions(options2);
        persistToXml(OptionsCollection.class, optionsCollection, OptionsXmlRepository.FILE_NAME);
    }

    @Test
    public void testGetOptionsCollection() throws JAXBException, IOException {
        assertEquals(2, optionsRepository.getOptionsCollection().getOptions().size());

        removeFile(OptionsXmlRepository.FILE_NAME);
        assertEquals(0, optionsRepository.getOptionsCollection().getOptions().size());
    }

    @Test
    public void testSaveOptionsCollection() throws JAXBException, IOException {
        OptionsCollection optionsCollection = optionsRepository.getOptionsCollection();
        optionsCollection.addOptions(new Options("user3", "language3", 0, true));
        optionsRepository.save(optionsCollection);

        optionsCollection = readFromXml(OptionsCollection.class, OptionsXmlRepository.FILE_NAME);
        assertEquals(3, optionsCollection.getOptions().size());

        removeFile(OptionsXmlRepository.FILE_NAME);
        optionsRepository.save(optionsCollection);
        optionsCollection = readFromXml(OptionsCollection.class, OptionsXmlRepository.FILE_NAME);
        assertEquals(3, optionsCollection.getOptions().size());
    }
}
