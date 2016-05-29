package be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.default_implementation;

import be.rdhaese.packetdelivery.back_end.model.options.Options;
import be.rdhaese.packetdelivery.back_end.model.options.OptionsCollection;
import be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.default_implementation.config.XmlTestConfig;
import be.rdhaese.packetdelivery.back_end.persistence.xml_repositories.interfaces.OptionsRepository;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;

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
        persistToXml(OptionsCollection.class, optionsCollection, XmlTestConfig.OPTIONS_XML);
    }

    @After
    public void tearDown() throws IOException {
        applicationContext.getResource(XmlTestConfig.OPTIONS_XML).getFile().delete();
    }

    @Test
    public void testGetOptionsCollection() throws JAXBException, IOException, URISyntaxException {
        assertEquals(2, optionsRepository.getOptionsCollection().getOptions().size());

        clearFile(XmlTestConfig.OPTIONS_XML);
        assertEquals(0, optionsRepository.getOptionsCollection().getOptions().size());
    }

    @Test
    public void testSaveOptionsCollection() throws JAXBException, IOException, URISyntaxException {
        OptionsCollection optionsCollection = optionsRepository.getOptionsCollection();
        optionsCollection.addOptions(new Options("user3", "language3", 0, true));
        optionsRepository.save(optionsCollection);

        optionsCollection = readFromXml(OptionsCollection.class, XmlTestConfig.OPTIONS_XML);
        assertEquals(3, optionsCollection.getOptions().size());

        clearFile(XmlTestConfig.OPTIONS_XML);
        optionsRepository.save(optionsCollection);
        optionsCollection = readFromXml(OptionsCollection.class, XmlTestConfig.OPTIONS_XML);
        assertEquals(3, optionsCollection.getOptions().size());
    }

    private void clearFile(String fileName) throws IOException {
        File file = applicationContext.getResource(fileName).getFile();
        PrintWriter writer = new PrintWriter(file);
        writer.println("<options></options>");
        writer.close();
    }
}
